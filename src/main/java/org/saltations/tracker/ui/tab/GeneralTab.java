/**
 * 
 */
package org.saltations.tracker.ui.tab;

import java.text.MessageFormat;
import java.util.List;

import org.saltations.tracker.model.Participant;

import uk.co.it.modular.beans.BeanUtils;
import uk.co.it.modular.beans.TypeProperty;

import com.panemu.tiwulfx.table.CheckBoxColumn;
import com.panemu.tiwulfx.table.NumberColumn;
import com.panemu.tiwulfx.table.TableControl;
import com.panemu.tiwulfx.table.TableController;
import com.panemu.tiwulfx.table.TextColumn;

import javafx.scene.control.Tab;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jmochel
 *
 */
@Slf4j
public class GeneralTab<T>  extends Tab {
	
	Class<? extends T> clazz;
	
	List<String> propertyNames;
	
	TableControl<T> subjectTable; 
	
	public GeneralTab(String name, Class<? extends T> clazz, List<String> propertyNames, TableController<T> tableController) {
	
		super(name);
		
		this.clazz = clazz;
		
		this.propertyNames = propertyNames;

		/*
		 * Configure the subject Table 
		 */
		
		subjectTable = new  TableControl<T>();
		subjectTable.setController(tableController);
		subjectTable.setRecordClass((Class<T>) clazz);
		subjectTable.autosize();

		/*
		 * Configure the displayed columns
		 */
		

		List<TypeProperty> properties = BeanUtils.propertyList((Class<T>) clazz);
		
		for (String propertyName : propertyNames) {
		
			TypeProperty property  = BeanUtils.get((Class<T>) clazz, propertyName);
			
			if (property == null )
			{
				log.error(MessageFormat.format("Unable to find the requested property {0} in the {1} class. We are ignoring this property in generating the table", propertyName, Participant.class.getSimpleName()));
			}
			else {
				if (property.isString())
				{
					subjectTable.addColumn(new TextColumn<T>(property.getName()));
				}
				else if (property.isBoolean() )
				{
					CheckBoxColumn<T> checkBox = new CheckBoxColumn<T>(property.getName());
					
					subjectTable.addColumn(checkBox);
				}
				else if (property.isInteger() )
				{
					subjectTable.addColumn(new NumberColumn<T, Integer>(property.getName(), Integer.class));
				}
			}
			
		}
		
		super.setContent(subjectTable);
	}
	
}
