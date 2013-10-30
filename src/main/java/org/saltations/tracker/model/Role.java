package org.saltations.tracker.model;

import com.eaio.uuid.UUID;

public interface Role {

	public abstract Boolean getGraduate();

	public abstract UUID getId();

	public abstract RoleType getType();

	public abstract String getCalled();

	public abstract String getCity();

	public abstract String getFirst();

	public abstract String getLast();

	public abstract String getPostalCode();

}