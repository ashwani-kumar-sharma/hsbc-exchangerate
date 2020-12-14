
package Runner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import hsbc.api.utilities.BaseTest;
import hsbc.api.utilities.FrameWorkConstants;

@RunWith(Cucumber.class)
@CucumberOptions( plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/extent-report/report.html"},features="src/test/java/Features",glue= {"stepDefinitions"},tags="@Test")

public class TestRunner extends BaseTest{	
	
	@AfterClass
    public static void writeExtentReport() {
		Reporter.loadXMLConfig(new File(FrameWorkConstants.EXTENT_REPORTS_CONFIG));
       
    }
}
