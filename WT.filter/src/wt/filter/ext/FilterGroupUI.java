package wt.filter.ext;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.mondo.modular.filterui.ext.IObtainClassFeatures;

import dslFilter.EnumLogicOperation;
import dslFilter.FilterContent;
import dslFilter.impl.DslFilterFactoryImpl;
import dslFilter.impl.FilterGroupImpl;

public class FilterGroupUI extends FilterGroupImpl{

	protected Composite composite;
	private int count_filtergroup;
	private IObtainClassFeatures anClassFeatures;
	private ExpandItem item;
	
	//Attributes
	private Button btn_del; 
		
	public FilterGroupUI(int count, IObtainClassFeatures anClassFeatures){
		super();
		this.count_filtergroup = count;
		this.anClassFeatures = anClassFeatures;
		this.composite = null;
		this.item = null;
	}
	
	public void CreateControl(ExpandBar bar){
		
		//Initialize Filter Group
		InitFilterGroup(count_filtergroup);
		
		// First item
		composite = new Composite (bar, SWT.NONE);
		
		GridLayout grp_layout = new GridLayout ();
		grp_layout.numColumns = 1;
		composite.setLayout(grp_layout);
		
		//Create Row for Group Attributes
		CreateGroupAttributes();
		
		//Create Title Row
		CreateTitleRow();
				
		//Add first item
		AddItem();
		
		item = new ExpandItem(bar, SWT.NONE);
		item.setText(getName());
		item.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item.setControl(composite);
		item.setExpanded(true);		
	}
	
	private void CreateGroupAttributes() {
		
		Composite anComposite_Filter = new Composite(composite, SWT.NONE);
		GridLayout first = new GridLayout ();
        first.numColumns = 13;
        first.horizontalSpacing = 8;
        anComposite_Filter.setLayout(first);	 		
		
		//Label Name
		Label lbl_name = new Label(anComposite_Filter, SWT.CENTER);
		lbl_name.setText("Name: ");
		
		//TextBox
		Text txt_name = new Text(anComposite_Filter, SWT.BORDER | SWT.SINGLE | SWT.HORIZONTAL);
		txt_name.setText(getName());
		
		//Label Classes
		Label lbl_class = new Label(anComposite_Filter, SWT.NONE);
		lbl_class.setText("Class: ");	
				
		//ComboBox Classes
		Combo cmb_classes = new Combo(anComposite_Filter, SWT.READ_ONLY);
		cmb_classes.setItems(GetClassName());
				
		//Label Logic Operation
		Label lbl_logic = new Label(anComposite_Filter, SWT.CENTER);
		lbl_logic.setText("Logic Operation: ");	
		
		//ComboBox Logic Operation
		Combo cmb_logic = new Combo(anComposite_Filter, SWT.NONE);
		cmb_logic.setItems(DslFilterFactoryImpl.eINSTANCE.createBasicCLogicOperation().EnumToStringArray().toArray(new String[0]));
		cmb_logic.select(getAnLogicOperation().getAnLogicOperation().getValue());		
		
		//Label Negative
		Label lbl_neg = new Label(anComposite_Filter, SWT.CENTER);
		lbl_neg.setText("Negative: ");
		
		//CheckBox Negative
		Button btn_neg = new Button(anComposite_Filter, SWT.CHECK | SWT.CENTER); 
		
		//Delete Group Filter
		btn_del = new Button(anComposite_Filter, SWT.PUSH | SWT.CENTER);
		btn_del.setText("Delete Group");
		
		//Execute Group
		Button btn_exec = new Button(anComposite_Filter, SWT.PUSH | SWT.CENTER);
		btn_exec.setText("Execute Group");
		
		//Label Execute
		Label lbl_exec = new Label(anComposite_Filter, SWT.CENTER);
		lbl_exec.setText("Activate");
				
		//Check Execute
		Button check_exec = new Button(anComposite_Filter, SWT.CHECK | SWT.CENTER);
		check_exec.setSelection(isExecute());		
		
		//All Listeners
		
		//Listener Classes
		cmb_classes.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					// TODO Auto-generated method stub anClassFeatures.GetEListClasses()
					if(anEClass==null || !anEClass.equals(anClassFeatures.GetEListClasses().get(cmb_classes.getSelectionIndex())))
					{
						anEClass = anClassFeatures.GetEListClasses().get(cmb_classes.getSelectionIndex()).GetEClass();
						Update_Class(cmb_classes.getSelectionIndex());
					}										
				}
		});	
		
		//Listener Negative
		
		btn_neg.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				setNegative(btn_neg.getSelection());
			}
			
		});
		
		//Listener Logic Operation 
		
		cmb_logic.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				anLogicOperation.setAnLogicOperation(EnumLogicOperation.valueOf(cmb_logic.getText()));
				System.out.println(anLogicOperation.getAnLogicOperation().toString());
			}			
			
		});
		
		check_exec.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				setExecute(check_exec.getSelection());
			}	
			
		});
		
		//End All Listeners
	}
	
	public void CreateTitleRow()
	{
		//Item Composite
		Composite titles_row = new Composite(composite, SWT.CENTER);	
		titles_row.setData(new GridData(SWT.CENTER));
		//Item Layout
		GridLayout grp_layout = new GridLayout();
		grp_layout.numColumns = 7;
		grp_layout.horizontalSpacing = 45;
		titles_row.setLayout(grp_layout);
					
		//Name of Columns Second Item
		Label lbl_vis = new Label(titles_row, SWT.CENTER);
		lbl_vis.setText("Negative");
			        
		Label lbl_attr = new Label(titles_row, SWT.CENTER);
		lbl_attr.setText("Structural Features");
			                 
		Label lbl_rule = new Label(titles_row, SWT.CENTER);
		lbl_rule.setText("Rule");  
		lbl_rule.setLayoutData(Columns_Title_Rule());
				        
		Label lbl_exp = new Label(titles_row, SWT.CENTER);
		lbl_exp.setText("Expression");
		lbl_exp.setLayoutData(Columns_Title_Exp());
				        
		Label lbl_add = new Label(titles_row, SWT.RIGHT);
		lbl_add.setText("Add");
		lbl_add.setLayoutData(new GridData(40, SWT.DEFAULT));
		
		Label lbl_del = new Label(titles_row, SWT.LEFT);
		lbl_del.setText("Del");
		lbl_del.setLayoutData(new GridData(SWT.LEFT, SWT.DEFAULT, false, false));
		
		new Label(titles_row, SWT.CENTER).setText("Exec");
	}
	
	public void AddItem()
	{
		FilterUI filter = new FilterUI(anClassFeatures);
		filter.CreateControl(composite);
		contents.add(filter);
		
		if(anEClass!=null){
		 filter.Get_cmb_Struct_Feat().setItems(GetEStructuralFeatures().toArray(new String[0]));
		 filter.Get_cmb_Rules().setItems(new String[]{});
		}
		
		filter.GetBtnAdd().addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				AddItem();
				Composite parent = ((Button)e.widget).getParent();
				composite.getChildren()[composite.getChildren().length-1].moveBelow(parent);
				item.setHeight(item.getHeight() + 50);				
			}
			
		});		

		filter.GetBtnDel().addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if(contents.size()>1){
					contents.remove(filter);					
					Composite parent = ((Button)e.widget).getParent();
					parent.dispose();
					item.setHeight(item.getHeight() - 50);
				}
			}			
		});
		
	}
	
	public String[] GetClassName()
	{
		EList<String> list_names = new BasicEList<String>(anClassFeatures.GetEListClasses().size());
		for (int i = 0; i < anClassFeatures.GetEListClasses().size(); i++) {
			list_names.add(anClassFeatures.GetEListClasses().get(i).GetEClass().getName());
		}
		return list_names.toArray(new String[0]);
	}	
	
	public EList<String> GetEStructuralFeatures() {
		// TODO: implement this method
		EList<String> list_names = new BasicEList<String>();
		EList<EStructuralFeature> list_struct_feat = anClassFeatures.GetEListClasses().get(Get_ClassIndex()).GetEStructuralFeature();;
		for (int i = 0; i < list_struct_feat.size(); i++) {
			list_names.add(list_struct_feat.get(i).getName());			
		}		
		return list_names;
	}
	
	public int Get_ClassIndex(){
		
		EList<IObtainClassFeatures> list_classes = anClassFeatures.GetEListClasses();
		for (int i = 0; i < list_classes.size(); i++) {
			if(list_classes.get(0).GetEClass().equals(anEClass))
				return i;
		}
		return -1;
	}
	
	public Control getControl(){
		return composite;
	}
	
	public void RemoveControl()
	{
		item.dispose();
		composite.dispose();
	}
	
	public Button GetBtn_Del(){
		return btn_del;
	}
	
	public GridData Columns_Title_Rule()
	{
		return new GridData(200, SWT.DEFAULT);
	}
	
	public GridData Columns_Title_Exp()
	{
		return new GridData(120, SWT.DEFAULT);
	}
	
	public void Update_Class(int index)
	{
		for (int i = 0; i < contents.size(); i++) {
			FilterContent obj_filter = contents.get(i);
			if(obj_filter instanceof FilterUI){				
				((FilterUI)obj_filter).setanClassFeatures(anClassFeatures.GetEListClasses().get(index));
				((FilterUI)obj_filter).Get_cmb_Struct_Feat().setItems(ConvertListFeaturesToString(anClassFeatures.GetEListClasses().get(index).GetEStructuralFeature()));
				((FilterUI)obj_filter).Get_cmb_Rules().setItems(new String[]{});
			}
		}
	}
	
	public String[] ConvertListFeaturesToString(EList<EStructuralFeature> list_feat)
	{
		String[] array = new String[list_feat.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = list_feat.get(i).getName();
		}
		return array;
	}
	
}

