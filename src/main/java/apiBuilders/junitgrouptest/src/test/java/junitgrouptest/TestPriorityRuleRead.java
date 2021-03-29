package junitgrouptest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import junit.framework.Test;
import junitgrouptest.testPriority.Priority;

public class TestPriorityRuleRead implements TestRule{
	
	public Statement apply(final Statement base, final Description description){
		
		System.out.println(description.getMethodName());
		testPriority tPriority = description.getAnnotation(testPriority.class);
		if(tPriority!= null){
			System.out.println("Priority of Test Case="+tPriority.value().toString());
			if(tPriority.value().equals(Priority.HIGH))
	        	return base;
			else if(tPriority.value() == null){
				return new Statement() {
					
					@Override
					public void evaluate() throws Throwable {
						System.out.println("No Priority Found Setting Default Priority ="+description.getMethodName());
						 Assume.assumeFalse("No Priority Found Setting Default Priority",true);				
					}
				};
			}
			else
				return new Statement() {
				
				@Override
				public void evaluate() throws Throwable {
					System.out.println("Skipping Test case name="+description.getMethodName());
					 Assume.assumeFalse("Test Case being Ignored",true);				
				}
			};
		}else {
			return base;
		}
	}
}
