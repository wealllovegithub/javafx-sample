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
public class SELPEvt extends AbstractCenterEvt {

	private boolean SELFAnnouncement = false;
	
	private boolean announcements = false;;

	public SELPEvt(DateTime when, String what) {
		super(when, what);
	}
	
	public SELPEvt() {
	}

}
