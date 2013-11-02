/**
 * 
 */
package org.saltations.tracker.ui.table.controller;

import java.util.List;

/**
 * @author jmochel
 *
 */
public interface LiveData<T> {

	public List<T> get();
	
	public T exemplar();
	
	public void store();
	
	
}
