/**
 * 
 */
package org.saltations.tracker.ui.table.controller;

import java.util.List;

import org.saltations.controller.Context;
import org.saltations.tracker.model.Participant;

/**
 * @author jmochel
 *
 */
public class ParticipantLiveData implements LiveData<Participant>{

	@Override
	public List<Participant> get() {
		return Context.get().getParticipants();
	}

	@Override
	public void store() {
		Context.store();
	};

	@Override
	public Participant exemplar() {
		return new Participant();
	}
	
}
