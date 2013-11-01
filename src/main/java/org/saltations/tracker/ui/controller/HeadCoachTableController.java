/**
 * 
 */
package org.saltations.tracker.ui.controller;

import java.util.List;

import org.saltations.controller.Context;
import org.saltations.tracker.model.HeadCoach;

/**
 * @author jmochel
 *
 */
public class HeadCoachTableController extends GeneralTableController<HeadCoach> {

	public HeadCoachTableController() {
	}

	@Override
	public List<HeadCoach> getLiveList() {
		return Context.get().getHeadCoaches();
	}
	
}
