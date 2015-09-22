package wt.sirius.editor;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.GridView;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.ViewOrdering;

public class TestViewOrdering implements ViewOrdering{

	@Override
	public GridView getSortedViewsAsGrid() {
		// TODO Auto-generated method stub
		System.out.println("getSortedViewsAsGrid");
		return null;
	}

	@Override
	public boolean isAbleToManageView(View view) {
		// TODO Auto-generated method stub
		EObject asd = view.getElement();
		System.out.println(asd.toString());
		System.out.println("isAbleToManageView");
		return false;
	}

	@Override
	public <T extends View> void setViews(Collection<T> views) {
		// TODO Auto-generated method stub
		System.out.println("setViews");
	}

	@Override
	public List<View> getSortedViews() {
		// TODO Auto-generated method stub
		System.out.println("getSortedViews");
		return null;
	}
		
}
