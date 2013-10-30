package org.saltations.tracker.ui.tab;

import java.util.List;

import javafx.scene.control.Tab;

import org.saltations.tracker.model.Participant;
import org.saltations.tracker.ui.ParticipantTableController;

import uk.co.it.modular.beans.BeanUtils;
import uk.co.it.modular.beans.TypeProperty;

import com.panemu.tiwulfx.table.CheckBoxColumn;
import com.panemu.tiwulfx.table.NumberColumn;
import com.panemu.tiwulfx.table.TableControl;
import com.panemu.tiwulfx.table.TextColumn;

public class ParticipantTab extends Tab {

	public ParticipantTab() {
		super("Participants");
		
		/*
		 * Configure the Participant Table 
		 */
		
		TableControl<Participant> participantTable = new  TableControl<Participant>();
		participantTable.setController(new ParticipantTableController());
		participantTable.setRecordClass(Participant.class);
		
		participantTable.autosize();

		List<TypeProperty> properties = BeanUtils.propertyList(Participant.class);
		
		for (TypeProperty property : properties) {
		
			if (property.isString())
			{
				participantTable.addColumn(new TextColumn<Participant>(property.getName()));
			}
			else if (property.isBoolean() )
			{
				participantTable.addColumn(new CheckBoxColumn<Participant>(property.getName()));
			}
			else if (property.isInteger() )
			{
				participantTable.addColumn(new NumberColumn<Participant, Integer>(property.getName(), Integer.class));
			}
		}
		
		super.setContent(participantTable);
	}
	

	
	
}
