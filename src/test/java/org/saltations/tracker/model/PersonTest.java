/**
 * 
 */

package org.saltations.tracker.model;

import static org.testng.Assert.assertNotNull;
import lombok.extern.slf4j.Slf4j;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test for the {@code Person} <b>Entity</b>. Because this is a test of an
 * Entity the equality mechanisms are more complicated.
 * 
 * @author jmochel
 * 
 */

@Slf4j
public class PersonTest extends TestBase {

	private String nameOfClassBeingTested = Person.class.getSimpleName();

	@Test(testName = "Copy method", description = "Testing basic copying")
	void testCopying() {

		Person person = new Person("Janice", "Burrell", "Jan-Jan");
		assertNotNull(person);
		
		Person copy = person.copy();
		assertNotNull(copy);
		
		/*
		 * Walk through each element and confirm that they are equal valued but not the same object
		 */

		Assert.assertEquals(person, copy);
		
	}

}
