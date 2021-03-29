package junitgrouptest;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(P2Test.class)
@Suite.SuiteClasses({TestA.class, TestB.class, TestC.class})
public class P2TestsSuite {

}
