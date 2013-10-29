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
@NoArgsConstructor()
@EqualsAndHashCode(callSuper=true)


public class Graduate extends RoleImpl {

	private static final long serialVersionUID = 1L;
	
	/**
     * PERSON ID - It matches up with the LM PID to simplify verification and
     * futures tracking. It is not used as the identifier of record.
     */

    private Long pid;

	public Graduate(Person person) {
		super(person);
		setGraduate(true);
	}
	
}
