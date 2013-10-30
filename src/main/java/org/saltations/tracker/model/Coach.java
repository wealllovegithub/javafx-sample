/**
 * 
 */
package org.saltations.tracker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jmochel
 *
 */

@Data
@EqualsAndHashCode(callSuper=true)
public class Coach extends AbstractCoach {

	private static final long serialVersionUID = 1L;

	/**
	 * @param person
	 */
	
	public Coach(Person person) {
		super(RoleType.COACH, person);
	}
	
	public Coach() {
		super(RoleType.COACH, new Person());
	}

}
