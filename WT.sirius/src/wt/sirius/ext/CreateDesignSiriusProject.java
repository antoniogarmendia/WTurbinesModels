package wt.sirius.ext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.helper.SiriusResourceHelper;
import org.eclipse.sirius.business.api.session.DefaultLocalSessionCreationOperation;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionCreationOperation;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallbackWithConfimation;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
import org.eclipse.sirius.ui.tools.api.project.ViewpointSpecificationProject;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.PlatformUI;
import org.mondo.modular.sirius.ext.ISiriusCreation;

public class CreateDesignSiriusProject implements ISiriusCreation {

	private static String prefix_name = ".odesign";
	private static final String NL = System.getProperty("line.separator");
	public static final String aird = "aird";
	
	public CreateDesignSiriusProject() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ExecuteAfterCreateProject(String anProjectName) {
		// TODO Auto-generated method stub
		//Create ViewPoint Specification Project
		IProject current_pro = ResourcesPlugin.getWorkspace().getRoot().getProject(anProjectName.concat(prefix_name));
		if(current_pro.exists())
			return;
		String modelInitialObjectName = ViewpointSpecificationProject.INITIAL_OBJECT_NAME;
		ProgressMonitorDialog monitorDialog = new ProgressMonitorDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		IPath projectLocationPath = ResourcesPlugin.getWorkspace().getRoot().getLocation();
		try {
			//Create ViewPoint Specification Project
			current_pro = ViewpointSpecificationProject.
					createNewViewpointSpecificationProject(PlatformUI.getWorkbench(), anProjectName.concat(prefix_name),
							projectLocationPath , "WT.odesign",
								 modelInitialObjectName, "UTF-8", monitorDialog);
			//END
			
			//Fix Sirius Bug Eclipse Luna
			//add org.eclipse.core.resources						
			IFile manifest = current_pro.getFile("META-INF/MANIFEST.MF");			
			final InputStream is = manifest.getContents();
			final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	        final StringBuilder sb = new StringBuilder();
	        String line = null;
	        
	        try {
				while ((line = reader.readLine()) != null) {
					sb.append(line).append(NL);
					if(line.contains("Require-Bundle"))
						sb.append(" org.eclipse.core.resources,").append(NL);						
					//System.out.println(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        final InputStream update = new StringBufferInputStream(sb.toString());
	        manifest.setContents(update, IResource.FORCE , new NullProgressMonitor());	        
			System.out.println("Update Manifest File");						
			//End Fixing Sirius Bug
			//Update odesign file
			InputStream file =  CreateDesignSiriusProject.class.getResourceAsStream("/description/WT.odesign");
			IFile odesign = current_pro.getFile("description/WT.odesign");
			odesign.setContents(file, IResource.FORCE, new NullProgressMonitor());
			System.out.println("Update odesign file");
			//END
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//End
		System.out.println(anProjectName);
	}

	@Override
	public void ExecuteAfterCreateFile(URI anfileURI) {
		// TODO Auto-generated method stub
		//Create *.aird if it doesn't exist
		IProgressMonitor monitor = new NullProgressMonitor();
		URI sessionResourceURI = URI.createURI(anfileURI.toString().substring(0,
										anfileURI.toString().length()-anfileURI.fileExtension().length()).concat(aird),true);
		
		//Create Session Operation
		SessionCreationOperation oper = new DefaultLocalSessionCreationOperation(sessionResourceURI, monitor);
		try {
			//Create Session
			oper.execute();
			Session createdSession = oper.getCreatedSession();

			//Add Default Representation
				//Adding the resource also to Sirius session
				AddSemanticResourceCommand addCommandToSession = new AddSemanticResourceCommand(createdSession,anfileURI,monitor);
				createdSession.getTransactionalEditingDomain().getCommandStack().execute(addCommandToSession);
				createdSession.save(monitor); 	
				//END
			//Add View
				TransactionalEditingDomain domain = createdSession.getTransactionalEditingDomain();
				final Set<Viewpoint> newSelectedViewpoints = ViewpointSelection.getViewpoints(anfileURI.fileExtension());
				Set<Viewpoint> viewpoints = new HashSet<Viewpoint>();
				final ViewpointSelection.Callback callback = new ViewpointSelectionCallbackWithConfimation();
				
				String name = null;					
				for(Viewpoint p : newSelectedViewpoints){
					viewpoints.add(SiriusResourceHelper.getCorrespondingViewpoint(createdSession, p));
					name = p.getName();
				}
				@SuppressWarnings("restriction")
				Command command = new ChangeViewpointSelectionCommand(createdSession, 
						callback, viewpoints , new HashSet<Viewpoint>(), true,
						monitor);
				domain.getCommandStack().execute(command);
				createdSession.save(monitor); 
				//END		
				
			//Create Representation
				EObject rootObject = createdSession.getSemanticResources().iterator().next().getContents().get(0);
				Collection<RepresentationDescription> descriptions = DialectManager.INSTANCE.getAvailableRepresentationDescriptions(createdSession.getSelectedViewpoints(false),  rootObject );
				if(descriptions.isEmpty())
					throw new Exception("Could not found representation description for object: " + rootObject);
					
				RepresentationDescription description = descriptions.iterator().next();
				
				//DialectManager viewpointDialectManager = DialectManager.INSTANCE;
				Command createViewCommand = new CreateRepresentationCommand(createdSession,
						  description, rootObject, "Default "+ name + " Diagram", monitor);
				
				createdSession.getTransactionalEditingDomain().getCommandStack().execute(createViewCommand);

				SessionManager.INSTANCE.notifyRepresentationCreated(createdSession);
			
			//Save session and Refresh workspace		
			createdSession.save(monitor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			

		System.out.println("Create *.aird and Representation Command");
	}
}
