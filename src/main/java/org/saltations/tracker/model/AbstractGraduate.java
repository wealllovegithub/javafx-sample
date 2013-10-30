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
public abstract class AbstractGraduate extends AbstractRoleImpl {

	private static final long serialVersionUID = 1L;
	
	/**
     * PERSON ID - It matches up with the LM PID to simplify verification and
     * futures tracking. It is not used as the identifier of record.
     */

    private Long pid;
    
	public AbstractGraduate(RoleType type, Person person) {
		super(type, person);
		setGraduate(true);
	}
}
