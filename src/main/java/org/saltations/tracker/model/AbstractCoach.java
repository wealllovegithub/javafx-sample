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
public class AbstractCoach extends AbstractPartaker {

	private static final long serialVersionUID = 1L;

	List<AbstractPartaker> coachees = Lists.newArrayList();

	public AbstractCoach(RoleType type, Person person) {
		super(type, person);
	}
	
}
