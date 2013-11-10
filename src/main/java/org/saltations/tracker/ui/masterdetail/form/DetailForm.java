package org.saltations.tracker.ui.masterdetail.form;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.saltations.tracker.infra.PropertyEval;
import org.saltations.tracker.ui.masterdetail.MDRequestEditEvt;
import org.saltations.tracker.ui.table.controller.LiveData;
import org.tbee.javafx.scene.layout.MigPane;

import com.google.common.collect.Sets;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.panemu.tiwulfx.form.CheckBoxControl;
import com.panemu.tiwulfx.form.Form;
import com.panemu.tiwulfx.form.NumberControl;
import com.panemu.tiwulfx.form.TextControl;
import com.panemu.tiwulfx.table.CheckBoxColumn;
import com.panemu.tiwulfx.table.NumberColumn;

/**
 * The {@link DetailForm} is responsible for displaying, editing and saving the contents for a POJO of type <T>    
 * 
 * @author jmochel
 *
 * @param <T> Type of the POJO to be edited.
 */
@Slf4j
public class DetailForm<T> extends MigPane {

	private final EventBus bus;
	
	private T record;

	public DetailForm(EventBus bus, List<String> toBeDisplayed, LiveData<T> data)
	{
		super();
		
		this.bus = bus;
		this.record = data.exemplar();
		
		try {
			/*
			 * Check that all the property names we are looking for are also in the list of known properties.  
			 */
			
			@SuppressWarnings("unchecked")
			Set<String> beanProperties = (Set<String>) BeanUtilsBean.getInstance().describe(data.exemplar()).keySet();
			
			Set<String> unknownProperties = Sets.difference(Sets.newHashSet(toBeDisplayed), beanProperties);
			
			if ( !unknownProperties.isEmpty() )
			{
				log.error(MessageFormat.format("Some properties that were requested to be displayed have not been found : {0}", unknownProperties));
				
				throw new IllegalArgumentException();
			}
			

			/*
			 * For each requested property, create a Label and a Entry point
			 */
			
			
			for (String propertyName : toBeDisplayed) {

				PropertyDescriptor descriptor = BeanUtilsBean2.getInstance().getPropertyUtils().getPropertyDescriptor(data.exemplar(), propertyName);

				PropertyEval eval = new PropertyEval(descriptor.getPropertyType());
				
				/*
				 * Determine the type of the Control.
				 */
				
				if (!eval.isInterface())
				{
					if (eval.isString())
					{
						Label label = new Label(descriptor.getDisplayName());
						
						TextControl control = new TextControl();
						control.setPropertyName(propertyName);
						
						super.add(label);
						super.add(control, "wrap");
					}
					else if (eval.isBoolean() )
					{
						Label label = new Label(descriptor.getDisplayName());
						
						CheckBoxControl control = new CheckBoxControl();
						control.setPropertyName(propertyName);
						
						super.add(label);
						super.add(control, "wrap");
					}
					else if (eval.isInteger() )
					{
						Label label = new Label(descriptor.getDisplayName());
						
						NumberControl<Integer> control = new NumberControl<Integer>();
						control.setPropertyName(propertyName);
						
						super.add(label);
						super.add(control, "wrap");
					}
				}
			}
		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
