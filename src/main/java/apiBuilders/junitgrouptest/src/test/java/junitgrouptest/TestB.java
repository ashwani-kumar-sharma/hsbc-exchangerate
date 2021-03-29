package junitgrouptest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import junitgrouptest.testPriority.Priority;

public class TestB {

	@Rule
	public TestPriorityRuleRead testPrio = new TestPriorityRuleRead();
	
	@Test
	//@Category(P1Tests.class)
	@testPriority(Priority.HIGH)
	public void bTest1() {
		System.out.println("P1_Test");
	}

	@Test
	//@Category(P2Test.class)
	@testPriority(Priority.HIGH)
	public void bTest2() {
		System.out.println("P2_Test");
	}
}
