/**
 * 
 */
package org.saltations.tracker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author jmochel
 *
 */

@Data
@EqualsAndHashCode(callSuper=true)
public class HeadCoach extends AbstractCoach {

	private static final long serialVersionUID = 1L;

	public HeadCoach(Person person) {
		super(RoleType.HEAD_COACH, person);
	}
	
	public HeadCoach() {
		super(RoleType.HEAD_COACH, new Person());
	}
}
