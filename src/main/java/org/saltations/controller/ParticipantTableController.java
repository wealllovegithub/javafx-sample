/**
 * 
 */
package org.saltations.controller;

import java.util.List;

import javafx.scene.control.TableColumn.SortType;

import org.saltations.tracker.model.Participant;

import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.table.TableController;

/**
 * 
 * The controller that is
 * responsible to load data, additionally to delete, update and insert data.
 * <p>
 * TableController is an abstract class. You need to extend it and implement
 * loadData() methods. If you want your table to have delete and save
 * capabilities, you need to override insert(), update() and delete() methods.
 * There are also several callback methods that could be overriden to customize
 * TableControl’s default behaviour.
 * 
 * @author jmochel
 */

public class ParticipantTableController extends TableController<Participant> {
	



//	    @Override
//	    public List<Person> update(List<Person> records) {
//	        List<Person> result = daoPerson.update(records);
//	        result = daoPerson.initRelationship(records, Person_.insurance.getName());
//	        return result;
//	    }
//
//	    @Override
//	    public void delete(List<Person> records) {
//	        daoPerson.delete(records);
//	    }
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.panemu.tiwulfx.table.TableController#loadData(int,
	 * java.util.List, java.util.List, java.util.List, int)
	 */
	@Override
	public TableData loadData(int startIndex,
			List<TableCriteria> filteredColumns, List<String> sortedColumns,
			List<SortType> sortingOrders, int maxResult) {

        TableData<Participant> result = new TableData(Context.get().getPeeps(Participant.class),false, Context.get().getPeeps(Participant.class).size()); 		
		
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.panemu.tiwulfx.table.TableController#insert(java.util.List)
	 */
	@Override
	public List<Participant> insert(List<Participant> newRecords) {
		
		List<Participant> participants = Context.get().getPeeps(Participant.class);
		
		return participants;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.panemu.tiwulfx.table.TableController#update(java.util.List)
	 */
	@Override
	public List<Participant> update(List<Participant> records) {
		return super.update(records);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.panemu.tiwulfx.table.TableController#delete(java.util.List)
	 */
	@Override
	public void delete(List<Participant> records) {
		super.delete(records);
	}

}
