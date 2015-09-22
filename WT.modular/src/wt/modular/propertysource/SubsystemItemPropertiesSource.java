
package wt.modular.propertysource;

import java.io.IOException;

import wt.modular.XMI_File;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import dslComponent.Subsystem;

public class SubsystemItemPropertiesSource implements IPropertySource { 
	
	private XMI_File property_file;

	public SubsystemItemPropertiesSource(XMI_File property_file) throws IOException{
		this.property_file = property_file;		
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		// TODO Auto-generated method stub
		if(id.equals("name"))
			try {
				((Subsystem)property_file.Get_Root()).setName(value.toString());				
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}			
		else
		if(id.equals("description"))
			try {
				((Subsystem)property_file.Get_Root()).setDescription(value.toString());				
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}			
		try {
			property_file.Save_XMI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

	@Override
	public void resetPropertyValue(Object id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isPropertySet(Object id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Object getPropertyValue(Object id) {
		// TODO Auto-generated method stub
		if(id.equals("name"))
			try {
				return ((Subsystem)property_file.Get_Root()).getName().toString();				
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}		
		
		else
		if(id.equals("description"))
			try {
				return ((Subsystem)property_file.Get_Root()).getDescription().toString();				
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}		
		
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		// TODO Auto-generated method stub

		TextPropertyDescriptor attr_name = new TextPropertyDescriptor("name", "name");
		attr_name.setCategory("Attributes");
		TextPropertyDescriptor attr_description = new TextPropertyDescriptor("description", "description");
		attr_description.setCategory("Attributes");
		return new IPropertyDescriptor[]{
				attr_name
				,				
				attr_description
								
		};
	}
	
	@Override
	public Object getEditableValue() {
		// TODO Auto-generated method stub
		return null;
	}
		
	}

