package wt.modular.editors;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;



public class FilterView extends ViewPart {

	public static final String ID = "WT.modular.view1";	
	
	public FilterView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		IEditorPart editor = 
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().addSelectionListener(new ISelectionListener() {

			@Override
			public void selectionChanged(IWorkbenchPart part,
					ISelection selection) {
				// TODO Auto-generated method stub
				System.out.println("Cambio el editor!!!");
			}	
				
			});
		
		if(editor instanceof MultiPageEditor){
			MultiPageEditor multi = (MultiPageEditor)editor;
			//multi.getEditingDomain().getResourceSet().getResources().get(0).getURI().toString()
			
		Text lbl = new Text(parent,SWT.NONE);
		lbl.setText("AQui estamos!!!" + multi.getEditingDomain().getResourceSet().getResources().get(0).getURI().toString());	
		}
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
		System.out.println("Change!!!");
	}

}
