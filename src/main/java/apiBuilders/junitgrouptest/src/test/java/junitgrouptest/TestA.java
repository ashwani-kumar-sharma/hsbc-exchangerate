package junitgrouptest;

import java.awt.AWTException;
import java.awt.Robot;
import java.lang.reflect.Executable;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import junitgrouptest.testPriority.Priority;

public class TestA {
	
	@Rule
	public TestPriorityRuleRead testPrio = new TestPriorityRuleRead();
	
	public TestA() throws AWTException {
		// TODO Auto-generated constructor stub
	}
	Robot roboObj = new Robot();
	@Test
	//@Category(P1Tests.class)
	@testPriority(Priority.HIGH)
	public void aTest1() {
		System.out.println("P1_Test");
	}

	
	@Test
	@testPriority(Priority.MEDIUM)
	public void aTest2() {
		System.out.println("No Category_Test");
	}

}
