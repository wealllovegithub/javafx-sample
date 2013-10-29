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


public class NonGraduate extends RoleImpl {

	private static final long serialVersionUID = 1L;

	public NonGraduate(Person person) {
		super(person);
		setGraduate(false);
	}
	
}
