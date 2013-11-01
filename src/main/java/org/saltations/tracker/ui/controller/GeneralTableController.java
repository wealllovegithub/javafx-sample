/**
 * 
 */
package org.saltations.tracker.ui.controller;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javafx.scene.control.TableColumn.SortType;

import org.saltations.controller.Context;

import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.table.TableController;

/**
 * @author jmochel
 *
 */
public abstract class GeneralTableController<T> extends TableController<T> {

	public abstract List<T> getLiveList();
	
	public GeneralTableController() {
	}

	@Override
	public TableData<T> loadData(int arg0, List<TableCriteria> arg1,
			List<String> arg2, List<SortType> arg3, int arg4) {
		
		return new TableData<T>(getLiveList(), false, getLiveList().size()); 
	}

	/**
	 * Insert this list of participants into the backing store.
	 */
	
	@Override
	public List<T> insert(List<T> newRecords) {
		checkNotNull(newRecords);
		checkArgument(newRecords.size() != 0);
		
		getLiveList().addAll(newRecords);
		Context.store();
		
		return getLiveList();
	}
	
	@Override
	public List<T> update(List<T> updatedRecords) {
		checkNotNull(updatedRecords);
		checkArgument(updatedRecords.size() != 0);

		Context.store();
		
		return getLiveList();
	}
	
	@Override
	public void delete(List<T> deletedRecords) {
		checkNotNull(deletedRecords);
		checkArgument(deletedRecords.size() != 0);

		for (T subject : deletedRecords) {
			getLiveList().remove(subject);
		}
		
		Context.store();
	}
	
	
}
