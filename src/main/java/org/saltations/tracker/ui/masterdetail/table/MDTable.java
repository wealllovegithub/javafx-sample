/**
 * 
 */
package org.saltations.tracker.ui.masterdetail.table;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.saltations.tracker.infra.PropertyEval;
import org.saltations.tracker.model.Participant;
import org.saltations.tracker.ui.table.controller.LiveData;
import org.saltations.tracker.ui.table.controller.MDTableController;

import com.google.common.collect.Sets;
import com.google.common.eventbus.EventBus;
import com.panemu.tiwulfx.table.CheckBoxColumn;
import com.panemu.tiwulfx.table.NumberColumn;
import com.panemu.tiwulfx.table.TableControl;
import com.panemu.tiwulfx.table.TextColumn;

/**
 * @author jmochel
 *
 */

@Data
@Slf4j
public class MDTable<T> extends TableControl<T> {

	private final EventBus bus;
	
	/**
	 * @param recordClass
	 */
	
	public MDTable(Class<T> recordClass, EventBus bus, List<String> columnPropertyNames, LiveData<T> data) {
		
		super(recordClass);
		this.bus = bus;
		
		bus.register(this);
		
		try {
			/*
			 * Check that all the property names we are looking for are also in the list of known properties.  
			 */
			
			@SuppressWarnings("unchecked")
			Set<String> beanProperties = (Set<String>) BeanUtilsBean.getInstance().describe(data.exemplar()).keySet();
			
			Set<String> unknownProperties = Sets.difference(Sets.newHashSet(columnPropertyNames), beanProperties);
			
			if ( !unknownProperties.isEmpty() )
			{
				log.error(MessageFormat.format("Some properties that were requested to be displayed have not been found : {0}", unknownProperties));
				
				throw new IllegalArgumentException();
			}
			

			/*
			 * For each requested property, create a Label and a Entry point
			 */
			
			
			for (String propertyName : columnPropertyNames) {

				PropertyDescriptor descriptor = BeanUtilsBean2.getInstance().getPropertyUtils().getPropertyDescriptor(data.exemplar(), propertyName);

				PropertyEval eval = new PropertyEval(descriptor.getPropertyType());
				
				/*
				 * Determine the type of the Control.
				 */
				
				if (!eval.isInterface())
				{
					if (eval.isString())
					{
						super.addColumn(new TextColumn<T>(descriptor.getName()));
					}
					else if (eval.isBoolean() )
					{
						CheckBoxColumn<T> col = new CheckBoxColumn<T>(descriptor.getName());
						col.setRequired(true);
						col.setLabel("Y", "N", "");
						
						super.addColumn(col);
					}
					else if (eval.isInteger() )
					{
						super.addColumn(new NumberColumn<T, Integer>(descriptor.getName(), Integer.class));
					}
				}
			}
		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MDTableController<T> tableController = new MDTableController<T>(bus, data);

		super.setRecordClass((Class<T>) recordClass);
		super.setController(tableController);
		super.autosize();
	}
	
}
