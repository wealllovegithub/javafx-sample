package org.saltations.tracker.ui.masterdetail;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class MDTableRefreshEvt<T> {
	
	private T subject;

}
