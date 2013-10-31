/**
 * 
 */
package org.saltations.controller;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import org.saltations.tracker.model.Location;
import org.saltations.tracker.model.Person;
import org.saltations.tracker.model.Program;

import com.db4o.ObjectContainer;

/**
 * @author jmochel
 *
 */
public class Context {

	/**
	 * The Program class which acts as the data model for the entire program 
	 */
	
	private static Program program;
	
	/**
	 * Data Store for Db4O
	 */

	private static ObjectContainer objStore = null;

	/**
	 * Data Mapper for doing copying and mapping of POJOs 
	 */
	
	private static MapperFacade mapper = null;
	
	{
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		
		mapperFactory.classMap(Person.class, Person.class)
		   .byDefault()
		   .register();

		mapperFactory.classMap(Location.class, Location.class)
		   .byDefault()
		   .register();
		
		MapperFacade mapper = mapperFactory.getMapperFacade();
	}
	
	
	public static void set(Program aProgram)
	{
		program = aProgram;
	}

	public static void set(ObjectContainer anObjStore)
	{
		objStore = anObjStore;
	}
	
	public static Program get()
	{
		return program;
	}
	
	public static void store()
	{
		objStore.store(program);
		objStore.commit();
	}
	
}
