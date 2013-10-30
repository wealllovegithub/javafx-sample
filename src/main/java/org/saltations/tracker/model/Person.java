package org.saltations.tracker.model;

import java.util.Map;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import com.google.common.collect.Maps;

@Data
@RequiredArgsConstructor
public class Person {
	
	final public static Person WHOLE_COMMUNITY = new Person("WHOLE","COMMUNITY","US");

	@NonNull
	private String first;

	@NonNull
	private String last;

	@NonNull
	private String called;

	private String postalCode;

	private String city;

	private Map<String, Promise> promises = Maps.newHashMap();

	public Person() {
		first = "";
		last = "";
		called = "";
	}
	
	

}
