/**
 * 
 */
package org.saltations.tracker.model;

import org.saltations.tracker.infra.Display;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author jmochel
 *
 */

@Data
@AllArgsConstructor
public class Location {

	@Display(name = "Street Address 1")
	private String street1;

	@Display(name = "Street Address 2")
	private String street2;
	
	@Display(name = "City")
	private String city;
	
	@Display(name = "State")
	private State state;

	@Display(name = "Zip")
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
