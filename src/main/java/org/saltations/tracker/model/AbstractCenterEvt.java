/**
 * 
 */
package org.saltations.tracker.model;

import lombok.Data;

import org.joda.time.DateTime;

/**
 * @author jmochel
 *
 */
@Data
public class AbstractCenterEvt extends AbstractEvt {

	
	private Boolean guestEvt;

	private Person leader;

	public AbstractCenterEvt(DateTime when, String what) {
		super(when, what);
		leader = Person.UNKNOWN;
		guestEvt = false;
	}
	
	public AbstractCenterEvt() {
	}
}
