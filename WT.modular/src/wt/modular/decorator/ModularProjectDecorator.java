
package wt.modular.decorator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import wt.modular.XMI_File;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import dslComponent.impl.SubsystemImpl;
import dslComponent.impl.ControlSubsystemImpl;

public class ModularProjectDecorator implements ILightweightLabelDecorator {

	@Override
	public void addListener(ILabelProviderListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isLabelProperty(Object arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}
	
	private static final ImageDescriptor PACKAGE_Subsystem;  
	private static final ImageDescriptor PACKAGE_ControlSubsystem;  
	static {  
	 	PACKAGE_Subsystem = AbstractUIPlugin.imageDescriptorFromPlugin("WT.modular", "icons/Subsystem.png");
	 	PACKAGE_ControlSubsystem = AbstractUIPlugin.imageDescriptorFromPlugin("WT.modular", "icons/ControlSubsystem.png");
	}  	
	
	
	@Override
	public void decorate(Object element, IDecoration decoration) {
		// TODO Auto-generated method stub
		if(element instanceof IFolder)
		{
			try{
				IFolder folder = (IFolder)element;
				IProjectNature nature = folder.getProject().getNature("WT.modular.ModularNature");
				if(nature.getProject().exists()){
					//Search XMI
					IFile file = folder.getFile(folder.getName().concat(".xmi"));
					if(file.exists()){
						XMI_File file_xmi = new XMI_File(URI.createFileURI(file.getFullPath().toString()));
						EObject root = file_xmi.Get_Root();
						if(root instanceof SubsystemImpl)
							decoration.addOverlay(PACKAGE_Subsystem, IDecoration.BOTTOM_LEFT);
						if(root instanceof ControlSubsystemImpl)
							decoration.addOverlay(PACKAGE_ControlSubsystem, IDecoration.BOTTOM_LEFT);
					}						
				}
				
			}catch(Throwable th){/*Ignore if file is not found*/ }
		}
		
	}
}
