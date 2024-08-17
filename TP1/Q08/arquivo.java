import java.io.IOException;
import java.io.RandomAccessFile;

public class arquivo{

	public static int toi(String s){
		int result = 0;
		for(int i=s.length(); i>0; i--){
			result += Math.pow(10, i) * (int)(s.charAt(i-1)-48);
		}
		return result;
	}

	public static int commaIndex(String s){
		boolean search = true;
		int i = -1;
		while(i<s.length() && search){
			i++;
			if(s.charAt(i)==',' || s.charAt(i)=='.') search = false;
		}
		return search ? -1 : i;
	}

	public static double tob(String s, int comma){
		double result = 0.0;
		int com = comma;
		for(int i=0; i<s.length(); i++){
			if(i!=comma){
				com--;
				result += ((int)s.charAt(i)-48) * Math.pow(10, com);
			}
		}
		return result;
	}

	public static void printValue(String s){
		int comma = commaIndex(s);
		if(comma==-1) System.out.println(toi(s));
		else System.out.println(tob(s), comma);
	}

	public static void main(String[] args){
		try{
			RandomAccessFile fil = new RandomAccessFile("pub.in", "rw");
			String s = fil.readLine();
			int reps = toi(s),
				i = 0;
			while(reps>0){
				fil.seek(fil.getFilePointer() - s.length();
				printValue(s);
				reps--;
			}
		} catch(IOException e){
			System.out.println("Ocorreu um erro");
		}
	}
}
