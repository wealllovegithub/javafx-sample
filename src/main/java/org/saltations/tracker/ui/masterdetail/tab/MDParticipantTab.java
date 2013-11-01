package org.saltations.tracker.ui.masterdetail.tab;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.saltations.tracker.model.Participant;
import org.saltations.tracker.ui.table.controller.ParticipantLiveData;

@Slf4j
public class MDParticipantTab extends MasterDetailTab<Participant> {

	static List<String> defaultPropertyNames = Arrays.asList("first","called","last", "street1", "street2", "city","state","postalCode");
	
	public MDParticipantTab(List<String> propertyNamesInOrder) {
		super("Participants", Participant.class, (!propertyNamesInOrder.isEmpty()) ? propertyNamesInOrder : defaultPropertyNames, new ParticipantLiveData());
	}

}
