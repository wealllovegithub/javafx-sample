/**
 * 
 */
package org.saltations.tracker.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.joda.time.DateTime;

/**
 * @author jmochel
 *
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public abstract class AbstractEvt implements Evt {

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

	@NonNull
	private DateTime when; 

	@NonNull
	private String what;
	
	
}
