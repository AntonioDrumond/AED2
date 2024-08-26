import java.util.Scanner;

public class algBool{

	public static boolean stop(String s){
		return (s.length()==1 && s.charAt(0)=='0');
	}

	public static String sbs(String s, int start, int end){
		String result = new String("");
		for(int i=start; i<end; i++){
			result += s.charAt(i);
		}
		return result;
	}

	public static String simplify(String s, boolean[] bools){
		int rem = ((bools.length + 1)*2)-1;
		s = sbs(s, rem, s.length());
		String result = new String("");
		for(int i=0; i<s.length(); i++){
			char x = s.charAt(i);
			if(x=='A') result += ((bools[0])?1:0);
			else if(x=='B') result += ((bools[1])?1:0);
			else if(x=='C') result += ((bools[2])?1:0);
			else if(x=='a'){
				result += 'a';
				i+=2;
			}
			else if(x=='o'){
				result += 'o';
				i++;
			}
			else if(x=='n'){
				result += '!';
				i+=2;
			}
			else if(x!=' ' && x!=',') result += x;
		}
		return result;
	}


	public static boolean[] getBools(String s){
		int nBools = (int)s.charAt(0)-'0';
		boolean[] bools = new boolean[nBools];
		boolean result = true;
		int j = 0;
		for(int i=1; i<s.length(); i++){
			if(s.charAt(i)=='1'){
				bools[j] = true;
				j++;
			}
			else if(s.charAt(i)=='0'){
				bools[j] = false;
				j++;
			}
		}
		return bools;
	}

	public static char solve(String s){
		Stack pilha = new Stack();
		int i = 0;
		while(i<s.length()){
			char x = s.charAt(i);
			char aux = ' ';
			if(x!=')')
				pilha.add(s.charAt(i));
			else{
				String subEx = new String("");
				do{
					subEx += pilha.remove();
				} while(subEx.charAt(subEx.length()-1) != '(');
				char operation = pilha.remove();
				pilha.add(operate(operation, subEx));
			}
			i++;
		}
		return pilha.top.data;
	}

	public static char operate(char operation, String expr){
		if(operation=='!'){
			char result = '0';
			for(int i=0; i<expr.length(); i++){
				if(expr.charAt(i)=='1'){
					i = 100;
					result = '0';
				}
				else if(expr.charAt(i)=='0'){
					i = 100;
					result = '1';
				}
			}
			return result;
		}

		else if(operation=='a'){
			char result = '1';
			for(int i=0; i<expr.length(); i++){
				if(expr.charAt(i)=='0'){
					i = 100;
					result = '0';
				}
			}
			return result;
		}

		else{
			char result = '0';
			for(int i=0; i<expr.length(); i++){
				if(expr.charAt(i)=='1'){
					i = 100;
					result = '1';
				}
			}
			return result;
		}
	}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String s = null;
		boolean work = true;
		while(work){
			s = sc.nextLine();
			if(stop(s)) work = false;
			//else System.out.println(simplify(s, getBools(s)));
			else{
				String simplificada = simplify(s, getBools(s));
				//System.out.println(simplificada);
				System.out.println(solve(simplificada));
			}
		}
	}
}

class Cell{
	public char data;
	public Cell next;

	Cell(){
		data = 0;
		next = null;
	}

	Cell(char x){
		data = x;
		next = null;
	}
}

class Stack{
	public Cell top;

	Stack(){
		top = null;
	}

	Stack(char x){
		top = new Cell(x);
	}

	public void add(char x){
		Cell tmp = new Cell(x);
		tmp.next = top;
		top = tmp;
	}

	public char remove(){
		if(top==null) return '2';
		else{
			char backup = top.data;
			Cell tmp = top;
			top = top.next;
			tmp.next = null;
			return backup;
		}
	}
}
