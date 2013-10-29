package org.saltations.tracker.model;


public interface Role {

	public abstract Boolean getGraduate();

	public abstract Person getPerson();

	/**
	 * @return
	 * @see org.saltations.tracker.model.Person#getCalled()
	 */
	public abstract String getCalled();

	/**
	 * @return
	 * @see org.saltations.tracker.model.Person#getFirst()
	 */
	public abstract String getFirst();

	/**
	 * @return
	 * @see org.saltations.tracker.model.Person#getLast()
	 */
	public abstract String getLast();

}