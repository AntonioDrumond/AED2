import java.util.Scanner;

public class cesarRec{
	public static boolean stop(String s){
		if(s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M') return true;
		else return false;
	}

	public static String encrypt(String s){
		String result = new String("");
		return encrypt(s, result);
	}

	public static String encrypt(String s, String result){
		int i = result.length();
		if(s.length()>i){
			result = result + (char)(s.charAt(i)+3);
			return encrypt(s, result);
		}
		else return result;
	}

	public static void main(String[] args){
		//Scanner sc = new Scanner(System.in);
		String input = null;
		boolean work = true;
		while(work){
			//input = sc.nextLine();
			input = MyIO.readLine();
			if(stop(input)) work = false;
			//else System.out.println(encrypt(input));
			else MyIO.println(encrypt(input));
		}
	}
}
