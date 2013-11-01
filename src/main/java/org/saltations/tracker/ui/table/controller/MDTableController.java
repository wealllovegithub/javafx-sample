/**
 * 
 */
package org.saltations.tracker.ui.table.controller;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javafx.scene.control.TableColumn.SortType;
import lombok.Data;
import lombok.Getter;

import org.saltations.controller.Context;
import org.saltations.tracker.ui.masterdetail.MDRequestEditEvt;

import com.google.common.eventbus.EventBus;
import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.table.TableController;

/**
 * Master Detail Table Controller takes an interface element that produces a
 * data set that can be treated as "Live", i.e any changes to the data set can be
 * simply stored.
 * 
 * @author jmochel
 */

public class MDTableController<T> extends TableController<T> {

	public LiveData<T> data;

	final private EventBus bus;

	public MDTableController(EventBus bus, LiveData<T> data) {
		super();
		this.bus = bus;
		this.data = data;
		
		bus.register(this);
	}

	@Override
	public TableData<T> loadData(int arg0, List<TableCriteria> arg1,
			List<String> arg2, List<SortType> arg3, int arg4) {

		return new TableData<T>(data.get(), false, data.get().size());
	}

	/**
	 * Insert this list of participants into the backing store.
	 */

	@Override
	public List<T> insert(List<T> newRecords) {
		checkNotNull(newRecords);
		checkArgument(newRecords.size() != 0);

		data.get().addAll(newRecords);
		data.store();

		return data.get();
	}

	@Override
	public List<T> update(List<T> updatedRecords) {
		checkNotNull(updatedRecords);
		checkArgument(updatedRecords.size() != 0);

		data.store();

		return data.get();
	}

	@Override
	public void delete(List<T> deletedRecords) {
		checkNotNull(deletedRecords);
		checkArgument(deletedRecords.size() != 0);

		for (T subject : deletedRecords) {
			data.get().remove(subject);
		}

		data.store();
	}

	@Override
	public void doubleClick(T record) {
		bus.post(new MDRequestEditEvt<T>(record));
	}
}
