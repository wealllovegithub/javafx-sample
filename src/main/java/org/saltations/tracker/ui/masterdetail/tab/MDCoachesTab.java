package org.saltations.tracker.ui.masterdetail.tab;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.saltations.tracker.model.Coach;
import org.saltations.tracker.ui.table.controller.CoachLiveData;

@Slf4j
public class MDCoachesTab extends MasterDetailTab<Coach> {

	static List<String> defaultPropertyNames = Arrays.asList("first","called","last", "street1", "street2", "city","state","postalCode");
	
	public MDCoachesTab(List<String> propertyNamesInOrder) {
		super("Coaches", (!propertyNamesInOrder.isEmpty()) ? propertyNamesInOrder : defaultPropertyNames, new CoachLiveData());
	}
	
}
