package org.saltations.tracker.ui.tab;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.saltations.tracker.model.Coach;
import org.saltations.tracker.ui.CoachTableController;

@Slf4j
public class CoachesTab extends GeneralTab<Coach> {

	static List<String> defaultPropertyNames = Arrays.asList("first","called","last", "street1", "street2", "city","state","postalCode");
	
	public CoachesTab(List<String> propertyNamesInOrder) {
		super("Coaches", Coach.class, (!propertyNamesInOrder.isEmpty()) ? propertyNamesInOrder : defaultPropertyNames, new CoachTableController());
	}
	
}
