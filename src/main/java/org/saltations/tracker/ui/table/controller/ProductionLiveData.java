/**
 * 
 */
package org.saltations.tracker.ui.table.controller;

import java.util.List;

import org.saltations.controller.Context;
import org.saltations.tracker.model.Participant;
import org.saltations.tracker.model.Production;

/**
 * @author jmochel
 *
 */
public class ProductionLiveData implements LiveData<Production>{

	@Override
	public List<Production> get() {
		return Context.get().getProduction();
	}
	
	public void store() {
		Context.store();
	};

	@Override
	public Production exemplar() {
		return new Production();
	}
}
