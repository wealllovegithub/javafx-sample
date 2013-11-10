package org.saltations.tracker.infra;

import java.math.BigInteger;

import org.joda.time.DateTime;

public class PropertyEval  {

	Class<?> clazz;
	
	public PropertyEval(Class<?> aClazz) {
		this.clazz = aClazz;
	}

	public boolean isInstance(Object obj) {
		return clazz.isInstance(obj);
	}

	public boolean isAssignableFrom(Class<?> cls) {
		return clazz.isAssignableFrom(cls);
	}

	public boolean isInterface() {
		return clazz.isInterface();
	}

	public boolean isArray() {
		return clazz.isArray();
	}

	public boolean isPrimitive() {
		return clazz.isPrimitive();
	}

	public boolean isAnnotation() {
		return clazz.isAnnotation();
	}

	public boolean isLocalClass() {
		return clazz.isLocalClass();
	}

	public boolean isEnum() {
		return clazz.isEnum();
	}
	
	public boolean isString()
	{
		return clazz.isAssignableFrom(String.class);
	}

	public boolean isInteger()
	{
		return clazz.isAssignableFrom(Integer.class);
	}

	public boolean isBoolean()
	{
		return clazz.isAssignableFrom(Boolean.class);
	}

	public boolean isLong()
	{
		return clazz.isAssignableFrom(Long.class);
	}

	public boolean isFloat()
	{
		return clazz.isAssignableFrom(Float.class);
	}

	public boolean isDouble()
	{
		return clazz.isAssignableFrom(Double.class);
	}

	public boolean isBigInteger()
	{
		return clazz.isAssignableFrom(BigInteger.class);
	}

	public boolean isDateTime()
	{
		return clazz.isAssignableFrom(DateTime.class);
	}

	

}
