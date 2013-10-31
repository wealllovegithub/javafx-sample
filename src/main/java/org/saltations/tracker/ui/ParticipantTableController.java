/**
 * 
 */
package org.saltations.tracker.ui;

import java.util.List;

import org.saltations.controller.Context;
import org.saltations.tracker.model.Participant;

/**
 * @author jmochel
 *
 */
public class ParticipantTableController extends GeneralTableController<Participant> {

	public ParticipantTableController() {
	}

	@Override
	public List<Participant> getLiveList() {
		return Context.get().getParticipants();
	}	
	
}
