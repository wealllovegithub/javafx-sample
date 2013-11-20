package org.saltations.tracker.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

import com.google.common.collect.ImmutableSet;

@Slf4j
public class FieldAndPropertyReflectionTest extends TestBase {

	public FieldAndPropertyReflectionTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void test() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, NoSuchFieldException, SecurityException {
		
		Object bean = Participant.class.newInstance();
		
		ImmutableSet<String> nonCompoundTypes = ImmutableSet.of("Object","Boolean", "Integer", "List", "Array", "Set");
		
		@SuppressWarnings("unchecked")
		Map<String,?> beanProperties = (Map<String,?>) BeanUtilsBean.getInstance().describe(bean);
		
		Set<String> propertyNames = beanProperties.keySet();
		
		for (String propertyName : propertyNames) {

			Class<?> propertyType = PropertyUtils.getPropertyType(bean, propertyName);
			
			log.info(MessageFormat.format("{0} - {1}", propertyType.getSimpleName(), propertyName));
			
			
			if (!nonCompoundTypes.contains(propertyType.getSimpleName()))
			{
				/*
				 * This is a compound type
				 */
			}
		}
		
		

		Map<String, Field> mappedFields = extractPropertiesAsFields(Participant.class, "");
		
		
		Set<String> keys = mappedFields.keySet();
		
		for (String key : keys) {
			
			log.info(MessageFormat.format(" ---- {0} -----", key));
			
		}
	}
	
	
	
	protected Map<String,Field> extractPropertiesAsFields(Class<?> clazz, String prefix)
	{
		Map<String,Field> mappedFields = Maps.newHashMap();
		
		ImmutableSet<String> nonCompoundTypes = ImmutableSet.of("Class","Object","Boolean", "Integer", "List", "Array", "Set");
		
		try {
			Object bean = clazz.newInstance();
			
			@SuppressWarnings("unchecked")
			Map<String,?> beanProperties = (Map<String,?>) BeanUtilsBean.getInstance().describe(bean);
			
			Set<String> propertyNames = beanProperties.keySet();
			
			for (String propertyName : propertyNames) {

				Class<?> propertyType = PropertyUtils.getPropertyType(bean, propertyName);
				
				if (Serializable.class.isAssignableFrom(propertyType) && !nonCompoundTypes.contains(propertyType.getSimpleName()))
				{
					/*
					 * This is a compound type
					 */
					
					Map<String,Field> nestedMappedFields = extractPropertiesAsFields(propertyType, propertyName + ".");
					
					mappedFields.putAll(nestedMappedFields);
				}
				else {
					
					Field field = clazz.getField(propertyName);
					
					String key = prefix.isEmpty() ? propertyName : prefix + propertyName;  
					
					mappedFields.put(key, field);
				}
				
			}
			

			
			
		} catch (InstantiationException | IllegalAccessException
				| InvocationTargetException | NoSuchMethodException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return mappedFields;
	}
	
	
	
	
	
	
	
	
}
