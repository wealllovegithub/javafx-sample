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
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)


public class Production extends PersonWhoPartakes {

	private static final long serialVersionUID = 1L;

	/**
	 * @param person
	 */
	
	public Production(Person person) {
		super(person);
	}
}
