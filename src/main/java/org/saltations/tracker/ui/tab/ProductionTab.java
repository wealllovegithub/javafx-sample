package org.saltations.tracker.ui.tab;

import java.util.List;

import javafx.scene.control.Tab;

import org.saltations.tracker.model.Production;
import org.saltations.tracker.ui.ProductionTableController;

import uk.co.it.modular.beans.BeanUtils;
import uk.co.it.modular.beans.TypeProperty;

import com.panemu.tiwulfx.table.CheckBoxColumn;
import com.panemu.tiwulfx.table.NumberColumn;
import com.panemu.tiwulfx.table.TableControl;
import com.panemu.tiwulfx.table.TextColumn;

public class ProductionTab extends Tab {

	public ProductionTab() {
		super("Production");
		
		/*
		 * Configure the Table 
		 */
		
		TableControl<Production> participantTable = new  TableControl<Production>();
		participantTable.setController(new ProductionTableController());
		participantTable.setRecordClass(Production.class);
		
		participantTable.autosize();

		List<TypeProperty> properties = BeanUtils.propertyList(Production.class);
		
		for (TypeProperty property : properties) {
		
			if (property.isString())
			{
				participantTable.addColumn(new TextColumn<Production>(property.getName()));
			}
			else if (property.isBoolean() )
			{
				participantTable.addColumn(new CheckBoxColumn<Production>(property.getName()));
			}
			else if (property.isInteger() )
			{
				participantTable.addColumn(new NumberColumn<Production, Integer>(property.getName(), Integer.class));
			}
		}
		
		super.setContent(participantTable);
	}
	

	
	
}
