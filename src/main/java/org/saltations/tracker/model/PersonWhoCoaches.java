/**
 * 
 */
package org.saltations.tracker.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.google.common.collect.Lists;

/**
 * @author jmochel
 *
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class PersonWhoCoaches extends PersonWhoPartakes {

	private static final long serialVersionUID = 1L;

	/**
	 * @param person
	 */
	
	public PersonWhoCoaches(Person person) {
		super(person);
	}
	
	List<PersonWhoPartakes> coachees = Lists.newArrayList();
}
