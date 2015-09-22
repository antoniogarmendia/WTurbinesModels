package wt.filter.eclass;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.mondo.modular.filterui.ext.IObtainClassFeatures;

import dslComponent.dslComponentPackage;

public class ControlSubsystemFilter implements IObtainClassFeatures{

	@Override
	public EClass GetEClass() {
		// TODO Auto-generated method stub
		return dslComponentPackage.eINSTANCE.getControlSubsystem();
	}

	@Override
	public EList<EStructuralFeature> GetEStructuralFeature() {
		// TODO Auto-generated method stub
		EList<EStructuralFeature> list_feat = new BasicEList<EStructuralFeature>();
		list_feat.add(GetEClass().getEStructuralFeature("name"));
		list_feat.add(GetEClass().getEStructuralFeature("machines"));
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
		//list_classes.add(new StateMachineFilter());
		//list_classes.add(new VertexFilter());
		//list_classes.add(new EdgeFilter());
		list_classes.add(new SimpleStateFilter());
		//list_classes.add(new InitialStateFilter());
		return list_classes;
	}
	
}

