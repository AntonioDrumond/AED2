import java.io.RandomAccessFile;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MakingFile{

	public static void main(String[] args){
		Random rnd = new Random();
		rnd.setSeed(4);
		try{
			FileWriter fil = new FileWriter("foo.txt");
			for(int i=0; i<Math.pow(2,6); i++){
				fil.write(Math.abs(rnd.nextInt()%150) + "\n");
			}
			fil.close();
		} catch(IOException e){
			System.out.println("deu merda dog");
		}
	}
}
