package org.saltations.tracker.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Person {

	  @NonNull
	  private String first;

	  @NonNull
	  private String last;

	  @NonNull
	  private String called;

	  private String postalCode;
	  
	  private String city;
	  
	
	public Person() {
		first = "";
		last = "";
		called = "";
	}

}
