import java.util.Scanner;

public class jaRec{

	public static boolean stop(String s){
		if(s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M') return true;
		else return false;
	}

	public static int qnts(String s){
		return qnts(s, 0);
	}

	public static int qnts(String s, int i){
		if(i>=s.length()) return 0;
		else return qnts(s, i+1) + ((s.charAt(i)>='A' && s.charAt(i)<='Z') ? 1 : 0);
	}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		for(String input=sc.nextLine(); !stop(input); input=sc.nextLine()){
			System.out.println(qnts(input));
		}
		sc.close();
	}
}
