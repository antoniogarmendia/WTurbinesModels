package wt.sirius.sample.layout;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;

import rioko.layouts.bridge.AbstractLayoutBridge;
import rioko.layouts.graphImpl.LayoutVertex;


public class TestSamplerSirius extends AbstractLayoutBridge<ShapeEditPart, LayoutVertex>{

	public TestSamplerSirius(Class<LayoutVertex> layoutVertexClass) {
		super(layoutVertexClass);
		// TODO Auto-generated constructor stub
	}

	@Override
	public LayoutVertex parseVertex(ShapeEditPart obj) {
		// TODO Auto-generated method stub
		//obj.getc
		return new LayoutVertex(obj.getFigure().getBounds().preciseWidth(),obj.getFigure().getBounds().preciseHeight());
	}
	
	@Override
	public boolean parseEdge(Object arg0) {
		// TODO Auto-generated method stub
		
		//LayoutVertex source = this.getLayoutVertex(((InternalRelationship)obj).getSource());
		//LayoutVertex target = this.getLayoutVertex(((InternalRelationship)obj).getDestination());
			
		//this.graph.addEdge(source, target);
		return true;
		
	}
	
}
