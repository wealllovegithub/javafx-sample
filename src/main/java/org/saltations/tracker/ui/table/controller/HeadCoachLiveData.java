/**
 * 
 */
package org.saltations.tracker.ui.table.controller;

import java.util.List;

import org.saltations.controller.Context;
import org.saltations.tracker.model.HeadCoach;
import org.saltations.tracker.model.Participant;

/**
 * @author jmochel
 *
 */
public class HeadCoachLiveData implements LiveData<HeadCoach>{

	@Override
	public List<HeadCoach> get() {
		return Context.get().getHeadCoaches();
	}
	
	public void store() {
		Context.store();
	};
	
	@Override
	public HeadCoach exemplar() {
		return new HeadCoach();
	}

}
