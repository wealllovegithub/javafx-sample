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
public class AbstractPartaker extends AbstractGraduate {

	private static final long serialVersionUID = 1L;

	public AbstractPartaker(RoleType type, Person person) {
		super(type, person);
	}
}
