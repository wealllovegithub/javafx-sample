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
public class Participant extends AbstractPartaker {

	private static final long serialVersionUID = 1L;

	/**
	 * @param person
	 */
	
	public Participant(Person person) {
		super(RoleType.PARTICIPANT, person);
	}
	
	public Participant() {
		super(RoleType.PARTICIPANT, new Person());
	}
	
}
