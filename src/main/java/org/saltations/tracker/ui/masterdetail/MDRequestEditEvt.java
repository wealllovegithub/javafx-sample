package org.saltations.tracker.ui.masterdetail;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class MDRequestEditEvt<T> {
	
	private T subject;

}
