package junitgrouptest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import junitgrouptest.testPriority.Priority;

@Category(P2Test.class)
public class TestC {

	@Rule
	public TestPriorityRuleRead testPrio = new TestPriorityRuleRead();
	
	@Test
	@testPriority()
	public void cTest1() {
		System.out.println("No cat Test");
	}

	@Test
	@testPriority()
	public void cTest2() {
		System.out.println("No Cat Test");
	}
}
