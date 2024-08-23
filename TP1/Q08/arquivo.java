import java.io.IOException;
import java.io.RandomAccessFile;

public class arquivo{

	public static int toi(String s){
		int result = 0;
		int j = s.length();
		for(int i=0; i<s.length(); i++){
			j--;
			result += Math.pow(10, j) * (int)(s.charAt(i)-48);
		}
		return result;
	}

	public static int commaIndex(String s){
		boolean search = true;
		int i = -1;
		int reps = s.length()-1;
		while(i<reps && search){
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
		else System.out.printf("%.3f\n", tob(s, comma));
	}

	public static void reader(RandomAccessFile fil, int i){
		if(i>0){
			try{
				String s = fil.readLine();
				reader(fil, i-1);
				printValue(s);
			} catch(IOException e){
				System.out.println("Ocorreu um erro");
			}
		}
	}

	/*public static void main(String[] args){
		try{
			RandomAccessFile fil = new RandomAccessFile("pub.in", "rw");
			int i = toi(fil.readLine());
			reader(fil, i);
		} catch(IOException e){
			System.out.println("Ocorreu um erro");
		}
	}*/

	public static void main(String[] args){
		try{
			RandomAccessFile fil = new RandomAccessFile("my.out", "rw");
			int reps = toi(MyIO.readLine());
			for(int i=0; i<reps; i++){
				double x = MyIO.readDouble();
				fil.writeDouble(x);
			}
			fil.close();
			fil = new RandomAccessFile("my.out", "r");
			for(int i=reps-1; i>=0; i--){
				fil.seek(i*8);
				double real = fil.readDouble();
				int inte = (int)real;
				if(real==inte) MyIO.println(inte);
				else MyIO.println(real);
			}
			fil.close();
		} catch(IOException e){
			MyIO.println("Ocorreu um erro");
		}
	}

	/*public static void main(String[] args){
		printValue("810");
		System.out.println(toi("51"));
		printValue("31.14");
		try{
			RandomAccessFile fil = new RandomAccessFile("pub.in", "rw");
			int i = toi(fil.readLine());
			System.out.println(i);
		} catch(IOException e){
			System.out.println("erro");
		}
	}*/
}
