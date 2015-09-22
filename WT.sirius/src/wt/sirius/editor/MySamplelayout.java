package wt.sirius.editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.AbstractLayoutProvider;

public class MySamplelayout extends AbstractLayoutProvider{

	@Override
	public Command layoutEditParts(List selectedObjects, IAdaptable layoutHint) {
		// TODO Auto-generated method stub
		final Iterator<?> iterEditParts = selectedObjects.iterator();
		final Map<View, ShapeEditPart> viewsToEditPartMap = new HashMap<View, ShapeEditPart>();
		final List<View> views = new ArrayList<View>(selectedObjects.size());
		final List<ShapeEditPart> sortedEditParts = new ArrayList<ShapeEditPart>(selectedObjects.size());
		 while (iterEditParts.hasNext()) {
			 final Object next = iterEditParts.next();
			 if (next instanceof ShapeEditPart)//DNodeEditPart
			 {
				 final ShapeEditPart shapeEditPart = (ShapeEditPart) next;
				 //shapeEditPart.
				 final View view = shapeEditPart.getNotationView();
				 //view.unsetElement();
	             viewsToEditPartMap.put(view, shapeEditPart);
	             views.add(view);
	             sortedEditParts.add(shapeEditPart);
			 }
		 }
		 
		return createNodeChangeBoundCommands(sortedEditParts);		
	}

	
	protected Command createNodeChangeBoundCommands(final List<ShapeEditPart> sortedNodes) {
		
		final CompoundCommand result = new CompoundCommand();
		final Iterator<ShapeEditPart> iterEditParts = sortedNodes.iterator();
		 while (iterEditParts.hasNext()) {
			 final ShapeEditPart shapeEditPart = iterEditParts.next();
			 
			 final ChangeBoundsRequest request = new ChangeBoundsRequest(org.eclipse.gef.RequestConstants.REQ_MOVE);
			 request.setEditParts(shapeEditPart);
			 final Point ptLocation = new Point(0,0);
			 // the zoom.
             double scale = 1.0;
             if (shapeEditPart.getRoot() instanceof DiagramRootEditPart) {
                 final ZoomManager zoomManager = ((DiagramRootEditPart) shapeEditPart.getRoot()).getZoomManager();
                 scale = zoomManager.getZoom();
             }
             // Compute request data.
             final Point ptOldLocation = shapeEditPart.getFigure().getBounds().getLocation();
             
             final Dimension delta = ptLocation.getDifference(ptOldLocation);
             
             request.setMoveDelta(new PrecisionPoint(delta.width * scale, delta.height * scale));
             request.setLocation(new PrecisionPoint(ptLocation.x * scale, ptLocation.y * scale));
             
             final Command cmd = this.buildCommandWrapper(request, shapeEditPart);
             result.add(cmd);
		 }
		
		return result;
	}
}
