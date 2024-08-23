import java.util.Scanner;

public class espelho{
	
	public static String espe(int x, int y){
		String result = new String("");
		int crr = x;
		while(crr<=y){
			result += crr;
			crr++;
		}
		for(int i=result.length()-1; i>=0; i--){
			result += result.charAt(i);
		}
		return result;
	}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int x = 0;
		int y = 0;
		while(sc.hasNext()){
			x = sc.nextInt();
			y = sc.nextInt();
			String result = espe(x, y);
			System.out.println(result);
		}
	}
}
