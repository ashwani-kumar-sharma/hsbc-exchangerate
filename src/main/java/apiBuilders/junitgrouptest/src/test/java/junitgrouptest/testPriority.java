package junitgrouptest;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.junit.rules.MethodRule;

import junitgrouptest.testPriority.Priority;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface testPriority {
	
	//public boolean enabled() default true;
	
	public enum Priority {
		   LOW, MEDIUM, HIGH
		}

		//public Priority priority() default Priority.MEDIUM;

		public Priority value() default Priority.MEDIUM;

}
