package org.saltations.tracker.ui.tab;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.saltations.tracker.model.Participant;
import org.saltations.tracker.model.State;
import org.saltations.tracker.model.ZipCode;
import org.saltations.tracker.ui.ParticipantTableController;

@Slf4j
public class ParticipantAttendanceCommunicationTab extends GeneralTab<Participant> {

	static List<String> defaultPropertyNames = Arrays.asList("first","called","last", "handedInAttendanceCommunication");
	
	public ParticipantAttendanceCommunicationTab(List<String> propertyNamesInOrder) {
		super("Participants", Participant.class, (!propertyNamesInOrder.isEmpty()) ? propertyNamesInOrder : defaultPropertyNames, new ParticipantTableController());
	}
	
}
