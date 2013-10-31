package org.saltations.tracker.ui.tab;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.saltations.tracker.model.Production;
import org.saltations.tracker.ui.ProductionTableController;

@Slf4j
public class ProductionTab extends GeneralTab<Production> {

	static List<String> defaultPropertyNames = Arrays.asList("first","called","last", "street1", "street2", "city","state","postalCode");
	
	public ProductionTab(List<String> propertyNamesInOrder) {
		super("Production", Production.class, (!propertyNamesInOrder.isEmpty()) ? propertyNamesInOrder : defaultPropertyNames, new ProductionTableController());
	}
	
}
