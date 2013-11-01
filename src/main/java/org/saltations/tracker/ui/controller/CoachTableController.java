/**
 * 
 */
package org.saltations.tracker.ui.controller;

import java.util.List;

import org.saltations.controller.Context;
import org.saltations.tracker.model.Coach;

/**
 * @author jmochel
 *
 */
public class CoachTableController extends GeneralTableController<Coach> {

	public CoachTableController() {
	}

	@Override
	public List<Coach> getLiveList() {
		return Context.get().getCoaches();
	}
	
}
