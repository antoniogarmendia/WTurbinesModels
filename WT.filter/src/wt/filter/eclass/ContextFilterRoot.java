package wt.filter.eclass;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.mondo.modular.filterui.ext.IObtainClassFeatures;

public class ContextFilterRoot {
	
	private EList<IObtainClassFeatures> list_feat;

	public ContextFilterRoot() {
		
		list_feat = new BasicEList<IObtainClassFeatures>();
		list_feat.add(new WTComponentsFilter());	
		list_feat.add(new SubsystemFilter());	
		list_feat.add(new ComponentFilter());	
		list_feat.add(new StateMachineFilter());	
		list_feat.add(new VertexFilter());	
		list_feat.add(new EdgeFilter());	
		list_feat.add(new SimpleStateFilter());	
		list_feat.add(new InitialStateFilter());	
		list_feat.add(new ControlSubsystemFilter());	
		list_feat.add(new DocumEltFilter());	
		list_feat.add(new PortFilter());	
		list_feat.add(new InPortFilter());	
		list_feat.add(new OutPortFilter());	
	}	
	
	public IObtainClassFeatures ListClasses(EObject anEObject)
	{
		for (IObtainClassFeatures anObtainClassFeatures : list_feat) {
			if(anObtainClassFeatures.isIstanceEObject(anEObject))
				return anObtainClassFeatures;
		}		
		return null;		
	}
	
	

}

