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

import org.saltations.tracker.infra.Display;

import com.eaio.uuid.UUID;

/**
 * @author jmochel
 *
 */

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public abstract class AbstractRole implements Serializable, Role  {

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
	@Display
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
	public ZipCode getPostalCode() {
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

	public void setPostalCode(ZipCode postalCode) {
		person.setPostalCode(postalCode);
	}

	/**
	 * @return
	 * @see org.saltations.tracker.model.Person#getState()
	 */
	public State getState() {
		return person.getState();
	}

	/**
	 * @return
	 * @see org.saltations.tracker.model.Person#getStreet1()
	 */
	public String getStreet1() {
		return person.getStreet1();
	}

	/**
	 * @return
	 * @see org.saltations.tracker.model.Person#getStreet2()
	 */
	public String getStreet2() {
		return person.getStreet2();
	}

	/**
	 * @param state
	 * @see org.saltations.tracker.model.Person#setState(org.saltations.tracker.model.State)
	 */
	public void setState(State state) {
		person.setState(state);
	}

	/**
	 * @param street1
	 * @see org.saltations.tracker.model.Person#setStreet1(java.lang.String)
	 */
	public void setStreet1(String street1) {
		person.setStreet1(street1);
	}

	/**
	 * @param street2
	 * @see org.saltations.tracker.model.Person#setStreet2(java.lang.String)
	 */
	public void setStreet2(String street2) {
		person.setStreet2(street2);
	}

	
}
