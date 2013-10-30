package org.saltations.tracker.ui.tab;

import java.util.List;

import javafx.scene.control.Tab;

import org.saltations.tracker.model.HeadCoach;
import org.saltations.tracker.ui.HeadCoachTableController;

import uk.co.it.modular.beans.BeanUtils;
import uk.co.it.modular.beans.TypeProperty;

import com.panemu.tiwulfx.table.CheckBoxColumn;
import com.panemu.tiwulfx.table.NumberColumn;
import com.panemu.tiwulfx.table.TableControl;
import com.panemu.tiwulfx.table.TextColumn;

public class HeadCoachesTab extends Tab {

	public HeadCoachesTab() {
		super("Head Coaches");
		
		/*
		 * Configure the Table 
		 */
		
		TableControl<HeadCoach> participantTable = new  TableControl<HeadCoach>();
		participantTable.setController(new HeadCoachTableController());
		participantTable.setRecordClass(HeadCoach.class);
		
		participantTable.autosize();

		List<TypeProperty> properties = BeanUtils.propertyList(HeadCoach.class);
		
		for (TypeProperty property : properties) {
		
			if (property.isString())
			{
				participantTable.addColumn(new TextColumn<HeadCoach>(property.getName()));
			}
			else if (property.isBoolean() )
			{
				participantTable.addColumn(new CheckBoxColumn<HeadCoach>(property.getName()));
			}
			else if (property.isInteger() )
			{
				participantTable.addColumn(new NumberColumn<HeadCoach, Integer>(property.getName(), Integer.class));
			}
		}
		
		super.setContent(participantTable);
	}
	

	
	
}
