/**
 * 
 */
package org.saltations.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author jmochel
 *
 */

@Data
@AllArgsConstructor
public class Location {

	private String street1;
	
	private String street2;
	
	private String city;
	
	private State state;

	private ZipCode postalCode;
	
	final public static Location NOWHERE = new Location("","","",State.MA, new ZipCode(""));
	
	public Location() {
		street1 = "";
		street2 = "";
		postalCode = new ZipCode("");
		city = "";
		state = State.MA;
	}
	
	

}
