
package wt.modular;

import java.io.IOException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import wt.modular.wizard.WTComponentsNewProjectInfo;
import wt.modular.wizard.WTComponentsProjectFeatures;
import org.osgi.service.prefs.BackingStoreException;


import dslComponent.WTComponents;
import dslComponent.impl.dslComponentFactoryImpl;
import dslComponent.impl.SubsystemImpl;
import dslComponent.impl.ControlSubsystemImpl;
import dslComponent.impl.ComponentImpl;
import dslComponent.impl.StateMachineImpl;

public class Modular_Diagram {
	
	private XMI_File diagram;
	
	public Modular_Diagram(){}
	
	public void Create_Modular_Diagram(IProject pro, org.eclipse.emf.common.util.URI uri_diagram) throws CoreException, IOException
	{
		//Create new xmi file
		diagram = new XMI_File();
		diagram.Create_XMI_File(uri_diagram);
		//Search the root xmi
		IFile file = pro.getFile(pro.getName().concat(".xmi"));
		XMI_File file_xmi = new XMI_File(URI.createFileURI(file.getFullPath().toString()));
			
		EObject obj =  file_xmi.Get_Root();
		Copier copier = new Copier();
		EObject result = copier.copy(obj);
		copier.copyReferences();
		diagram.getFile_res().getContents().add(result);
		diagram.Save_XMI();		
	}
	
	public void Reverse_Engineering(URI fileURI) throws IOException, CoreException, BackingStoreException
	{
		//Create Modular Project
		XMI_File filemodel = new XMI_File(fileURI, true); 
		WTComponents root = (WTComponents) filemodel.Get_Root();
		WTComponentsProjectFeatures feat = new WTComponentsProjectFeatures(
		root.getId(),
		root.getAuthor()
		);
		WTComponentsNewProjectInfo project = new WTComponentsNewProjectInfo(feat);
		project.Create_Project();
		
		//Folders & Files
		TreeIterator<EObject> model_tree = filemodel.getFile_res().getAllContents();
		
		String path = "";
		URI path_uri = null;
		URI parent_uri = null;
		IFolder fol = null;
		IContainer parent = null;
		XMI_File xmi_file = null;		
		XMI_File xmi_parent = null;
		EObject eObject = null;	
		EObject eObject_root = null;
		String name_folder = null;
		boolean isFile = false;

		model_tree.next();//The first element(Root) was added already
		
		while (model_tree.hasNext()) {
				
			eObject = (EObject) model_tree.next();
					
			//Parent path
			path = GetObjectPath(eObject);

			//Folders
			name_folder = IsFolder(eObject);	
			if(name_folder!=null)			
			{
				//Create Folder
				fol = project.Create_Folder(path);
				parent = fol.getParent();
				//Path to Create XMI File
				path = path.concat("/").concat(name_folder.concat(".xmi"));
				//Create File for Folder
				path_uri = URI.createFileURI(project.getProject().getFullPath().toString().concat(path));		
				name_folder = null;		
			}	
			else
			{	
				//Files
				parent = project.Create_Folder(path);
				if(eObject instanceof ComponentImpl)
					path = path.concat(((ComponentImpl)eObject).getName().toString());
				if(eObject instanceof StateMachineImpl)
					path = path.concat(((StateMachineImpl)eObject).getName().toString());
				//Create File
				path_uri = URI.createFileURI(project.getProject().getFullPath().toString().concat(path));
				isFile = true;
			}
			
			//Object to add
			if(isFile)
			{
				Copier copier = new Copier();
				eObject_root = copier.copy(eObject);
				copier.copyReferences();
				model_tree.prune();
			}
			else
			eObject_root = GetObjectRoot(eObject);

			//Create XMI File
			xmi_file = XMI_File.Get_Create_XMI(path_uri,eObject_root);
			//Include Parent
			parent_uri = URI.createURI(parent.getFullPath().toString().concat("/"+parent.getName().concat(".xmi")));
			xmi_parent = new XMI_File(parent_uri, xmi_file.getFile_reset());
			((EList<EObject>)xmi_parent.Get_Root().eGet(eObject.eContainingFeature())).add(xmi_file.Get_Root());
			xmi_parent.Save_XMI();
			
			path = "";
			path_uri = null;
			fol = null;
			isFile = false;
												
		}		
		System.out.println("Reverse_Engineering_Create_Project_Complete");

	}

	public String IsFolder(EObject eObject){
		
		 if(eObject instanceof SubsystemImpl)
				return ((SubsystemImpl)eObject).getName().toString();	
		 if(eObject instanceof ControlSubsystemImpl)
				return ((ControlSubsystemImpl)eObject).getName().toString();	
		return null;
	}
	
	public String GetObjectPath(EObject obj){
		
		String cad = "/"; 
		EObject parent = obj;
		String name_folder = null;
		while(parent!=null){
			name_folder = IsFolder(parent);
			if(name_folder!=null)
				cad = "/".concat(name_folder).concat(cad);//Attribute annotated as name
			parent = parent.eContainer();						
		}						
		return cad;
	}	
	
	public EObject GetObjectRoot(EObject param_obj){
		
		EObject result = dslComponentFactoryImpl.eINSTANCE.create(param_obj.eClass());
		EList<EAttribute> result_attr = result.eClass().getEAllAttributes();
		for (EAttribute eAttribute : result_attr) {
			result.eSet(eAttribute, param_obj.eGet(eAttribute));
		}
		
		return result;
	}	

}


