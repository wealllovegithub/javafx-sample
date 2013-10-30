/**
 * 
 */
package org.saltations.tracker.ui;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javafx.scene.control.TableColumn.SortType;

import org.saltations.controller.Context;
import org.saltations.tracker.model.Production;

import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.table.TableController;

/**
 * @author jmochel
 *
 */
public class ProductionTableController extends TableController<Production> {

	public ProductionTableController() {
	}

	/* (non-Javadoc)
	 * @see com.panemu.tiwulfx.table.TableController#loadData(int, java.util.List, java.util.List, java.util.List, int)
	 */
	
	@Override
	public TableData<Production> loadData(int arg0, List<TableCriteria> arg1,
			List<String> arg2, List<SortType> arg3, int arg4) {
		return new TableData<Production>(Context.get().getProduction(), false, Context.get().getProduction().size()); 
	}

	/**
	 * Insert this list of participants into the backing store.
	 */
	
	@Override
	public List<Production> insert(List<Production> newRecords) {
		checkNotNull(newRecords);
		checkArgument(newRecords.size() != 0);
		
		Context.get().getProduction().addAll(newRecords);
		Context.store();
		
		return Context.get().getProduction();
	}
	
	@Override
	public List<Production> update(List<Production> updatedRecords) {
		checkNotNull(updatedRecords);
		checkArgument(updatedRecords.size() != 0);

		Context.store();
		
		return Context.get().getProduction();
	}
	
	@Override
	public void delete(List<Production> deletedRecords) {
		checkNotNull(deletedRecords);
		checkArgument(deletedRecords.size() != 0);

		for (Production coach : deletedRecords) {
			Context.get().getProduction().remove(coach);
		}
		
		Context.store();
	}
	
	
}
