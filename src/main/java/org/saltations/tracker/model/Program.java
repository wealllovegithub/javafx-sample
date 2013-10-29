package org.saltations.tracker.model;

import java.io.Serializable;
import java.util.List;

import javax.management.relation.Role;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.joda.time.DateTime;

import com.google.common.collect.Lists;


/**
 * @author jmochel
 */

@Data
@EqualsAndHashCode(callSuper=true)
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

	private List<Role> roles = Lists.newArrayList();

	/**
	 * Method designed to walkthrough the roles and come up with a list of the given Role class  
	 *  
	 * @param clazz
	 * @return
	 */

	private class RoleExemplar<T extends Role> {
		T exemplar;

		public RoleExemplar(Class<T> clazz) {

			try {
				exemplar = clazz.newInstance();
			} catch (InstantiationException e) {
				throw new IllegalStateException(e);
			} catch (IllegalAccessException e) {
				throw new IllegalStateException(e);
			}

		}

		boolean instanceOf(Role sample) {
			return exemplar.getClass().isAssignableFrom(sample.getClass());
		}
	}

	public <T extends Role> List<T> getPeeps(Class<T> clazz) {
		
		RoleExemplar<T> exemplar = new RoleExemplar(clazz);

		List<T> peeps = Lists.newArrayList();

		for (Role role : roles) {

			if (exemplar.instanceOf(role)) {
				peeps.add((T) role);
			}
		}

		return peeps;
	}

}
