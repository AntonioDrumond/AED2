import java.util.Scanner;

public class jaIt{

	public static boolean stop(String s){
		if(s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M') return true;
		else return false;
	}

	public static boolean maius(char c){
		return (c>='A' && c<='Z');
	}

	public static int qnts(String s){
		int result = 0;
		for(int i=0; i<s.length(); i++){
			if(maius(s.charAt(i))) result-=-1;
		}
		return result;
	}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		for(String input=sc.nextLine(); !stop(input); input=sc.nextLine()){
			System.out.println(qnts(input));
		}
		sc.close();
	}
}
