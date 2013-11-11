package org.saltations.tracker.ui.masterdetail.form;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.saltations.tracker.infra.PropertyEval;
import org.saltations.tracker.ui.masterdetail.MDRequestEditEvt;
import org.saltations.tracker.ui.masterdetail.MDTableRefreshEvt;
import org.saltations.tracker.ui.table.controller.LiveData;
import org.saltations.tracker.ui.table.controller.MDTableController;
import org.tbee.javafx.scene.layout.MigPane;
import org.testng.collections.Lists;

import com.google.common.collect.Sets;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.panemu.tiwulfx.form.BaseControl;
import com.panemu.tiwulfx.form.CheckBoxControl;
import com.panemu.tiwulfx.form.NumberControl;
import com.panemu.tiwulfx.form.TextControl;

/**
 * The {@link AutoDetailForm} is responsible for displaying, editing and saving the contents for a POJO of type <T>    
 * 
 * @author jmochel
 *
 * @param <T> Type of the POJO to be edited.
 */
@Slf4j
public class AutoDetailForm<T> extends MigPane {

	/**
	 * Event Bus for communicating between the Table and Form
	 */
	
	private final EventBus bus;

	/*
	 * The record currently being referenced by the Form
	 */
	
	private T record;
	
	private Button saveButton;
	
	private Button cancelButton;
	
	/**
	 * A list of all the input controls
	 */
	
    private List<BaseControl> inputControls = Lists.newArrayList();
    
    
    private MDTableController<T> controller;
    
    /**
     * Constructor for the form.
     *  
     * @param bus
     * @param toBeDisplayed
     * @param data
     */
    
	public AutoDetailForm(EventBus bus, List<String> toBeDisplayed, LiveData<T> data, MDTableController<T> controller)
	{
		super();
		
		this.bus = bus;
		this.record = data.exemplar();
		
		this.saveButton = new Button("Save");
		
		this.saveButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        save();
		    }
		});
		
		this.cancelButton = new Button("Cancel");
		
		this.cancelButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        cancel();
		    }
		});
		
		super.add(saveButton);
		super.add(cancelButton, "wrap");
		
		this.controller = controller;
		
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

						inputControls.add(control);
						
						super.add(label);
						super.add(control, "wrap");
					}
					else if (eval.isBoolean() )
					{
						Label label = new Label(descriptor.getDisplayName());
						
						CheckBoxControl control = new CheckBoxControl();
						control.setPropertyName(propertyName);
						
						inputControls.add(control);
						
						super.add(label);
						super.add(control, "wrap");
					}
					else if (eval.isInteger() )
					{
						Label label = new Label(descriptor.getDisplayName());
						
						NumberControl<Integer> control = new NumberControl<Integer>();
						control.setPropertyName(propertyName);
						
						inputControls.add(control);
						
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

		/*
		 * Tell the bus that we handle the configured events
		 */
		
		bus.register(this);
		
		autosize();
		setVisible(true);
	}

	protected void save() {
		
		for (BaseControl control : this.inputControls) {
			control.pullValue(record);
		}
		
		this.controller.update(Lists.newArrayList(record));
		
		this.bus.post(new MDTableRefreshEvt<>(record));
	}
	
	protected void cancel() {
		for (BaseControl control : this.inputControls) {
			control.pushValue(record);
		}
	}

	/**
	 * The Form handles any changes in the data resulting from the table. Insertions, deletions and updates.
	 * 
	 * @param evt
	 */
	
	@Subscribe
	public void changeRecord(MDRequestEditEvt<T> evt)
	{
		this.record = evt.getSubject();
		
		for (BaseControl control : this.inputControls) {
			control.pushValue(record);
		}
	}
	
}
