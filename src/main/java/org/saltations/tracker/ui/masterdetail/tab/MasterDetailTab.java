/**
 * 
 */
package org.saltations.tracker.ui.masterdetail.tab;

import java.util.List;

import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import lombok.extern.slf4j.Slf4j;

import org.saltations.tracker.ui.masterdetail.form.DetailForm;
import org.saltations.tracker.ui.masterdetail.table.MDTable;
import org.saltations.tracker.ui.table.controller.LiveData;
import org.saltations.tracker.ui.table.controller.MDTableController;

import com.google.common.eventbus.EventBus;
import com.panemu.tiwulfx.table.TableControl;

/**
 * 
 * A general Master Detail Tab with an event bus connecting them.
 * 
 * @author jmochel
 *
 */
@Slf4j
public class MasterDetailTab<T>  extends Tab {
	
	/**
	 * Name of the properties to be displayed in the table columns
	 */

	List<String> propertyNames;
	
	/**
	 * The master table
	 */
	
	TableControl<T> table;
	
	/**
	 * The detailed view of the POJO 
	 */
	
	DetailForm<T>	detail;
	
	/**
	 * Event Bus for managing communication among the parts 
	 */
	
	final private EventBus bus; 

	/**
	 * 
	 * @param name Name of the table
	 * 
	 * @param clazz
	 * @param columnPropertyNames
	 * @param data
	 */
	
	public MasterDetailTab(String name, List<String> columnPropertyNames, LiveData<T> data) {
	
		super(name);
		
		this.bus = new EventBus();

		/*
		 * Construct the internal box for the Table and Detail
		 */
		HBox hbox = new HBox();
		
		/*
		 * Configure the subject Table 
		 */

		table = new  MDTable<T>((Class<T>) data.exemplar().getClass(), bus, columnPropertyNames, data);
		
		/*
		 * Construct the detail form for editing.
		 */
		
		detail = new DetailForm<T>(bus, columnPropertyNames, data);
		
		/*
		 * Throw it all into the 
		 */
		
		hbox.getChildren().add(table);
		hbox.getChildren().add(detail);
		
		super.setContent(hbox);
	}
	
}
