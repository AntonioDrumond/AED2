import java.io.IOException;
import java.io.RandomAccessFile;

public class ReadingFile{
	
	public static void main(String[] args){
		try{
			RandomAccessFile fil = new RandomAccessFile("bar.txt", "rw");
			String dentro;
			dentro = fil.readLine();
			System.out.println("dentro: " + dentro);
			System.out.println("filePointer: " + fil.getFilePointer());
			fil.seek(fil.getFilePointer()+5);
			String eh = fil.readLine();
			System.out.println("pulou? " + eh);
			System.out.println("pointer btw: " + fil.getFilePointer());
			fil.close();
		} catch(IOException e){
			System.out.println("Deu erro dog");
		}
	}
}
