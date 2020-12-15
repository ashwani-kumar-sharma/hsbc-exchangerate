//*********************************************************************************************************************************************
package hsbc.api.utilities;

import org.apache.log4j.PropertyConfigurator;

//*********************************************************************************************************************************************
public class Logger {
	private static boolean root = false;

	// *************************************************************************************************************************************
	// Function Objective:configure log4j logger
	// *************************************************************************************************************************************
	public static Logger getLogger(Class<?> cls) {
		if (root) {
			return Logger.getLogger(cls);
		}
		PropertyConfigurator.configure("log4j.properties");
		root = true;
		return Logger.getLogger(cls);
	}
}
//*********************************************************************************************************************************************