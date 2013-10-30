/**
 * 
 */
package org.saltations.tracker.ui;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javafx.scene.control.TableColumn.SortType;

import org.saltations.controller.Context;
import org.saltations.tracker.model.HeadCoach;

import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.table.TableController;

/**
 * @author jmochel
 *
 */
public class HeadCoachTableController extends TableController<HeadCoach> {

	public HeadCoachTableController() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.panemu.tiwulfx.table.TableController#loadData(int, java.util.List, java.util.List, java.util.List, int)
	 */
	@Override
	public TableData<HeadCoach> loadData(int arg0, List<TableCriteria> arg1,
			List<String> arg2, List<SortType> arg3, int arg4) {
		return new TableData<HeadCoach>(Context.get().getHeadCoaches(), false, Context.get().getHeadCoaches().size()); 
	}

	/**
	 * Insert this list of participants into the backing store.
	 */
	
	@Override
	public List<HeadCoach> insert(List<HeadCoach> newRecords) {
		checkNotNull(newRecords);
		checkArgument(newRecords.size() != 0);
		
		Context.get().getHeadCoaches().addAll(newRecords);
		Context.store();
		
		return Context.get().getHeadCoaches();
	}
	
	@Override
	public List<HeadCoach> update(List<HeadCoach> updatedRecords) {
		checkNotNull(updatedRecords);
		checkArgument(updatedRecords.size() != 0);

		Context.store();
		
		return Context.get().getHeadCoaches();
	}
	
	@Override
	public void delete(List<HeadCoach> deletedRecords) {
		checkNotNull(deletedRecords);
		checkArgument(deletedRecords.size() != 0);

		for (HeadCoach coach : deletedRecords) {
			Context.get().getHeadCoaches().remove(coach);
		}
		
		Context.store();
	}
	
	
}
