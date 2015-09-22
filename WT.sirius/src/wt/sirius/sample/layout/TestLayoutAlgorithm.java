package wt.sirius.sample.layout;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;


import rioko.layouts.algorithms.AbstractLayoutAlgorithm;
//import org.eclipse.zest.layouts.algorithms.AbstractLayoutAlgorithm;
import rioko.layouts.algorithms.LayoutAlgorithm;
import rioko.layouts.runtime.registers.RegisterLayoutAlgorithm;

public class TestLayoutAlgorithm extends AbstractLayoutAlgorithm{

	//private TestSamplerSirius bridge = new TestSamplerSirius(ShapeEditPart.class);
	private LayoutAlgorithm currentAlgorithm = RegisterLayoutAlgorithm.getRegisteredAlgorithms().get(0);
	
	@Override
	public LayoutAlgorithm copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void running() {
		// TODO Auto-generated method stub
		
	}
	

}
