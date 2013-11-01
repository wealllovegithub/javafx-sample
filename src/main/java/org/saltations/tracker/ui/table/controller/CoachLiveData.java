/**
 * 
 */
package org.saltations.tracker.ui.table.controller;

import java.util.List;

import org.saltations.controller.Context;
import org.saltations.tracker.model.Coach;

/**
 * @author jmochel
 *
 */
public class CoachLiveData implements LiveData<Coach>{

	@Override
	public List<Coach> get() {
		return Context.get().getCoaches();
	}
	
	public void store() {
		Context.store();
	};

}
