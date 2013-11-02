package org.saltations.tracker.ui.masterdetail.form;

import java.util.List;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import org.saltations.tracker.ui.masterdetail.MDRequestEditEvt;

import uk.co.it.modular.beans.BeanProperty;
import uk.co.it.modular.beans.BeanUtils;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * The {@link DetailForm} is responsible for displaying, editing and saving the contents for a POJO of type <T>    
 * 
 * @author jmochel
 *
 * @param <T> Type of the POJO to be edited.
 */

public class DetailForm<T> extends GridPane {

	private final EventBus bus;
	
	private T record;

	public DetailForm(EventBus bus, T exemplar)
	{
		super();
		
		this.bus = bus;
		this.record = exemplar;

		/*
		 * Walk through the properties and create the form.
		 */
		
		List<BeanProperty> properties = BeanUtils.propertyList(exemplar);
		
		for (BeanProperty beanProperty : properties) {
			
			/*
			 * For each property pick an appropriate control, lable and tooltip.  
			 */
			
			if (beanProperty.isString())
			{
				TextField field = new TextField(beanProperty.getName());
				field.setText("example");
				field.setEditable(true);
				getChildren().add(field);
			}
			
//			    private CheckBoxControl chkAlive;
//			    private ComboBoxControl<String> cmbBirthPlace;
//			    private ChoiceBoxControl<Character> cmbGender;
//			    private LookupControl<Insurance> lkupInsurance;
//			    private DateControl txtBirthDate;
//			    private TextControl txtEmail;
//			    private NumberControl<Integer> txtVersion;
//			    private Form<Person> personForm;
			
		}
		
		bus.register(this);
		
		autosize();
		setVisible(true);
	}

	
	@Subscribe
	public void changeRecord(MDRequestEditEvt<T> evt)
	{
//		super.setRecord(evt.getSubject());
	}
}
