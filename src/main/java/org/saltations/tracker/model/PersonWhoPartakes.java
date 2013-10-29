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
public class PersonWhoPartakes extends Graduate {

	private static final long serialVersionUID = 1L;
	
//	private Participation participation;
	
	public PersonWhoPartakes(Person person) {
		super(person);
	}
}
