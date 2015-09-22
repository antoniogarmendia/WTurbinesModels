package wt.filter.ext;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class CustomizeFilter extends ViewerFilter {

	
	private EMap<Integer, EList<EObject>> map;
	
	
	public CustomizeFilter(EMap<Integer, EList<EObject>> map) {
		super();
		// TODO Auto-generated constructor stub
		this.map = new BasicEMap<Integer, EList<EObject>>();
		this.map.addAll(map);
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		// TODO Auto-generated method stub
			
		Collection<EList<EObject>> list = map.values();
		
		if(element instanceof EObject)
		{
			
		for (Iterator<EList<EObject>> iterator = list.iterator(); iterator.hasNext();) {
			EList<EObject> current_list = (EList<EObject>) iterator.next();
			for (EObject eObject : current_list) {
				if(element.equals(eObject))
					return true;
				else
				{
					
					EObject current_eobj = (EObject)element;
					URI element_uri = EcoreUtil.getURI(current_eobj);
						
					URI uri_eObj = EcoreUtil.getURI(eObject);
					//String aux = uri_eObj.fragment();
					//String aux1 = element_uri.fragment();
					if(uri_eObj.fragment().contains(element_uri.fragment()))
						return true;						
						
				}
			}					
		}	
		}
		else
			return true;
		
		return false;
	}

}

