package org.saltations.model;

import java.util.Calendar;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Person {

	  @NonNull
	  private String firstName;

	  @NonNull
	  private String lastName;
	  
	  private String street;
	  private int postalCode;
	  private String city;
	  private Calendar birthday;

	
	public Person() {
		// TODO Auto-generated constructor stub
	}

}
