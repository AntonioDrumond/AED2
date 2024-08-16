import java.util.Scanner;

public class is{
	public static boolean stop(String s){
		if(s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M') return true;
		else return false;
	}

	public static boolean vog(char c){
		return (c=='a' || c=='e' || c=='i' || c=='o' || c=='u' || c=='A' || c=='E' || c=='I' || c=='O' || c=='U');
	}

	public static boolean isLetter(char c){
		return (c>='a' && c<='z') || (c>='A' || c<='Z');
	}

	public static boolean isNumber(char c){
		return (c>='0' && c<='9');
	}

	public static boolean vogal(String s){
		boolean found = false;
		for(int i=0; i<s.length() && !found; i++){
			if(!vog(s.charAt(i))) found = true;
		}
		return !found;
	}

	public static boolean consoante(String s){
		boolean found = false;
		for(int i=0; i<s.length() && !found; i++){
			char x = s.charAt(i);
			if(!isLetter(x) || vog(x) || isNumber(x)) found = true;
		}
		return !found;
	}
	
	public static boolean inteiro(String s){
		boolean found = false;
		for(int i=0; i<s.length() && !found; i++){
			char x = s.charAt(i);
			if(!isNumber(x)) found = true;
		}
		return !found;
	}

	public static boolean real(String s){
		boolean found = false;
		int commaCounter = 0;
		for(int i=0; i<s.length() && !found; i++){
			char x = s.charAt(i);
			if(x=='.' || x==',') commaCounter++;
			if((!isNumber(x) && x!=',' && x!='.') || commaCounter>1) found = true;
		}
		return !found;
	}

	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String input = null;
		boolean work = true;
		while(work){
			input = sc.nextLine();
			if(stop(input)) work = false;
			else{
				if(vogal(input)) System.out.print("SIM ");
				else System.out.print("NAO ");
				if(consoante(input)) System.out.print("SIM ");
				else System.out.print("NAO ");
				if(inteiro(input)) System.out.print("SIM ");
				else System.out.print("NAO ");
				if(real(input)) System.out.println("SIM");
				else System.out.println("NAO");
			}
		}
	}
}
