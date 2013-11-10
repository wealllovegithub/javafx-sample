package org.saltations.tracker.infra;

public @interface Display {
	
	String name() default "";
	
	String desc() default "";
	
	boolean inline() default true; 
	
	boolean required() default false;
}
