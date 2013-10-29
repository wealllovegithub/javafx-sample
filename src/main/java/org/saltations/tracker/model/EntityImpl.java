package org.saltations.tracker.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import com.eaio.uuid.UUID;

/**
 * @author jmochel
 *
 */

@Data
@NoArgsConstructor
public abstract class EntityImpl implements Entity<UUID> {

	/**
	 * Globally (Almost Unique) identifier
	 */
	
	@NonNull
	private final UUID id = new UUID();

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityImpl other = (EntityImpl) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean equivalentTo(Entity<UUID> otherEntity) {
		throw new UnsupportedOperationException();
	}
	
}
