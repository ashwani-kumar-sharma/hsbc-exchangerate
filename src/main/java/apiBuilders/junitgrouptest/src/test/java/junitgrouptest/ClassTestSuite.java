package junitgrouptest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junitgrouptest.testPriority.Priority;

@RunWith(Suite.class)
@SuiteClasses({ TestA.class, TestB.class, TestC.class })
public class ClassTestSuite {

}
