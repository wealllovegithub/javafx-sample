/**
 * 
 */
package org.saltations.tracker.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import com.eaio.uuid.UUID;

/**
 * @author jmochel
 *
 */

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public abstract class AbstractRoleImpl implements Serializable, Role  {

	private static final long serialVersionUID = 1L;
	
	private UUID id = new UUID();

	/**
	 * What type does this Role represent
	 */
	
	@NonNull
	private RoleType type;
		
	/**
	 * Person who has this Role.
	 */
	
	@NonNull
	private Person person;

    /**
     * Are they a graduate flag
     */

	@NonNull
    private Boolean graduate = true;

	/* (non-Javadoc)
	 * @see org.saltations.tracker.model.Role#getCalled()
	 */
	@Override
	public String getCalled() {
		return person.getCalled();
	}

	/* (non-Javadoc)
	 * @see org.saltations.tracker.model.Role#getCity()
	 */
	@Override
	public String getCity() {
		return person.getCity();
	}

	/* (non-Javadoc)
	 * @see org.saltations.tracker.model.Role#getFirst()
	 */
	@Override
	public String getFirst() {
		return person.getFirst();
	}

	/* (non-Javadoc)
	 * @see org.saltations.tracker.model.Role#getLast()
	 */
	@Override
	public String getLast() {
		return person.getLast();
	}

	/* (non-Javadoc)
	 * @see org.saltations.tracker.model.Role#getPostalCode()
	 */
	@Override
	public String getPostalCode() {
		return person.getPostalCode();
	}

	public void setCalled(String called) {
		person.setCalled(called);
	}

	public void setCity(String city) {
		person.setCity(city);
	}

	public void setFirst(String first) {
		person.setFirst(first);
	}

	public void setLast(String last) {
		person.setLast(last);
	}

	public void setPostalCode(String postalCode) {
		person.setPostalCode(postalCode);
	}

	
}
