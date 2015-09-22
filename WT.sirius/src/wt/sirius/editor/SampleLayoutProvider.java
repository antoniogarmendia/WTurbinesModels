package wt.sirius.editor;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemsAwareFreeFormLayer;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemsAwareFreeFormLayer.BorderItemAwareFreeFormHelper;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemsUtil;
import org.eclipse.gmf.runtime.diagram.ui.internal.figures.BorderItemContainerFigure;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutEditPartProvider;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.CompoundLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.InlineEdgeLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.LayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.LineLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.BorderItemAwareLayoutProvider;
import org.eclipse.sirius.viewpoint.DAnalysis;

import wt.sirius.sample.layout.SiriusSampler;
import dslComponent.StateMachine;

public class SampleLayoutProvider implements LayoutProvider {

	public SampleLayoutProvider() {
		// TODO Auto-generated constructor stub
	}

	/**The GMF layout provider**/
	private CompoundLayoutProvider layoutProvider;
	
	@Override
	public boolean provides(IGraphicalEditPart container) {//When click a node or an edge, I think that any action...
		// TODO Auto-generated method stub

		System.out.println("SampleLayoutProvider provides");
		return true;
	}

	@Override
	public AbstractLayoutEditPartProvider getLayoutNodeProvider(
			IGraphicalEditPart container) {
		// TODO Auto-generated method stub
		if(isStateMachineDiagram(container))
		{
			if(this.layoutProvider==null)
			{
				//this.layoutProvider = getLineLayoutProviderOther();
				
			}	
			
			//return this.layoutProvider;
			//return getLineLayoutProviderOther();
			return getSample();
		}
		System.out.println("getLayoutNodeProvider");
		return null;
	}

	@Override
	public boolean isDiagramLayoutProvider() {
		// TODO Auto-generated method stub
		System.out.println("isDiagramLayoutProvider");
		return true;
	}
	
	private boolean isStateMachineDiagram(IGraphicalEditPart container){
		
		if(container instanceof AbstractDDiagramEditPart)
		{
			AbstractDDiagramEditPart editpart = (AbstractDDiagramEditPart) container;
			if(editpart.resolveSemanticElement() instanceof DDiagram)
			{
				DDiagram diagram = (DDiagram)editpart.resolveSemanticElement();
				System.out.println("Por aqui!!");
			}
		}
		
		return true;
		
	}
	
	//Example LineLayoutProvider
	public CompoundLayoutProvider getLineLayoutProvider() {
		
		CompoundLayoutProvider auxLayout = new CompoundLayoutProvider();
		LineLayoutProvider lineLayoutProvider = new LineLayoutProvider();
		lineLayoutProvider.getPadding().right = 20;
		//lineLayoutProvider.setHorizontal(false);
		lineLayoutProvider.setHorizontal(true);
		auxLayout.addProvider(lineLayoutProvider);
		InlineEdgeLayoutProvider inlineedge = new InlineEdgeLayoutProvider();
		inlineedge.setSide(PositionConstants.EAST_WEST);//EAST_WEST
		inlineedge.setStart(PositionConstants.RIGHT);//PositionConstants.RIGHT
		inlineedge.setAlignment(PositionConstants.VERTICAL);//PositionConstants.VERTICAL
		inlineedge.setChangeNodeHeight(true);
		inlineedge.setChangeNodeWidth(true);
		//inlineedge.getPaddings().top = 1000;
		auxLayout.addProvider(inlineedge);		
		return auxLayout;
	}
	
	public CompoundLayoutProvider getLineLayoutProviderOther() {
		
		CompoundLayoutProvider auxLayout = new CompoundLayoutProvider();
		LineLayoutProvider lineLayoutProvider = new LineLayoutProvider();
		lineLayoutProvider.getPadding().right = 20;
		lineLayoutProvider.setHorizontal(true);
		//lineLayoutProvider.setHorizontal(false);
		auxLayout.addProvider(lineLayoutProvider);
		InlineEdgeLayoutProvider inlineedge = new InlineEdgeLayoutProvider();
		inlineedge.setSide(PositionConstants.EAST_WEST);//EAST_WEST
		inlineedge.setStart(PositionConstants.RIGHT);//PositionConstants.RIGHT
		inlineedge.setAlignment(PositionConstants.VERTICAL);//PositionConstants.VERTICAL
		inlineedge.setChangeNodeHeight(true);
		inlineedge.setChangeNodeWidth(true);
		//inlineedge.getPaddings().top = 1000;
		auxLayout.addProvider(inlineedge);
		
		//BorderItemAwareLayoutProvider border = new BorderItemAwareLayoutProvider(auxLayout);
		//BorderItemAwareLayoutProvider border = new BorderItemAwareLayoutProvider(auxLayout);
		
		//auxLayout.addProvider(border);
		
		return auxLayout;
	}
	
	public SiriusSampler getSample(){
		return new SiriusSampler();
	}
	
}
