/**
 * 
 */
package org.saltations.tracker.model;

/**
 * All entities must consider the equals and hashCode results to purely be a
 * function of their ID.
 * 
 * @author jmochel
 * 
 */

public interface Entity<ID> {

	/**
	 * All entities must have a unique idenity.
	 * 
	 * @since 1.0
	 * 
	 * @return The identity of the entity.
	 */

	public ID getId();

	/**
	 * Entities normally compare by identity, not attributes. This method checks
	 * that the two Entities are equal when comparing all attributes except the
	 * Id.
	 * 
	 * @param otherEntity
	 *            Entity to be compared for equivalence
	 * 
	 * @return {@code true} if the attributes are the same, no matter what the Id is. 
	 */

	public abstract boolean equivalentTo(Entity<ID> otherEntity);


}
