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
public class Production extends AbstractPartaker {

	private static final long serialVersionUID = 1L;

	/**
	 * @param person
	 */
	
	public Production(Person person) {
		super(RoleType.PRODUCTION, person);
	}
	
	public Production()
	{
		super(RoleType.PRODUCTION, new Person());
	}
}
