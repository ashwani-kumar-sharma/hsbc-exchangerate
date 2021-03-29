package hsbc.api.utilities;

import java.util.Arrays;

public class MasterCard {
	
	int num = 175;
	
	public boolean comaperDigit(int num) {
		int digit = num - num/10;
		double sum = 0;
		int i = Integer.toString(num).length();
		while(digit !=0) {
			sum = sum +  Math.pow(digit,i);
			digit = num - num/10;
			i = i--;
		}
		
		return true;
	}
	
	public boolean comaperStrings(String one, String sec){
		String str[] = one.split("");
		String strSec[] = sec.split("");
		Arrays.sort(strSec);
		Arrays.sort(str);
		
		int count = str.length;
		boolean isFound = false;
		if(str.length == strSec.length){
			for(String ch : str){
				for(String ch1 : strSec){
					if(ch.equals(ch1)){
						count--;
						isFound = true;
						break;
					}
				}
				if(!isFound)
					break;
			}
		}
		if(count == 0){
			return true;
		}else
			return false;
	}
	
	public static void main(String args[]) {
		MasterCard msObj = new MasterCard();
		System.out.println(msObj.comaperStrings("cat", "tac"));
	}
}
