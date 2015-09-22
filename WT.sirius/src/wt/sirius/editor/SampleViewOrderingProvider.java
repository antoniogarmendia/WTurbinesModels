package wt.sirius.editor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.NodeMappingSpec;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.ViewOrdering;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.ViewOrderingProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.GridLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.InlineEdgeLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.LineLayoutProvider;

import dslComponent.InitialState;

public class SampleViewOrderingProvider implements ViewOrderingProvider {

	/** The classifier ordering provider for UML2 modeler **/
	//private ClassifierTreeOrdering classifierOrdering = ne
	//private StateMachineTreeOrdering ordering = new StateMachineTreeOrdering();
	
	public SampleViewOrderingProvider() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean provides(DiagramElementMapping mapping) {
		// TODO Auto-generated method stub
		NodeMappingSpec spe = null;
		if(mapping instanceof NodeMappingSpec)
			spe = (NodeMappingSpec)mapping;
		String str = mapping.getName();
		//spe.
		
		String lab = mapping.getLabel();
		System.out.println("provides_class: SampleViewOrderingProvider: " + str + "Label: " + lab );
		return true;
	}

	@Override
	public ViewOrdering getViewOrdering(DiagramElementMapping mapping) {
		// TODO Auto-generated method stub
		System.out.println("getViewOrdering");
		String asd = getMappingName(mapping); 
		//ViewOrdering asdasd = (ViewOrdering) 
		Object asd121 = getMappingToViewOrdering().get(getMappingName(mapping));
		return (ViewOrdering) getMappingToViewOrdering().get(getMappingName(mapping));
	}
	
	 private String getMappingName(DiagramElementMapping mapping) {
	        String result = null;
	        if (mapping instanceof NodeMapping) {
	        	result = ((NodeMapping) mapping).getName();
	        }	
	        else if (mapping instanceof EdgeMapping) {
	        	result = ((EdgeMapping) mapping).getName();
			}
	        return result;
	 }
	 
	 private Map getMappingToViewOrdering(){
		 Map result = new HashMap<>();
		 result.put("IdInitialState", new TestViewOrdering());
		 result.put("IdSimpleState", new TestViewOrdering());
		 result.put("IdEdge", new TestViewOrdering());
		 //new LifelineOrdering();
		 //new GridLayoutProvider();
		 //new InlineEdgeLayoutProvider();		 
		 return result;
	 }

}
