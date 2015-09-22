
package wt.modular.editors;

import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.mondo.modular.filterui.ext.EvaluateFilterUIContributor;
import org.mondo.modular.filterui.ext.FilterAttribute;
import org.mondo.modular.filterui.ext.IFilterTabItem;

import dslComponent.presentation.dslComponentEditor;

public class MultiPageEditor extends dslComponentEditor implements IFilterTabItem{

	@Override
	public void createPages() {
		// TODO Auto-generated method stub
		super.createPages();
		
		if (!getEditingDomain().getResourceSet().getResources().isEmpty())
		// Create a page for the Filter view.
		{
			FilterAttribute anFilterAttribute = new FilterAttribute(getSite().getPage(),
												MultiPageEditor.this, getSite().getShell().getDisplay(),
												selectionViewer,editingDomain.getResourceSet().getResources().get(0),
												getContainer());			
			new EvaluateFilterUIContributor().execute(Platform.getExtensionRegistry(),anFilterAttribute);			
		}	
		
		//Try to add the view to the editor
		//IViewPart view_proper = PlatformUI.getWorkbench().
		//		getActiveWorkbenchWindow().getActivePage().findView("WT.modular.view1");
		//if(view_proper==null){
		//	try {
		//		view_proper = PlatformUI.getWorkbench().getActiveWorkbenchWindow().
		//						getActivePage().
		//						showView("WT.modular.view1");
		//		} catch (PartInitException e) {
		//		// TODO Auto-generated catch block
		//		e.printStackTrace();
		//		}			
		//}
		System.out.println("kjajaskjaskjas");
	}

	@Override
	public int AddItemPage(Control control) {
		// TODO Auto-generated method stub
		return addPage(control);		
	}

	@Override
	public void SetPageItemText(int pageCount, String name) {
		// TODO Auto-generated method stub
		setPageText(pageCount, name);
	}
	
}

