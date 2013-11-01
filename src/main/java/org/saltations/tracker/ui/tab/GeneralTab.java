/**
 * 
 */
package org.saltations.tracker.ui.tab;

import java.text.MessageFormat;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import lombok.extern.slf4j.Slf4j;

import org.saltations.tracker.model.Participant;
import org.saltations.tracker.model.Person;

import uk.co.it.modular.beans.BeanUtils;
import uk.co.it.modular.beans.TypeProperty;

import com.google.common.eventbus.EventBus;
import com.panemu.tiwulfx.form.Form;
import com.panemu.tiwulfx.table.CheckBoxColumn;
import com.panemu.tiwulfx.table.NumberColumn;
import com.panemu.tiwulfx.table.TableControl;
import com.panemu.tiwulfx.table.TableController;
import com.panemu.tiwulfx.table.TextColumn;

/**
 * @author jmochel
 *
 */
@Slf4j
public class GeneralTab<T>  extends Tab {
	
	Class<? extends T> clazz;
	
	List<String> propertyNames;
	
	TableControl<T> subjectTable;
	
	Form<T>	form;
	
	EventBus bus; 
	
	 private EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
		 
	        @Override
	        public void handle(ActionEvent evt) {

	        	log.info(evt.getSource().toString());
	
	        	if (evt.getSource() == subjectTable  ) {
	        		
		        	form.setRecord(subjectTable.getSelectedItem());
		        	form.setMode(Form.Mode.EDIT);
	        		
	        	}
	        	
//	            if (t.getSource() == btnSave && personForm.validate()) {
//	                Person p = personForm.getRecord();
//	                if (p.getId() == null) {
//	                    p = daoPerson.insert(p);
//	                } else {
//	                    p = daoPerson.update(p);
//	                    p = daoPerson.initRelationship(p, Person_.insurance.getName());
//	                }
//	                personForm.setRecord(p);
//	                personForm.setMode(Form.Mode.READ);
//	            } else if (t.getSource() == btnEdit) {
//	                personForm.setMode(Form.Mode.EDIT);
//	            } else if (t.getSource() == btnAdd) {
//	                personForm.setRecord(new Person());
//	                personForm.setMode(Form.Mode.INSERT);
//	            } else if (t.getSource() == btnReload) {
//	                personForm.setValueObject(person);
//	                personForm.setMode(Form.Mode.READ);
//	                personForm.validate();//ensure to remove exclamation mark next to the invalid fields
//	            }
	        }
	    };
	
	public GeneralTab(String name, Class<? extends T> clazz, List<String> propertyNames, TableController<T> tableController) {
	
		super(name);
		
		this.clazz = clazz;
		
		this.propertyNames = propertyNames;
		
		bus = new EventBus();
		
		HBox hbox = new HBox();
		
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
					CheckBoxColumn<T> col = new CheckBoxColumn<T>(property.getName());
					col.setLabel("Y", "N", "N");
					
					subjectTable.addColumn(col);
				}
				else if (property.isInteger() )
				{
					subjectTable.addColumn(new NumberColumn<T, Integer>(property.getName(), Integer.class));
				}
			}
		}
		
		hbox.getChildren().add(subjectTable);

		form = new Form<T>();
		
		hbox.getChildren().add(form);
		form.autosize();
		form.setVisible(true);
		
		super.setContent(hbox);
	}
	
	
	
}
