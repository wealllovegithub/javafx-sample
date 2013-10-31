package org.saltations.tracker.model;

import com.eaio.uuid.UUID;

public interface Role {

	public String getFirst();

	public String getCalled();

	public String getLast();

	public Boolean getGraduate();

	public UUID getId();

	public RoleType getType();

	public String getStreet1();
	
	public String getStreet2();
	
	public String getCity();

	public State getState();

	public ZipCode getPostalCode();

}