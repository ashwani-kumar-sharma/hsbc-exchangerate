package example;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

public class NewTest {

	private WebDriver driver;		
	@Test				
	public void testEasy() {	
		driver.get("http://www.guru99.com/selenium-tutorial.html");  
		String title = driver.getTitle();				 
		Assert.assertTrue("Title on the webpage not found as expected",title.contains("Free Selenium Tutorials")); 		
	}
	@Test
	public void additionNumber(){
		Assert.assertTrue("Addition of two number passed", true);
	}
	@Before
	public void beforeTest() {	
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\ashwani.kumar.sharma\\Downloads\\geckodriver-v0.11.1-win64\\geckodriver.exe");
		File pathToBinary = new File("C:\\Users\\ashwani.kumar.sharma\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile firefoxProfile = profile.getProfile("Firefox TestProfile");//new FirefoxProfile(pathToFFProfile);     
		
		driver = new FirefoxDriver(ffBinary,firefoxProfile);
	}		
	@After
	public void afterTest() {
		driver.quit();			
	}

}
