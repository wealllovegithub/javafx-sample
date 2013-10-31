package org.saltations.tracker.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.joda.time.DateTime;
import org.saltations.StockEvt;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

/**
 * @author jmochel
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@RequiredArgsConstructor
public class Program extends EntityImpl implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Start Date
	 */

	@NonNull
	private DateTime startDate;

	/*
	 * End Date
	 */

	@NonNull
	private DateTime endDate;

	private List<Coach> coaches = Lists.newArrayList();
	
	private List<Participant> participants = Lists.newArrayList();
	
	private List<HeadCoach> headCoaches = Lists.newArrayList();

	private List<Production> production = Lists.newArrayList();

	Multimap<StockEvt, Evt> calendar = HashMultimap.create();
}
