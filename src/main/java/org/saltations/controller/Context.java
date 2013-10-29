/**
 * 
 */
package org.saltations.controller;

import org.saltations.tracker.model.Program;

/**
 * @author jmochel
 *
 */
public class Context {

	private static Program program; 
	
	
	public static void set(Program aProgram)
	{
		program = aProgram;
	}

	public static Program get()
	{
		return program;
	}
}
