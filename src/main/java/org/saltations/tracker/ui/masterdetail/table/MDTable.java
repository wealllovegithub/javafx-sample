/**
 * 
 */
package org.saltations.tracker.ui.masterdetail.table;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.Label;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.beanutils.BeanUtilsBean2;
import org.apache.commons.beanutils.PropertyUtils;
import org.saltations.controller.Context;
import org.saltations.tracker.infra.PropertyEval;
import org.saltations.tracker.ui.masterdetail.MDTableRefreshEvt;
import org.saltations.tracker.ui.table.controller.LiveData;
import org.saltations.tracker.ui.table.controller.MDTableController;
import org.testng.collections.Maps;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.panemu.tiwulfx.table.CheckBoxColumn;
import com.panemu.tiwulfx.table.NumberColumn;
import com.panemu.tiwulfx.table.TableControl;
import com.panemu.tiwulfx.table.TextColumn;
import com.typesafe.config.Config;

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
		
		super.setCache(false);
		
		bus.register(this);
		
		try {
			
			/*
			 * Check that all the property names we are looking for are also in the list of known properties.  
			 */
			
			for (String propertyName : columnPropertyNames) {
				Object obj = PropertyUtils.getProperty(data.exemplar(), propertyName);
			}
			
			/*
			 * Get the config for the properties to name and desc text. 
			 */
			
			Config conf = Context.getConf().atPath("names");
			
			/*
			 * For each requested property, create a Label and a Entry point
			 */

			for (String propertyName : columnPropertyNames) {

				PropertyDescriptor descriptor = BeanUtilsBean2.getInstance().getPropertyUtils().getPropertyDescriptor(data.exemplar(), propertyName);

				if (conf.hasPath(propertyName))
				{
					String displayName = conf.atPath(propertyName).getString("name");
					String description = conf.atPath(propertyName).getString("desc");
					
					descriptor.setDisplayName(displayName);
					descriptor.setShortDescription(description);
				}
				
				PropertyEval eval = new PropertyEval(descriptor.getPropertyType());
				
				/*
				 * Determine the type of the Control.
				 */
				
				if (!eval.isInterface())
				{
					if (eval.isString())
					{
						TextColumn col = new TextColumn<T>(propertyName);
						col.setText(descriptor.getDisplayName());
						
						super.addColumn(col);
					}
					else if (eval.isBoolean() )
					{
						CheckBoxColumn<T> col = new CheckBoxColumn<T>(propertyName);
						col.setText(descriptor.getDisplayName());
						col.setRequired(true);
						col.setLabel("Y", "N", "");
						
						super.addColumn(col);
					}
					else if (eval.isInteger() )
					{
						NumberColumn<T, Integer> col = new NumberColumn<T, Integer>(propertyName, Integer.class);
						col.setText(descriptor.getDisplayName());
						
						super.addColumn();
					}
				}
			}
			
			
		    for (Node n: super.getTableView().lookupAll(".column-header > .label")) {
		        if (n instanceof Label) {
		          Label label = (Label) n;
		          label.setRotate(270);
		        }
		      }
			
		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MDTableController<T> tableController = new MDTableController<T>(bus, data);

		super.setRecordClass((Class<T>) recordClass);
		super.setController(tableController);
		super.autosize();
	}

	/**
	 * @param data
	 * @return
	 */
	private Map<String, Field> extractFieldMap(Class<?> clazz) {
		Map<String, Field> fieldMap = Maps.newHashMap();

		/*
		 * 
		 */
		
		/*
		 * Walk up the superclass tree collect fields 
		 */
		
		Class<?> currClass = clazz;
		
		while (!currClass.getSimpleName().equals("Object"))
		{
			Field[] fields = currClass.getDeclaredFields();

			for (Field field : fields) {
				
				log.info(MessageFormat.format("Field {0} from class {1} is added to the field map", field.getName(), currClass.getSimpleName()));
				
				fieldMap.put(field.getName(), field);
			}
			
			currClass = currClass.getSuperclass();
		}
		return fieldMap;
	}

	
	/**
	 * Refreshes the table when the data has changed.
	 * 
	 * @param evt
	 */
	
	@Subscribe
	public void refreshTable(MDTableRefreshEvt<T> evt)
	{
	   super.reload();
		
       boolean visible = super.getTableView().getColumns().get(0).isVisible();
       super.getTableView().getColumns().get(0).setVisible(false);
       super.getTableView().getColumns().get(0).setVisible(true);
       super.getTableView().getColumns().get(0).setVisible(visible);
	}

	
	
	
}
