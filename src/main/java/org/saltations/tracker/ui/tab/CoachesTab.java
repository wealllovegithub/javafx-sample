package org.saltations.tracker.ui.tab;

import java.util.List;

import javafx.scene.control.Tab;

import org.saltations.tracker.model.Coach;
import org.saltations.tracker.ui.CoachTableController;

import uk.co.it.modular.beans.BeanUtils;
import uk.co.it.modular.beans.TypeProperty;

import com.panemu.tiwulfx.table.CheckBoxColumn;
import com.panemu.tiwulfx.table.NumberColumn;
import com.panemu.tiwulfx.table.TableControl;
import com.panemu.tiwulfx.table.TextColumn;

public class CoachesTab extends Tab {

	public CoachesTab() {
		super("Coaches");
		
		/*
		 * Configure the Table 
		 */
		
		TableControl<Coach> participantTable = new  TableControl<Coach>();
		participantTable.setController(new CoachTableController());
		participantTable.setRecordClass(Coach.class);
		
		participantTable.autosize();

		List<TypeProperty> properties = BeanUtils.propertyList(Coach.class);
		
		for (TypeProperty property : properties) {
		
			if (property.isString())
			{
				participantTable.addColumn(new TextColumn<Coach>(property.getName()));
			}
			else if (property.isBoolean() )
			{
				participantTable.addColumn(new CheckBoxColumn<Coach>(property.getName()));
			}
			else if (property.isInteger() )
			{
				participantTable.addColumn(new NumberColumn<Coach, Integer>(property.getName(), Integer.class));
			}
		}
		
		super.setContent(participantTable);
	}
	

	
	
}
