package org.saltations.tracker.ui.masterdetail.tab;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.saltations.tracker.model.Participant;
import org.saltations.tracker.ui.table.controller.ParticipantLiveData;

@Slf4j
public class MDParticipantAttendanceCommunicationTab extends MasterDetailTab<Participant> {

	static List<String> defaultPropertyNames = Arrays.asList("pid","person.first","person.called","person.last","handedInAttendanceCommunication");
	
	public MDParticipantAttendanceCommunicationTab(List<String> propertyNamesInOrder) {
		super("Participants", (!propertyNamesInOrder.isEmpty()) ? propertyNamesInOrder : defaultPropertyNames, new ParticipantLiveData());
	}
	
}
