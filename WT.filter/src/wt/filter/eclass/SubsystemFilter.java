package wt.filter.eclass;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.mondo.modular.filterui.ext.IObtainClassFeatures;

import dslComponent.dslComponentPackage;

public class SubsystemFilter implements IObtainClassFeatures{

	@Override
	public EClass GetEClass() {
		// TODO Auto-generated method stub
		return dslComponentPackage.eINSTANCE.getSubsystem();
	}

	@Override
	public EList<EStructuralFeature> GetEStructuralFeature() {
		// TODO Auto-generated method stub
		EList<EStructuralFeature> list_feat = new BasicEList<EStructuralFeature>();
		list_feat.add(GetEClass().getEStructuralFeature("name"));
		list_feat.add(GetEClass().getEStructuralFeature("beh"));
		list_feat.add(GetEClass().getEStructuralFeature("components"));
		list_feat.add(GetEClass().getEStructuralFeature("subsystems"));
		return list_feat;		
	}

	@Override
	public boolean isIstanceEObject(EObject anEObject) {
		// TODO Auto-generated method stub
		if(GetEClass().isInstance(anEObject))
			return true;
		else
			return false;
	}

	@Override
	public EList<IObtainClassFeatures> GetEListClasses() {
		// TODO Auto-generated method stub
		EList<IObtainClassFeatures> list_classes = new BasicEList<IObtainClassFeatures>();
		//list_classes.add(new ControlSubsystemFilter());
		//list_classes.add(new StateMachineFilter());
		//list_classes.add(new VertexFilter());
		//list_classes.add(new EdgeFilter());
		//list_classes.add(new ComponentFilter());
		//list_classes.add(new PortFilter());
		//list_classes.add(new SubsystemFilter());
		list_classes.add(new SimpleStateFilter());
		//list_classes.add(new InitialStateFilter());
		//list_classes.add(new InPortFilter());
		//list_classes.add(new OutPortFilter());
		return list_classes;
	}
	
}

