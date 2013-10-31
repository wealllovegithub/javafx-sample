/**
 * 
 */
package org.saltations.tracker.model;

import lombok.Data;
import lombok.NonNull;

import org.joda.time.DateTime;

/**
 * @author jmochel
 *
 */
@Data
public class SeminarEvt extends AbstractCenterEvt {

	@NonNull
	private Person seminarLeader;

	public SeminarEvt(DateTime when, String what, Person leader) {
		super(when, what);
		seminarLeader = leader;
	}
	
	public SeminarEvt(DateTime when, String what) {
		super(when, what);
	}
	
	public SeminarEvt() {
	}

}
