package baseUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Login {
	private WebDriver driver;
	private boolean loginObjSuccess;
	private WebElement webObj;
	private PropertiesLoad prop;
	/*By userName = By.name("uid");
    By password = By.name("password");
    By titleText =By.className("barone");
    By signin = By.name("btnLogin");*/
    Login(WebDriver driver){
    	this.driver = driver;
    	this.loginObjSuccess = false;
    	this.prop = PropertiesLoad.getInstance();
    	this.webObj = null;
    }
    private boolean setUserName(String userId){
    	String value = prop.getValueOfProp("username");
    	webObj = driver.findElement(By.name(value));
    	if(webObj == null)
    		return false;
    	else{
    		webObj.clear();
    		//CharSequence ch [] = "ahswani";
    		webObj.sendKeys(userId);
    		return true;
    	}    		
    }
    private boolean setPwd(String pwd){
    	String value = prop.getValueOfProp("password");
    	webObj = driver.findElement(By.name(value));
    	if(webObj == null)
    		return false;
    	else{
    		webObj.clear();
    		//CharSequence ch [] = "ahswani";
    		webObj.sendKeys(pwd);
    		return true;
    	}
    }
    private boolean clickSigninBtn(){
    	String value = prop.getValueOfProp("sign-in");
    	webObj = driver.findElement(By.name(value));
    	if(webObj == null)
    		return false;
    	else{
    		webObj.click();
    		return true;
    	}
    }
    public boolean loginToApp(String userID, String pwd){
    	loginObjSuccess = setUserName(userID);
    	if(loginObjSuccess == false)
    		return false;
    	loginObjSuccess = setPwd(pwd);
    	if(loginObjSuccess == false)
    		return false;
    	loginObjSuccess = clickSigninBtn();
    	if(loginObjSuccess == false)
    		return false;
    	return loginObjSuccess;    	
    }
}
