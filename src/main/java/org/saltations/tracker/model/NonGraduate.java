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
public class NonGraduate extends AbstractRole {

	private static final long serialVersionUID = 1L;

	public NonGraduate(RoleType type, Person person) {
		super(type, person);
		setGraduate(false);
	}
	
}
