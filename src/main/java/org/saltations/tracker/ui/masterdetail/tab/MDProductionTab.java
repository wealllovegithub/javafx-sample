package org.saltations.tracker.ui.masterdetail.tab;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.saltations.tracker.model.Production;
import org.saltations.tracker.ui.table.controller.ProductionLiveData;

@Slf4j
public class MDProductionTab extends MasterDetailTab<Production> {

	static List<String> defaultPropertyNames = Arrays.asList("first","called","last", "street1", "street2", "city","state","postalCode");
	
	public MDProductionTab(List<String> propertyNamesInOrder) {
		super("Production", Production.class, (!propertyNamesInOrder.isEmpty()) ? propertyNamesInOrder : defaultPropertyNames, new ProductionLiveData());
	}
	
}