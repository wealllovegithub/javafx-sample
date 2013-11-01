package org.saltations.tracker.model;

import java.util.Map;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import com.google.common.collect.Maps;

@Data
public class Person {
	
	final public static Person WHOLE_COMMUNITY = new Person("WHOLE","COMMUNITY","US");
	
	final public static Person UNKNOWN = new Person("John/Jane","Doe","");

	@NonNull
	private String first;

	@NonNull
	private String last;

	@NonNull
	private String called;

	private Location location;

	private Map<String, Promise> promises = Maps.newHashMap();
	
	public Person() {
		first = "";
		last = "";
		called = "";
		location = new Location("","","",State.AK, new ZipCode(""));
	}

	public Person(String first, String last, String called) {
		super();
		this.first = first;
		this.last = last;
		this.called = called;
		
		this.location = new Location("","","",State.AK, new ZipCode(""));
	}
	
	
	/**
	 * @return
	 * @see org.saltations.tracker.model.Location#getCity()
	 */
	public String getCity() {
		return location.getCity();
	}

	/**
	 * @return
	 * @see org.saltations.tracker.model.Location#getPostalCode()
	 */
	public ZipCode getPostalCode() {
		return location.getPostalCode();
	}
	/**
	 * @return
	 * @see org.saltations.tracker.model.Location#getState()
	 */
	public State getState() {
		return location.getState();
	}

	/**
	 * @return
	 * @see org.saltations.tracker.model.Location#getStreet1()
	 */
	public String getStreet1() {
		return location.getStreet1();
	}

	/**
	 * @return
	 * @see org.saltations.tracker.model.Location#getStreet2()
	 */
	public String getStreet2() {
		return location.getStreet2();
	}

	/**
	 * @param city
	 * @see org.saltations.tracker.model.Location#setCity(java.lang.String)
	 */
	public void setCity(String city) {
		location.setCity(city);
	}

	/**
	 * @param postalCode
	 * @see org.saltations.tracker.model.Location#setPostalCode(org.saltations.tracker.model.ZipCode)
	 */
	public void setPostalCode(ZipCode postalCode) {
		location.setPostalCode(postalCode);
	}

	/**
	 * @param state
	 * @see org.saltations.tracker.model.Location#setState(org.saltations.tracker.model.State)
	 */
	public void setState(State state) {
		location.setState(state);
	}

	/**
	 * @param street1
	 * @see org.saltations.tracker.model.Location#setStreet1(java.lang.String)
	 */
	public void setStreet1(String street1) {
		location.setStreet1(street1);
	}

	/**
	 * @param street2
	 * @see org.saltations.tracker.model.Location#setStreet2(java.lang.String)
	 */
	public void setStreet2(String street2) {
		location.setStreet2(street2);
	}


	
}
