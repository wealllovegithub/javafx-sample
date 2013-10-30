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
public class CourseLeader extends AbstractCoach {

	private static final long serialVersionUID = 1L;

	/**
	 * @param person
	 */
	
	public CourseLeader(Person person) {
		super(RoleType.COURSE_LEADER, person);
	}
	
	public CourseLeader()
	{
		super(RoleType.COURSE_LEADER, new Person());
	}
}
