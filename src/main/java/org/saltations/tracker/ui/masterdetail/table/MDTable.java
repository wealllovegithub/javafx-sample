/**
 * 
 */
package org.saltations.tracker.ui.masterdetail.table;

import java.text.MessageFormat;
import java.util.List;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.saltations.tracker.model.Participant;
import org.saltations.tracker.ui.table.controller.LiveData;
import org.saltations.tracker.ui.table.controller.MDTableController;

import uk.co.it.modular.beans.BeanUtils;
import uk.co.it.modular.beans.TypeProperty;

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
		
		/*
		 * Configure the columns to be display in the table.
		 */

		List<TypeProperty> properties = BeanUtils.propertyList((Class<T>) recordClass);
		
		for (String propertyName : columnPropertyNames) {
		
			TypeProperty property  = BeanUtils.get((Class<T>) recordClass, propertyName);
			
			if (property == null )
			{
				log.error(MessageFormat.format("Unable to find the requested property {0} in the {1} class. We are ignoring this property in generating the table", propertyName, Participant.class.getSimpleName()));
			}
			else {
				
				if (property.isString())
				{
					super.addColumn(new TextColumn<T>(property.getName()));
				}
				else if (property.isBoolean() )
				{
					CheckBoxColumn<T> col = new CheckBoxColumn<T>(property.getName());
					col.setRequired(true);
					col.setLabel("Y", "N", "");
					
					super.addColumn(col);
				}
				else if (property.isInteger() )
				{
					super.addColumn(new NumberColumn<T, Integer>(property.getName(), Integer.class));
				}
			}
		}

		MDTableController<T> tableController = new MDTableController<T>(bus, data);

		super.setRecordClass((Class<T>) recordClass);
		super.setController(tableController);
		super.autosize();
	}
	
}
