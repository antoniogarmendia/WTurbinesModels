package wt.filter.ext;

import org.eclipse.jface.viewers.ViewerFilter;
import wt.filter.eclass.ContextFilterRoot;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.mondo.modular.filterui.ext.FilterAttribute;
import org.mondo.modular.filterui.ext.IFilterPage;
import org.mondo.modular.filterui.ext.IFilterTabItem;
import org.mondo.modular.filterui.ext.IObtainClassFeatures;

import dslFilter.impl.AllFiltersImpl;

public class FilterViewerPane extends AllFiltersImpl implements IFilterPage {
	
		
	//The Constructor Attributes
	protected FilterAttribute anFilterAttribute;
	
	//Attributes
	protected Composite container;
	protected ViewForm view;	
	protected Composite inner;	
	protected ExpandBar bar;
	
	//List Classes
	private ContextFilterRoot context;
	
	public FilterViewerPane(){
		
		super();
		this.anFilterAttribute = null;
		//Attributes
		this.container = null;
		this.view = null;
		this.inner = null;	
		this.bar = null;
		context = new ContextFilterRoot();
	}

	public void CreateControl(Composite parent){
		// TODO Auto-generated method stub
		 
		if (getControl() == null)
		{
			container = parent;
			view = new ViewForm(container, SWT.NONE);
			inner = new Composite(view, SWT.NONE);
			
			//Layout inner
			GridLayout layout = new GridLayout();
		    layout.numColumns = 1;
		    layout.marginLeft = 10;
		   	inner.setLayout(layout);
		   	
		   	//Global Inner Data
		   	GridData grd_inner = new GridData(SWT.DEFAULT, SWT.DEFAULT);//800 default
		   	inner.setData(grd_inner);
		   	//End		
		   	
			Create_AllFilters(inner);
			
			Create_Expand_bar(inner);
			
			view.setContent(inner);
		}
		
	}
	
	
	/*
	 * Create Filter Attributes	   
	 * */
		
	public void Create_AllFilters(Composite anComposite){
		
		//Initialize Values
		InitAllFilters();
		
		Composite anComposite_Filter = new Composite(anComposite, SWT.NONE);
		GridLayout first = new GridLayout ();
        first.numColumns = 8;
        first.horizontalSpacing = 8;
        anComposite_Filter.setLayout(first);	
        
        //Label Name
      	Label lbl_name = new Label(anComposite_Filter, SWT.CENTER);
      	lbl_name.setText("Name: ");
      	
      	//TextBox
      	Text txt_name = new Text(anComposite_Filter, SWT.BORDER | SWT.SINGLE | SWT.HORIZONTAL);
      	txt_name.setText(name);
      	
        //Label Negative
      	Label lbl_neg = new Label(anComposite_Filter, SWT.CENTER);
      	lbl_neg.setText("Negative: ");
      				
      	//CheckBox Negative
      	Button btn_neg = new Button(anComposite_Filter, SWT.CHECK); 
      	btn_neg.setSelection(isNegative());
      		
      	//Add Group Filter
      	Button btn_add = new Button(anComposite_Filter, SWT.PUSH);
      	btn_add.setText("Add Group");
      	
        //Execute Filters
        Button btn_exec = new Button(anComposite_Filter, SWT.PUSH);
      	btn_exec.setText("Execute All");
      	
      	//Reset Filters
        Button btn_reset = new Button(anComposite_Filter, SWT.PUSH);
        btn_reset.setText("Reset");
      	
       	//Save Configuration
      	Button btn_save = new Button(anComposite_Filter, SWT.PUSH);
      	btn_save.setText("Save Configuration");
      	
      	//Start Listeners
      	
      	//Execute 
      	btn_exec.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				DeleteAllEObjectsSatisfiesCondition();
				Execute(anFilterAttribute.getResource().getAllContents());	
				Delete_Filters();
				Update_Filter_Viewer();
			}     		
      		
		});
      	
        //Reset 
    	btn_reset.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				Delete_Filters();
			}  		
    		
		});      	
      	
    	//Add Group
    	btn_add.addSelectionListener(new SelectionAdapter() {
    		
    		@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
    			Create_Group_Expand();
			}     		
    		
		});
    	
    	//END Listeners
	}
	
	private void Create_Expand_bar(Composite anComposite) {
		
		bar = new ExpandBar(anComposite, SWT.V_SCROLL);				
		Create_Group_Expand();				
	}
	
	/*
	 * Create Group Inside ExpandBar
	 * */
	public void Create_Group_Expand()
	{
		// First item
		Composite composite = new Composite (bar, SWT.NONE);
		
		GridLayout grp_layout = new GridLayout ();
		grp_layout.numColumns = 1;
		composite.setLayout(grp_layout);
		
		FilterGroupUI fg_ui = new FilterGroupUI(groupfilters.size(),GetEClassFeat());
		fg_ui.CreateControl(bar);
		
		fg_ui.GetBtn_Del().addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				int count = groupfilters.size();
				if(count>1){
					fg_ui.RemoveControl();
					groupfilters.remove(fg_ui);	
					Update_Expand_Bar();
				}
			}		
			
		});
		
		groupfilters.add(fg_ui);
		
		//Update Height of the Expand Bar
		Update_Expand_Bar();
	}

	public void Update_Filter_Viewer()
	{
		anFilterAttribute.getFilterViewer().addFilter(new CustomizeFilter(AllEObjectsSatifiesCondition()));
		anFilterAttribute.getFilterViewer().expandAll();
	}
	
	protected void Delete_Filters(){
		
		ViewerFilter[] array_filter = anFilterAttribute.getFilterViewer().getFilters();
		for (ViewerFilter viewerFilter : array_filter) {
			anFilterAttribute.getFilterViewer().removeFilter(viewerFilter);
		}		
	}
	
	public void Update_Expand_Bar()
	{
		
		int count = groupfilters.size();
					
		if(count<=3)
		{
			GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
			gridData.widthHint = 900;
			gridData.heightHint = (165*count);		
			bar.setLayoutData(gridData);
			view.setContent(inner);
		}				
	}
	
	@Override
	public IObtainClassFeatures GetEClassFeat() {
		// TODO Auto-generated method stub
		return context.ListClasses(anFilterAttribute.getResource().getContents().get(0));
	}
	
	@Override
	public Control getControl() {
		// TODO Auto-generated method stub
		return view;
	}

	@Override
	public FilterAttribute getfilterattributes() {
		// TODO Auto-generated method stub
		return anFilterAttribute;
	}

	@Override
	public void setFilterAttribute(FilterAttribute anFilterAttribute) {
		// TODO Auto-generated method stub
		this.anFilterAttribute = anFilterAttribute;
	}

	@Override
	public void CreateControl() {
		// TODO Auto-generated method stub
		CreateControl(anFilterAttribute.getComposite());
		//Add Page
		int pageIndex = ((IFilterTabItem)anFilterAttribute.getPart()).AddItemPage(getControl());
		//Set Page Text
		((IFilterTabItem)anFilterAttribute.getPart()).SetPageItemText(pageIndex, "Filter View");
	}	
	
}

