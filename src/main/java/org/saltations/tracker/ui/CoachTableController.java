/**
 * 
 */
package org.saltations.tracker.ui;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javafx.scene.control.TableColumn.SortType;

import org.saltations.controller.Context;
import org.saltations.tracker.model.Coach;

import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.table.TableController;

/**
 * @author jmochel
 *
 */
public class CoachTableController extends TableController<Coach> {

	public CoachTableController() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.panemu.tiwulfx.table.TableController#loadData(int, java.util.List, java.util.List, java.util.List, int)
	 */
	@Override
	public TableData<Coach> loadData(int arg0, List<TableCriteria> arg1,
			List<String> arg2, List<SortType> arg3, int arg4) {
		return new TableData<Coach>(Context.get().getCoaches(), false, Context.get().getCoaches().size()); 
	}

	/**
	 * Insert this list of participants into the backing store.
	 */
	
	@Override
	public List<Coach> insert(List<Coach> newRecords) {
		checkNotNull(newRecords);
		checkArgument(newRecords.size() != 0);
		
		Context.get().getCoaches().addAll(newRecords);
		Context.store();
		
		return Context.get().getCoaches();
	}
	
	@Override
	public List<Coach> update(List<Coach> updatedRecords) {
		checkNotNull(updatedRecords);
		checkArgument(updatedRecords.size() != 0);

		Context.store();
		
		return Context.get().getCoaches();
	}
	
	@Override
	public void delete(List<Coach> deletedRecords) {
		checkNotNull(deletedRecords);
		checkArgument(deletedRecords.size() != 0);

		for (Coach coach : deletedRecords) {
			Context.get().getCoaches().remove(coach);
		}
		
		Context.store();
	}
	
	
}
