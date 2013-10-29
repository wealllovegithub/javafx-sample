package org.saltations.tracker.model;

import java.util.Calendar;

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
	  
	  
	  private String street;
	  private int postalCode;
	  private String city;
	  private Calendar birthday;

	
	public Person() {
		// TODO Auto-generated constructor stub
	}

}
