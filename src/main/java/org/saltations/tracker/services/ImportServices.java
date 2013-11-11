package org.saltations.tracker.services;

import static java.text.MessageFormat.format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.saltations.controller.Context;
import org.saltations.tracker.model.Coach;
import org.saltations.tracker.model.HeadCoach;
import org.saltations.tracker.model.Participant;
import org.saltations.tracker.model.Person;
import org.saltations.tracker.model.Production;

import com.csvreader.CsvReader;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

public class ImportServices {

	public ImportServices() {
		
	}
	
	public static Set<ConstraintViolation<Participant>> importParticipants(File csvFile)
	{
		Set<ConstraintViolation<Participant>> violations = Sets.newHashSet();

		try {
			CsvReader reader = new CsvReader(csvFile.getAbsolutePath());
			
			reader.readHeaders();
			
			/*
			 * Validate the headers
			 */
			
			ImmutableSet<String> REQUIRED_HEADERS = ImmutableSet.of("PID", "FIRST", "LAST", "LIKES");

			Set<String> actualHeaders = Sets.newHashSet(reader.getHeaders());
			
			SetView<String> missingHeaders = Sets.difference(REQUIRED_HEADERS, actualHeaders);
			
			if (!missingHeaders.isEmpty() )
			{
				for (String missingHeader : missingHeaders) {
					violations.add(new Violation<Participant>(format("File {0} is missing header {1}", csvFile.getAbsolutePath(), missingHeader)));	
				}
				
				return violations;
			}
			
			/*
			 * Read in the record.
			 */
			
			while (reader.readRecord() )
			{
				/*
				 * Read in the values
				 */
				
				String pid = reader.get("PID"); 
				String first = reader.get("FIRST");
				String last = reader.get("LAST");
				String likes = reader.get("LIKES");
				
				/*
				 * Create a person for the Role
				 */
				
				Person person = new Person(first, last, likes);
				
				/*
				 * Create the role
				 */
				
				Participant subject = new Participant(person);
				subject.setPid(pid);
				

				/*
				 * Add to the list of participants
				 */
				
				if (Context.get().getParticipants().contains(subject))
				{
					Context.get().getParticipants().remove(subject);
				}
				
				Context.get().getParticipants().add(subject);
				
				Context.store();
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			throw new IllegalStateException(e);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

		return violations;
	}

	public static Set<ConstraintViolation<Coach>> importCoaches(File file) {
		Set<ConstraintViolation<Coach>> violations = Sets.newHashSet();

		try {
			CsvReader reader = new CsvReader(file.getAbsolutePath());
			
			reader.readHeaders();
			
			/*
			 * Validate the headers
			 */
			
			ImmutableSet<String> REQUIRED_HEADERS = ImmutableSet.of("PID", "FIRST", "LAST", "LIKES");

			Set<String> actualHeaders = Sets.newHashSet(reader.getHeaders());
			
			SetView<String> missingHeaders = Sets.difference(REQUIRED_HEADERS, actualHeaders);
			
			if (!missingHeaders.isEmpty() )
			{
				for (String missingHeader : missingHeaders) {
					violations.add(new Violation<Coach>(format("File {0} is missing header {1}", file.getAbsolutePath(), missingHeader)));	
				}
				
				return violations;
			}
			
			/*
			 * Read in the record.
			 */
			
			while (reader.readRecord() )
			{
				/*
				 * Read in the values
				 */
				
				String pid = reader.get("PID"); 
				String first = reader.get("FIRST");
				String last = reader.get("LAST");
				String likes = reader.get("LIKES");
				
				/*
				 * Create a person for the Role
				 */
				
				Person person = new Person(first, last, likes);
				
				/*
				 * Create the role
				 */
				
				Coach subject = new Coach(person);
				subject.setPid(pid);
				

				/*
				 * Add to the list of participants
				 */
				
				if (Context.get().getCoaches().contains(subject))
				{
					Context.get().getCoaches().remove(subject);
				}
				
				Context.get().getCoaches().add(subject);
				
				Context.store();
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			throw new IllegalStateException(e);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

		return violations;
	}
	
	public static Set<ConstraintViolation<HeadCoach>> importHeadCoaches(File file) {
		Set<ConstraintViolation<HeadCoach>> violations = Sets.newHashSet();

		try {
			CsvReader reader = new CsvReader(file.getAbsolutePath());
			
			reader.readHeaders();
			
			/*
			 * Validate the headers
			 */
			
			ImmutableSet<String> REQUIRED_HEADERS = ImmutableSet.of("PID", "FIRST", "LAST", "LIKES");

			Set<String> actualHeaders = Sets.newHashSet(reader.getHeaders());
			
			SetView<String> missingHeaders = Sets.difference(REQUIRED_HEADERS, actualHeaders);
			
			if (!missingHeaders.isEmpty() )
			{
				for (String missingHeader : missingHeaders) {
					violations.add(new Violation<HeadCoach>(format("File {0} is missing header {1}", file.getAbsolutePath(), missingHeader)));	
				}
				
				return violations;
			}
			
			/*
			 * Read in the record.
			 */
			
			while (reader.readRecord() )
			{
				/*
				 * Read in the values
				 */
				
				String pid = reader.get("PID"); 
				String first = reader.get("FIRST");
				String last = reader.get("LAST");
				String likes = reader.get("LIKES");
				
				/*
				 * Create a person for the Role
				 */
				
				Person person = new Person(first, last, likes);
				
				/*
				 * Create the role
				 */
				
				HeadCoach subject = new HeadCoach(person);
				subject.setPid(pid);
				

				/*
				 * Add to the list of participants
				 */
				
				if (Context.get().getHeadCoaches().contains(subject))
				{
					Context.get().getHeadCoaches().remove(subject);
				}
				
				Context.get().getHeadCoaches().add(subject);
				
				Context.store();
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			throw new IllegalStateException(e);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

		return violations;
	}
	
	
	public static Set<ConstraintViolation<Production>> importProduction(File file) {
		Set<ConstraintViolation<Production>> violations = Sets.newHashSet();

		try {
			CsvReader reader = new CsvReader(file.getAbsolutePath());
			
			reader.readHeaders();
			
			/*
			 * Validate the headers
			 */
			
			ImmutableSet<String> REQUIRED_HEADERS = ImmutableSet.of("PID", "FIRST", "LAST", "LIKES");

			Set<String> actualHeaders = Sets.newHashSet(reader.getHeaders());
			
			SetView<String> missingHeaders = Sets.difference(REQUIRED_HEADERS, actualHeaders);
			
			if (!missingHeaders.isEmpty() )
			{
				for (String missingHeader : missingHeaders) {
					violations.add(new Violation<Production>(format("File {0} is missing header {1}", file.getAbsolutePath(), missingHeader)));	
				}
				
				return violations;
			}
			
			/*
			 * Read in the record.
			 */
			
			while (reader.readRecord() )
			{
				/*
				 * Read in the values
				 */
				
				String pid = reader.get("PID"); 
				String first = reader.get("FIRST");
				String last = reader.get("LAST");
				String likes = reader.get("LIKES");
				
				/*
				 * Create a person for the Role
				 */
				
				Person person = new Person(first, last, likes);
				
				/*
				 * Create the role
				 */
				
				Production subject = new Production(person);
				subject.setPid(pid);
				

				/*
				 * Add to the list of participants
				 */
				
				if (Context.get().getProduction().contains(subject))
				{
					Context.get().getProduction().remove(subject);
				}
				
				Context.get().getProduction().add(subject);
				
				Context.store();
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			throw new IllegalStateException(e);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

		return violations;
	}
}
