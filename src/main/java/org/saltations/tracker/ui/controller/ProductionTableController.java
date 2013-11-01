/**
 * 
 */
package org.saltations.tracker.ui.controller;

import java.util.List;

import org.saltations.controller.Context;
import org.saltations.tracker.model.Production;

/**
 * @author jmochel
 *
 */
public class ProductionTableController extends GeneralTableController<Production> {

	public ProductionTableController() {
	}

	@Override
	public List<Production> getLiveList() {
		return Context.get().getProduction();
	}
	
}
