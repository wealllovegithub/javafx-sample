package org.saltations.tracker.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.joda.time.DateTime;
import org.saltations.tracker.infra.PredicateRole;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

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

	/**
	 * All the roles
	 */

	private Set<Role> roles = Sets.newConcurrentHashSet();

	public <T extends Role> Set<T> getPeeps(Class<T> clazz) {

		return Sets.filter(roles, new PredicateRole(clazz));
		
	}

}
