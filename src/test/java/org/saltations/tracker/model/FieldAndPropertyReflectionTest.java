package org.saltations.tracker.model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Set;

import jodd.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.apache.commons.beanutils.PropertyUtils;
import org.saltations.tracker.infra.Display;
import org.testng.annotations.Test;

@Slf4j
public class FieldAndPropertyReflectionTest extends TestBase {

	public FieldAndPropertyReflectionTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void test() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, NoSuchFieldException, SecurityException {
		
		Object bean = Participant.class.newInstance();
		
		@SuppressWarnings("unchecked")
		Map<String,?> beanProperties = (Map<String,?>) BeanUtilsBean.getInstance().describe(bean);
		
		Set<String> propertyNames = beanProperties.keySet();
		
		for (String propertyName : propertyNames) {

			Class<?> propertyType = PropertyUtils.getPropertyType(bean, propertyName);
			
			log.info(MessageFormat.format("{0} - {1}", propertyType.getSimpleName(), propertyName));
			
//			Class propertyType = BeanUtil.getDeclaredPropertyType(bean, propertyName);
			
			Display display = BeanUtilsBean2.getInstance().getPropertyUtils().getPropertyDescriptor(bean, propertyName).getPropertyType().getAnnotation(Display.class);
			
			String displayName = propertyName;
			String desc = "";
			
			if (display != null)
			{
				if (!display.name().isEmpty())
				{
					displayName = display.name();
				}
				
				if (!display.desc().isEmpty())
				{
					desc = display.desc();
				}
			}
			
			log.info(MessageFormat.format("		{0} - {1}", displayName, desc));

		}
		
//		for (Class<?> clazz : clazzes) {
//
//			if (!clazz.isInterface()) {
//
//				log.info("[" + clazz.getSimpleName() + "]");
//
//				ClassFields<?> classFields = ReflectUtils
//						.getInstance().analyzeClass(clazz);
//
//				for (String fieldName : classFields.getFieldNames()) {
//
//					log.info(fieldName);
//
//					/*
//					 * get the fields annotations.
//					 */
//
//					Set<Annotation> annotations = classFields
//							.getFieldAnnotations(fieldName);
//
//					for (Annotation annotation : annotations) {
//
//						log.info("	" + annotation.getClass().getSimpleName());
//
//						if (annotation.annotationType().isAssignableFrom(
//								Display.class)) {
//							Display display = (Display) annotation;
//
//							String displayName = display.name().isEmpty() ? fieldName
//									: display.name();
//
//							log.info("		" + displayName);
//
//							String desc = display.desc();
//
//							log.info("		" + displayName);
//
//							Class<?> fieldType = classFields
//									.getFieldType(fieldName);
//
//							if (fieldType.isAssignableFrom(Boolean.class)) {
//
//							}
//
//						}
//
//					}
//
//				}
//
//			}

//		}
	}
}
