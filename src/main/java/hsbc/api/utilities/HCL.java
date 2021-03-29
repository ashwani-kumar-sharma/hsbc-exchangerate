package hsbc.api.utilities;

import java.util.Arrays;

public class HCL {
	
	
	public boolean match(String str, String str1) {
		String strArr[]  = str.split("");
		String str1Arr[] = str1.split("");
		
		Arrays.sort(strArr);
		Arrays.sort(str1Arr);
		int j=0;
		boolean isMatch = true;
		if(str.length() == str.length()) {
			for(String ch:strArr) {
				if(!ch.equals(str1Arr[j])) {
					isMatch = false;
				}
				j++;
			}
		}else
			isMatch = false;
		
		return isMatch;
	}

}
