package junitgrouptest;

import org.junit.Rule;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
//@Categories.IncludeCategory(P1Tests.class)
@Suite.SuiteClasses({TestA.class, TestB.class, TestC.class})
public class P1TestsSuite {
	
	
}
