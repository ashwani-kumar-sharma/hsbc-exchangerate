package hsbc.api.utilities;

import java.util.HashMap;
import java.util.Map;

public class Preparation {
	
	String str = "find dublicates in a string";
	
	String numAlpha = "123ashwani345";
	
	HashMap<String,Integer> freq = new HashMap<String, Integer>();
	public Preparation(){
		System.out.println(getClass().getSuperclass().toString());
	}
	public void getDuplicate() {
		String text[] = str.split(" ");
		for(String word : text) {
			if(freq.get(word) != null) {
				freq.put(word, freq.get(word) + 1);
			}else
				freq.put(word, 1);
		}
		
		for(Map.Entry<String,Integer> word: freq.entrySet()) {
			if(word.getValue() > 1)
				System.out.println(word.getKey());
		}
	}

	public static void main(String args[]) {
		Preparation prepObj = new Preparation();
		
	}
}
