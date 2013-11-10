/**
 * 
 */
package org.saltations.controller;

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
