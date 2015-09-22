package wt.filter.eclass;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.mondo.modular.filterui.ext.IObtainClassFeatures;

import dslComponent.dslComponentPackage;

public class OutPortFilter implements IObtainClassFeatures{

	@Override
	public EClass GetEClass() {
		// TODO Auto-generated method stub
		return dslComponentPackage.eINSTANCE.getOutPort();
	}

	@Override
	public EList<EStructuralFeature> GetEStructuralFeature() {
		// TODO Auto-generated method stub
		EList<EStructuralFeature> list_feat = new BasicEList<EStructuralFeature>();
		list_feat.add(GetEClass().getEStructuralFeature("name"));
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
		return list_classes;
	}
	
}

