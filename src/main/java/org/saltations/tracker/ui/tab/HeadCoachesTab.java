package org.saltations.tracker.ui.tab;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.saltations.tracker.model.HeadCoach;
import org.saltations.tracker.ui.controller.HeadCoachTableController;

@Slf4j
public class HeadCoachesTab extends GeneralTab<HeadCoach> {

	static List<String> defaultPropertyNames = Arrays.asList("first","called","last", "street1", "street2", "city","state","postalCode");
	
	public HeadCoachesTab(List<String> propertyNamesInOrder) {
		super("HeadCoaches", HeadCoach.class, (!propertyNamesInOrder.isEmpty()) ? propertyNamesInOrder : defaultPropertyNames, new HeadCoachTableController());
	}
	
}
