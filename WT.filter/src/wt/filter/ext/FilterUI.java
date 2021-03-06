package wt.filter.ext;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.mondo.modular.filterui.ext.IObtainClassFeatures;

import dslFilter.EnumStringOperation;
import dslFilter.EnumNumberOperation;
import dslFilter.impl.CNumberOperationImpl;
import dslFilter.impl.CStringOperationImpl;
import dslFilter.impl.CStructuralEObjectImpl;
import dslFilter.impl.FilterImpl;

public class FilterUI extends FilterImpl{
	
	protected Composite composite;
	private Button btn_add;
	private Button btn_del;
	private Combo cmb_attr;
	private Combo cmb_rules;
	private IObtainClassFeatures anClassFeatures;
	
	public FilterUI(IObtainClassFeatures anClassFeatures) {
		super();
		this.composite = null;	
		this.anClassFeatures = anClassFeatures;
	}
	
	public void CreateControl(Composite parent){
		
		//Initialize Filter
		InitFilter();
		//Item Group
		composite = new Composite(parent, SWT.CENTER);
		GridLayout grp_layout = new GridLayout ();
		grp_layout.marginLeft = 20;
		grp_layout.numColumns = 7;
		grp_layout.horizontalSpacing = 50;
		composite.setLayout(grp_layout);
						
		//Row for Rule Personalization
		//CheckBox Negative
		Button check_negative = new Button(composite, SWT.CHECK | SWT.CENTER);
				
		//ComboBox Attributes
		cmb_attr = new Combo(composite, SWT.READ_ONLY);
		cmb_attr.setLayoutData(Columms_2_3_4());	
		
		//ComboRules
		cmb_rules = new Combo(composite, SWT.READ_ONLY);
		cmb_rules.setLayoutData(Columms_2_3_4());
							
		//TextBox
		Text txt_exp = new Text(composite, SWT.BORDER | SWT.SINGLE | SWT.HORIZONTAL);
		txt_exp.setLayoutData(Columms_2_3_4());
								
		//Button Add Rule
		btn_add = new Button(composite, SWT.PUSH);
		btn_add.setText("+");
		btn_add.setLayoutData(new GridData(22, SWT.DEFAULT));
				
		//Button Delete Rule
		btn_del = new Button(composite, SWT.PUSH);
		btn_del.setText("-");
		btn_del.setLayoutData(Column_Del());
						
		//Button Delete Rule
		Button check_exec = new Button(composite, SWT.CHECK | SWT.CENTER);	
		check_exec.setSelection(isExecute());
		
		/*
		 * Listeners
		 * */
		
		check_exec.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				setExecute(check_exec.getSelection());				
			}		
			
		});		
				
		//Listener Structural Features
		
		cmb_attr.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if(anEStructuralFeature == null || 
						!anEStructuralFeature.equals(anClassFeatures.GetEStructuralFeature().get(cmb_attr.getSelectionIndex())) ||
						cmb_rules.getItems().length==0)
					{
					anEStructuralFeature = anClassFeatures.GetEStructuralFeature().get(cmb_attr.getSelectionIndex());
					cmb_rules.setItems(GetRules().toArray(new String[0]));
					if(anRule instanceof CStringOperationImpl){					
						cmb_rules.select(EnumStringOperation.get(((CStringOperationImpl)anRule).getAnStringOperation().getLiteral()).getValue());
					}	
					else if(anRule instanceof CStructuralEObjectImpl){
						cmb_rules.select(EnumNumberOperation.get(((CStructuralEObjectImpl)anRule).getAnNumberOperation().getLiteral()).getValue());
					}
				}
			}		
			
		});
		
		//Listener Rule
		cmb_rules.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if(anRule instanceof CStringOperationImpl){
					((CStringOperationImpl) anRule).setAnStringOperation(EnumStringOperation.get(cmb_rules.getText()));
				}
				else if(anRule instanceof CNumberOperationImpl){
					((CNumberOperationImpl) anRule).setAnNumberOperation(EnumNumberOperation.get(cmb_rules.getText()));
				}
				else if(anRule instanceof CStructuralEObjectImpl){
					((CStructuralEObjectImpl) anRule).setAnNumberOperation(EnumNumberOperation.get(cmb_rules.getText()));
				}					
			}		
			
		});
		
		//Listener Expression
		txt_exp.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				anRule.setAnExp(txt_exp.getText());
			}
		});	
		
		check_negative.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				setNegative(check_negative.getSelection());
			}			
			
		});
		
		/*
		 * END Listeners
		 * */
		
	}	
	
	public Control getControl(){
		return composite;
	}
	
	public Button GetBtnAdd()
	{
		return btn_add;
	}
	
	public Button GetBtnDel()
	{
		return btn_del;
	}
	
	public Combo Get_cmb_Struct_Feat()
	{
		return cmb_attr;		
	}
	
	public Combo Get_cmb_Rules()
	{
		return cmb_rules;		
	}	
	
	public IObtainClassFeatures getanClassFeatures(){
		return anClassFeatures;
	}
	
	public void setanClassFeatures(IObtainClassFeatures anClassFeatures) {
		this.anClassFeatures = anClassFeatures;
	}
	
	private GridData Columms_2_3_4()
	{
		return new GridData(130, SWT.DEFAULT);
	}
	
	private GridData  Column_Del() {
		// TODO Auto-generated method stub
		GridData  grd = new GridData(22,SWT.DEFAULT);
		return grd;
	}

}

