
package wt.modular.propertysource;

import java.io.IOException;

import wt.modular.XMI_File;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import dslComponent.WTComponents;

public class WTComponentsItemPropertiesSource implements IPropertySource { 
	
	private XMI_File property_file;

	public WTComponentsItemPropertiesSource(XMI_File property_file) throws IOException{
		this.property_file = property_file;		
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		// TODO Auto-generated method stub
		if(id.equals("id"))
			try {
				((WTComponents)property_file.Get_Root()).setId(value.toString());				
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}			
		else
		if(id.equals("author"))
			try {
				((WTComponents)property_file.Get_Root()).setAuthor(value.toString());				
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
		if(id.equals("id"))
			try {
				return ((WTComponents)property_file.Get_Root()).getId().toString();				
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}		
		
		else
		if(id.equals("author"))
			try {
				return ((WTComponents)property_file.Get_Root()).getAuthor().toString();				
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}		
		
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		// TODO Auto-generated method stub

		TextPropertyDescriptor attr_id = new TextPropertyDescriptor("id", "id");
		attr_id.setCategory("Attributes");
		TextPropertyDescriptor attr_author = new TextPropertyDescriptor("author", "author");
		attr_author.setCategory("Attributes");
		return new IPropertyDescriptor[]{
				attr_id
				,				
				attr_author
								
		};
	}
	
	@Override
	public Object getEditableValue() {
		// TODO Auto-generated method stub
		return null;
	}
		
	}

