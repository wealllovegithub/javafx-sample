/**
 * 
 */
package org.saltations.tracker.ui;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javafx.scene.control.TableColumn.SortType;

import org.saltations.controller.Context;
import org.saltations.tracker.model.Participant;

import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.table.TableController;

/**
 * @author jmochel
 *
 */
public class ParticipantTableController extends TableController<Participant> {

	public ParticipantTableController() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.panemu.tiwulfx.table.TableController#loadData(int, java.util.List, java.util.List, java.util.List, int)
	 */
	@Override
	public TableData<Participant> loadData(int arg0, List<TableCriteria> arg1,
			List<String> arg2, List<SortType> arg3, int arg4) {
		return new TableData<Participant>(Context.get().getParticipants(), false, Context.get().getParticipants().size()); 
	}

	/**
	 * Insert this list of participants into the backing store.
	 */
	
	@Override
	public List<Participant> insert(List<Participant> newRecords) {
		checkNotNull(newRecords);
		checkArgument(newRecords.size() != 0);
		
		Context.get().getParticipants().addAll(newRecords);
		Context.store();
		
		return Context.get().getParticipants();
	}
	
	@Override
	public List<Participant> update(List<Participant> updatedRecords) {
		checkNotNull(updatedRecords);
		checkArgument(updatedRecords.size() != 0);

		Context.store();
		
		return Context.get().getParticipants();
	}
	
	@Override
	public void delete(List<Participant> deletedRecords) {
		checkNotNull(deletedRecords);
		checkArgument(deletedRecords.size() != 0);

		for (Participant participant : deletedRecords) {
			Context.get().getParticipants().remove(participant);
		}
		
		Context.store();
	}
	
	
}
