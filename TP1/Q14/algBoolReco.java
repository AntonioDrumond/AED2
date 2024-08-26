import java.util.Scanner;

public class algBoolReco{

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
		return simplify(s, bools, 0);
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

	public static String simplify(String s, boolean[] bools, int i){
		if(i>=s.length()) return "";
		else{
			char x = s.charAt(i);
			if(x=='A') return ((bools[0])?1:0) + simplify(s, bools, i+1);
			else if(x=='B') return ((bools[1])?1:0) + simplify(s, bools, i+1);
			else if(x=='C') return ((bools[2])?1:0) + simplify(s, bools, i+1);
			else if(x=='a') return "a" + simplify(s, bools, i+3);
			else if(x=='o') return "o" + simplify(s, bools, i+2);
			else if(x=='n') return "!" + simplify(s, bools, i+3);
			else if(x!=' ' && x!=',') return x + simplify(s, bools, i+1);
			else return simplify(s, bools, i+1);
		}
	}

	public static char solve(String s){
		Stack pilha = new Stack();
		return solve(s, pilha, 0);
	}

	public static char solve(String s, Stack pilha, int i){
		if(i<s.length()){
			char x = s.charAt(i);
			if(x!=')') pilha.add(x);
			else{
				String subEx = new String("");
				do{
					subEx += pilha.remove();
				} while(subEx.charAt(subEx.length()-1) != '(');
				char operation = pilha.remove();
				pilha.add(operate(operation, subEx));
			}
			//pilha.print();
			return solve(s, pilha, i+1);
		}
		else return pilha.top.data;
	}

	static char operate(char operation, String expr){
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

	public void print()
	{
		Cell aux = this.top;
		printRec(aux);
		System.out.println("");
	}

	public void printRec(Cell aux)
	{
		if(aux != null)
		{
			printRec(aux.next);
			System.out.print(aux.data);
		}
	}
}
