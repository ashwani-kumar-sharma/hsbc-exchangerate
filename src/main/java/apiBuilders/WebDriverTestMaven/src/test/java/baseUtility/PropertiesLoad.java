package baseUtility;


import java.io.IOException;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoad {
	Properties properties;
	private static PropertiesLoad propLoadObj = null;
	static String propFilePath;
	private static InputStream propTestData,propRes;
	/*"C:\\Users\\ashwani.kumar.sharma\\workspace\\WebDriverTest\\src\\webdriverpkg\\Objectelements.properties";*/
	private PropertiesLoad(){
		properties = new Properties();
		loadProperty();		
	}
	protected void loadProperty(){
		try{
			propTestData = getClass().getResourceAsStream("src/test/resources/testData/testData.properties");
			propRes = getClass().getResourceAsStream("src/test/resources/objectRepository/resources.properties");
			properties.load(propTestData);
			properties.load(propRes);
			  //properties.load(new FileInputStream(propFilePath));
			  System.out.println("Property File Load Successfully");
		} catch (IOException e) {
			System.out.println(e.toString());	
			System.out.println("Property File Load Failed");
		}
	}
	String getValueOfProp(String key){
		return properties.getProperty(key);
	}
	public static PropertiesLoad getInstance(){
		if (propLoadObj == null){
			synchronized(PropertiesLoad.class){  
				propLoadObj = new PropertiesLoad();
			}
		}
		return propLoadObj;
	}
}
