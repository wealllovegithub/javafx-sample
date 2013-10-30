/**
 * 
 */
package org.saltations.tracker.model;

import org.joda.time.DateTime;

/**
 * @author jmochel
 *
 */

public class AbstractHappening implements Happening {

	@Override
	public DateTime getWhen() {
		return when;
	}

	public void setWhen(DateTime when) {
		this.when = when;
	}

	@Override
	public String getWhat() {
		return what;
	}

	public void setWhat(String what) {
		this.what = what;
	}

	private DateTime when; 

	private String what;
}
