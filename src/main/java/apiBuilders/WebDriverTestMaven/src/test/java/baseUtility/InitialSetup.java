package baseUtility;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

enum BROWSERTYPE{Firefox,IE,Chrome,Safari}

public class InitialSetup {
	WebDriver driver;
	InitialSetup(){
		this.driver = null;
	}
	@SuppressWarnings("static-access")
	WebDriver OpenBrowser(BROWSERTYPE broType){
		if(broType.Firefox == BROWSERTYPE.Firefox){
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\ashwani.kumar.sharma\\Downloads\\geckodriver-v0.11.1-win64\\geckodriver.exe");
			File pathToBinary = new File("C:\\Users\\ashwani.kumar.sharma\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
			FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile firefoxProfile = profile.getProfile("Firefox TestProfile");//new FirefoxProfile(pathToFFProfile);     
			
			driver = new FirefoxDriver(ffBinary,firefoxProfile);
			loadProperties();
		}
		return driver;
	}
	private void loadProperties(){
		PropertiesLoad.getInstance().loadProperty();
	}
}
