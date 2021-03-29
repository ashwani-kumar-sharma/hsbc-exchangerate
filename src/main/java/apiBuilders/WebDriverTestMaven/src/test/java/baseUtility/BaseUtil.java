package baseUtility;

import java.util.Arrays;
import java.util.Collection;
 
import org.junit.Test;
import org.junit.Before;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BaseUtil {
	
	private String userID;
	private String pwd;
	private InitialSetup initSetup;
	static PropertiesLoad prop;
	//private PrimeNumberChecker primeNumberChecker;

	/*@Before
	public void initialize() {
		primeNumberChecker = new PrimeNumberChecker();
	}*/

	   // Each parameter should be placed as an argument here
	   // Every time runner triggers, it will pass the arguments
	   // from parameters we defined in primeNumbers() method
	
	static class LoginData{
		String userID;
		String password;
		LoginData(String userID, String pwd){
			this.userID = userID;
			this.password = pwd;
		}
	}
		
	public BaseUtil(String userID, String pwd) {
		this.userID = userID;
	    this.pwd = pwd;
	    prop = null;
	    initSetup = new InitialSetup();
	}
	private BaseUtil(){
		
	}
	 /*@Parameterized.Parameters
	   public static Collection primeNumbers() {
	      return Arrays.asList(new Object[][] {
	         { 2, true },
	         { 6, false },
	         { 19, true },
	         { 22, false },
	         { 23, true }
	      });
	 }*/
	 
	@Parameterized.Parameters
	   public static Collection primeNumbers() {
		prop = PropertiesLoad.getInstance();
		return getUsers(prop.getValueOfProp("loginvalues"));
	 }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Collection getUsers(String userList){
		String []users = userList.split("#");
		String userDetails;
		String []userIdPwd;
		
		Object[][] loginData = new Object[users.length][];
		for(int j = 0; j<users.length;j++){
			userDetails = users[j];
			userIdPwd = userDetails.split("@");
			loginData[j] = new Object[userIdPwd.length];
			for(int i = 0; i<userIdPwd.length; i++ ){
				loginData[j][i] = userIdPwd[i];
				i++;
				loginData[j][i] = userIdPwd[i];
			}			
		}
		return Arrays.asList(loginData);
		//return arrLoginData;		
	}
	@Test
	 public void testPrimeNumberChecker() {
		 System.out.println("Parameterized Number is : " + "Base Util");
	     assertEquals("Base Util",true);
	 }
}
