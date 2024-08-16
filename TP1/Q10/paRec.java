import java.util.Scanner;

public class paRec{
	public static boolean stop(String s){
		if(s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M') return true;
		else return false;
	}

	public static boolean pali(String s){
		return pali(s, 0);
	}

	public static boolean pali(String s, int i){
		if(i>=((s.length()/2)-1)) return (s.charAt(i)==s.charAt(s.length()-i-1));
		else return (s.charAt(i)==s.charAt(s.length()-i-1) && pali(s, i+1));
	}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String input = null;
		boolean stop = false;
		while(!stop){
			input = sc.nextLine();
			if(stop(input)) stop = true;
			else if(pali(input)) System.out.println("SIM");
			else System.out.println("NAO");
		}
	}
}
