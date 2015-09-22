
package wt.modular.wizard;


public class WTComponentsProjectFeatures {
	
	
	//Attributes
	private java.lang.String atr_id;
	private java.lang.String atr_author;
	
	//Constructor with Attributes
	public WTComponentsProjectFeatures(
		 java.lang.String atr_id ,		 java.lang.String atr_author ){
		this.atr_id = atr_id;
		this.atr_author = atr_author;
	
	}											

	//Constructor
	public WTComponentsProjectFeatures() {
		atr_id = "org.eclipse.modular.example"; 
	}

	//Get Methods
	public java.lang.String Get_id()
	{
		return atr_id;
	}
	public java.lang.String Get_author()
	{
		return atr_author;
	}
	
	//Set Methods
	public void Set_id(java.lang.String arg)
	{
		this.atr_id = arg;
	}
	public void Set_author(java.lang.String arg)
	{
		this.atr_author = arg;
	}

}

