package dstu.nechay;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
  
public class Laba1 {
	public static int findLexCode(String alphabet,String word) throws Exception  {
		if(word.equals("")) return 0;
		final String alpha = Arrays.asList(alphabet.split(""))
                .stream()
                .distinct()
                .collect(Collectors.joining());
		for(char x: word.toCharArray()) {
			if(alphabet.indexOf(x)==-1) {
				throw new Exception("Слово содержит букву не из алфавита");
			}
		}
		AtomicInteger counter=new AtomicInteger(0);
		return word.chars().reduce(0,(sum, ch) -> 
								sum+
								(int)Math.pow(alpha.length(),
											word.length()-counter.getAndIncrement()-1)
								*
								(alpha.indexOf((char)ch)+1));
	}
	public static String findWord(String alphabet,int code) {
		if(code==0) return "";
		final String alpha = Arrays.asList(alphabet.split(""))
                .stream()
                .distinct()
                .collect(Collectors.joining());
		int k=code;
		int r=0;
		List<Integer> list = new ArrayList<Integer>();
		do {
			r=k%alpha.length();
			if(r==0) {
				r=alpha.length();
				k=k/alpha.length()-1;
			}else {
				k=k/alpha.length();
			}
			System.out.println(r + " * " + k);
			list.add(0,r);			
		}while(k>=alpha.length());
		if(r!=0) list.add(0,k);	
		System.out.println(k);
		return 	list
				.stream()
	            .map(x ->((Character) alpha.charAt(x-1)).toString())
	    		.reduce((a,b) -> a +b)
	    		.get();

	}
	public static void zakod() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Введите алфавит");
		String alph = sc.nextLine();
		System.out.println("Введите слово");
		String word = sc.nextLine();
		try {
			System.out.println("Код данного слова: " + findLexCode(alph,word));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void raskod() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Введите алфавит");
		String alph = sc.nextLine();
		System.out.println("Введите код");
		int code = sc.nextInt();
		try {
			System.out.println("Слово: " + findWord(alph,code));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
