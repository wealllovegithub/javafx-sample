/**
 * 
 */
package org.saltations.tracker.infra;

import org.apache.poi.ss.formula.functions.T;
import org.saltations.tracker.model.Role;

import com.google.common.base.Predicate;

/**
 * @author jmochel
 */

public class PredicateRole<T extends Role> implements Predicate<T> {

	private Class<T> clazz;
	
	public PredicateRole(Class<T> aClazz) {
		this.clazz = aClazz;
	}
	
	@Override
	public boolean apply(T role) {
		return clazz.isInstance(role);
	}

}
