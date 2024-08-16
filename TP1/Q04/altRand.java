import java.util.Scanner;
import java.util.Random;

public class altRand{
	public static boolean stop(String s){
		if(s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M') return true;
		else return false;
	}

	public static String alter(String s, Random rnd){
		char target = (char) ('a' + Math.abs(rnd.nextInt())%26);
		char nc = (char) ('a' + Math.abs(rnd.nextInt())%26);
		String result = new String("");
		for(int i=0; i<s.length(); i++){
			if(s.charAt(i)==target) result += nc;
			else result += s.charAt(i);
		}
		return result;
	}

	public static void main(String[] args){
		Random rnd = new Random();
		rnd.setSeed(4);
		Scanner scan = new Scanner(System.in);
		String input = null;
		boolean work = true;
		while(work){
			input = scan.nextLine();
			if(stop(input)) work = false;
			else System.out.println(alter(input, rnd));
		}
	}
}
