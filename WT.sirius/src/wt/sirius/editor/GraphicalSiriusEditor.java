package wt.sirius.editor;

import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IEditorLauncher;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;

import wt.sirius.ext.CreateDesignSiriusProject;

public class GraphicalSiriusEditor implements IEditorLauncher {

	@Override
	public void open(IPath file) {
		// TODO Auto-generated method stub
		IPath plug_path = ResourcesPlugin.getWorkspace().getRoot().getLocation();
		String relative_path = file.toString().replaceFirst(plug_path.toString(),"");
		URI anfileURI = URI.createPlatformResourceURI(relative_path, true);	
		IProgressMonitor monitor = new NullProgressMonitor();
		
		// Step 1: get the *.aird file and the corresponding Sirius Session
		URI sessionResourceURI = URI.createURI(anfileURI.toString().substring(0,
				anfileURI.toString().length()-anfileURI.fileExtension().length()).concat(CreateDesignSiriusProject.aird),true);
		final Session createdSession = SessionManager.INSTANCE.getSession(sessionResourceURI,monitor);
		createdSession.open(monitor);
		
		// Step 2: Get the DRepresentation to open
		DAnalysis root = (DAnalysis) createdSession.getSessionResource().getContents().get(0);
		DView dView = root.getOwnedViews().get(0);
		DRepresentation dRepresentation = dView.getOwnedRepresentations().get(0);
				
		// Step 3: Open representation
		DialectUIManager.INSTANCE.openEditor(createdSession, dRepresentation, monitor);
				
		System.out.println("Sirius Editor: " + file.toString());
	}
}
