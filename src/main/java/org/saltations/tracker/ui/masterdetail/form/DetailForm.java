package org.saltations.tracker.ui.masterdetail.form;

import org.saltations.tracker.ui.masterdetail.MDRequestEditEvt;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.panemu.tiwulfx.form.Form;

/**
 * The {@link DetailForm} is responsible for displaying, editing and saving the contents for a POJO of type <T>    
 * 
 * @author jmochel
 *
 * @param <T> Type of teh POJO to be edited.
 */

public class DetailForm<T> extends Form<T> {

	private final EventBus bus; 
	
	public DetailForm(EventBus bus) {
		super();
		this.bus = bus;
		
		bus.register(this);
		
		super.autosize();
		super.setVisible(true);
	}

	@Subscribe
	public void changeRecord(MDRequestEditEvt<T> evt)
	{
		super.setRecord(evt.getSubject());
		super.bindChildren();
	}
}
