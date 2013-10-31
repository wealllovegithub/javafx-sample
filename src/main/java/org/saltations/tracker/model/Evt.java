package org.saltations.tracker.model;

import org.joda.time.DateTime;

public interface Evt {

	public String getWhat();

	public DateTime getWhen();

}
