package apiConfigs;

public class APIPath {
	
	public static final class apiExchangePath{
		
		private static final String API = "api/";
		private static final String LATEST = "/latest";
		private static final String GET_REFERENCE_RATES = "HTTP/2";
		private static final String GET_SYMBOLS = "symbols=";
		private static final String GET_BASE = "base=";
		
		public static String getLatestBase(){
			return LATEST + GET_BASE;
		}
		
		public static String getLatest(){
			return LATEST;
		}
		
		public static String getPastConversionBase(String date) {
			return API + date + GET_BASE;
		}
	}	
}
