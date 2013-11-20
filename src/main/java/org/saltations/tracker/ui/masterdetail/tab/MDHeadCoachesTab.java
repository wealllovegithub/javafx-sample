package org.saltations.tracker.ui.masterdetail.tab;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.saltations.tracker.model.HeadCoach;
import org.saltations.tracker.ui.table.controller.HeadCoachLiveData;

@Slf4j
public class MDHeadCoachesTab extends MasterDetailTab<HeadCoach> {

	static List<String> defaultPropertyNames = Arrays.asList("pid","person.first","person.called","person.last", "person.location.city","person.location.state", "person.location.postalCode");
	
	public MDHeadCoachesTab(List<String> propertyNamesInOrder) {
		super("HeadCoaches", (!propertyNamesInOrder.isEmpty()) ? propertyNamesInOrder : defaultPropertyNames, new HeadCoachLiveData());
	}
	
}
