import java.util.Scanner;

public class isRec{

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
		return vogal(s, 0);
	}
	
	public static boolean vogal(String s, int i){
		boolean check = (vog(s.charAt(i)));
		if(!check) return false;
		else if(i<s.length()-1) return vogal(s, i+1);
		else return true;
	}

	public static boolean consoante(String s){
		return consoante(s, 0);
	}

	public static boolean consoante(String s, int i){
		char x = s.charAt(i);
		boolean check = isLetter(x) && !vog(x) && !isNumber(x);
		if(!check) return false;
		else if(i<s.length()-1) return consoante(s, i+1);
		else return true;
	}

	public static boolean inteiro(String s){
		return inteiro(s, 0);
	}

	public static boolean inteiro(String s, int i){
		boolean check = isNumber(s.charAt(i));
		if(!check) return false;
		else if(i<s.length()-1) return inteiro(s, i+1);
		else return true;
	}

	public static boolean real(String s){
		return real(s, 0, 0);
	}

	public static boolean real(String s, int i, int commaCounter){
		int counter = commaCounter;
		char x = s.charAt(i);
		if(x=='.' || x==',') counter++;
		boolean check = !((!isNumber(x) && x!=',' && x!='.') || counter>1);
		if(!check) return false;
		else if(i<s.length()-1) return real(s, i+1, counter);
		else return true;
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
