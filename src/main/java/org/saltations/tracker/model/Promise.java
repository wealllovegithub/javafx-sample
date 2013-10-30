package org.saltations.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.joda.time.DateTime;

@Data
@AllArgsConstructor
public class Promise {

	private String what;
	
	private DateTime byWhen;
	
	private Person toWhom;

	public Promise() {
		super();
		this.what = "";
		this.byWhen = DateTime.now();
		this.toWhom = Person.WHOLE_COMMUNITY;
	}
	
	
}
