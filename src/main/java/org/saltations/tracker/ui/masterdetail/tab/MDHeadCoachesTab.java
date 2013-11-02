package org.saltations.tracker.ui.masterdetail.tab;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.saltations.tracker.model.HeadCoach;
import org.saltations.tracker.ui.table.controller.HeadCoachLiveData;

@Slf4j
public class MDHeadCoachesTab extends MasterDetailTab<HeadCoach> {

	static List<String> defaultPropertyNames = Arrays.asList("first","called","last", "street1", "street2", "city","state","postalCode");
	
	public MDHeadCoachesTab(List<String> propertyNamesInOrder) {
		super("HeadCoaches", (!propertyNamesInOrder.isEmpty()) ? propertyNamesInOrder : defaultPropertyNames, new HeadCoachLiveData());
	}
	
}
