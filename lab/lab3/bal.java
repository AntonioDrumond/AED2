import java.util.Scanner;

public class bal{

	public static boolean stop(String s){
		return (s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M');
	}

	public static boolean check(String s){
		int value = 0;
		boolean result = true;
		for(int i=0; i<s.length() && result; i++){
			if(s.charAt(i)=='(') value++;
			else if(s.charAt(i)==')') value--;
			if(value<0) result = false;
		}
		return value==0 && result;
	}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		boolean work = true;
		String s;
		while(work){
			s = sc.nextLine();
			if(stop(s)) work = false;
			else if(check(s)) System.out.println("correto");
			else System.out.println("incorreto");
		}
	}
}
