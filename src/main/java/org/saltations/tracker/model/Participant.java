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
public class Participant extends PersonWhoPartakes {

	private static final long serialVersionUID = 1L;

	/**
	 * @param person
	 */
	
	public Participant(Person person) {
		super(person);
	}
	
	public Participant() {
		super(new Person());
	}
	
}
