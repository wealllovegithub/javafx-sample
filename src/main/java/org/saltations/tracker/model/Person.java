package org.saltations.tracker.model;

import java.util.Calendar;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.saltations.tracker.infra.Display;

@Data
@RequiredArgsConstructor
public class Person {

	  @NonNull
	  @Display("First")
	  private String first;

	  @NonNull
	  @Display("Last")
	  private String last;

	  @NonNull
	  @Display("Called")
	  private String called;
	  
	  @Display("Street")
	  private String street;
	  
	  @Display("Zip")
	  private String postalCode;
	  
	  @Display("City")
	  private String city;
	  
	  private Calendar birthday;

	
	public Person() {
		first = "";
		last = "";
		called = "";
		street = "";
	}

}
