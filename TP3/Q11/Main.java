import java.util.Scanner;

class Cell{

	public int data;
	public Cell up;
	public Cell left;
	public Cell right;
	public Cell down;

	Cell(int x){
		data = x;
		up = null;
		left = null;
		right = null;
		down = null;
	}

	Cell(){
		data = 0;
		up = null;
		left = null;
		right = null;
		down = null;
	}
	
}

class Matriz{
	
	public Cell start;
	public int l;
	public int c;

	Matriz(int l, int c){
		this.l = l;
		this.c = c;
		start = new Cell();
		Cell tmp = start;
		for(int i=1; i<c; i++){
			tmp.right = new Cell();
			tmp.right.left = tmp;
			tmp = tmp.right;
		}
		for(int i=1; i<l; i++){
			this.newLine();
		}
	}

	Matriz(){
		this(5,5);
	}

	public void newLine(){
		Cell tmp = start;
		while(tmp.down != null) tmp = tmp.down;
		Cell tmp1 = new Cell();
		tmp.down = tmp1;
		tmp1.up = tmp;
		while(tmp.right != null){
			tmp = tmp.right;
			tmp1.right = new Cell();
			tmp1.right.left = tmp1;
			tmp1 = tmp1.right;
			tmp.down = tmp1;
			tmp1.up = tmp;
		}
	}

	public void print(){
		Cell tmp = start;
		while(tmp != null){
			Cell tmp1 = tmp;
			while(tmp1 != null){
				System.out.print(tmp1.data + " ");
				tmp1 = tmp1.right;
			}
			System.out.println("");
			tmp = tmp.down;
		}
	}

	public static Matriz read(int l, int c, Scanner sc){
		Matriz ret = new Matriz(l, c);
		//Scanner sc = new Scanner(System.in);
		Cell tmp = ret.start;
		while(tmp != null){
			Cell tmp1 = tmp;
			while(tmp1 != null){
				int t = sc.nextInt();
				tmp1.data = t;
				//tmp1.data = sc.nextInt();
				tmp1 = tmp1.right;
			}
			tmp = tmp.down;
		}
		return ret;
	}

	public Matriz soma(Matriz other){
		Matriz ret = new Matriz(this.l, this.c);
		Cell tmp = this.start;
		Cell omp = other.start;
		Cell rmp = ret.start;
		while(tmp != null && omp != null){
			Cell tmp1 = tmp;
			Cell omp1 = omp;
			Cell rmp1 = rmp;
			while(tmp1 != null && omp1 != null){
				rmp1.data = tmp1.data + omp1.data;
				tmp1 = tmp1.right;
				rmp1 = rmp1.right;
				omp1 = omp1.right;
			}
			tmp = tmp.down;
			omp = omp.down;
			rmp = rmp.down;
		}
		return ret;
	}

	public void diagMain(){
		Cell tmp = start;
		System.out.print(tmp.data + " ");
		tmp = tmp.right;
		if(tmp!=null) tmp = tmp.down;
		while(tmp != null){
			System.out.print(tmp.data + " ");
			tmp = tmp.right;
			if(tmp!=null) tmp = tmp.down;
		}
		System.out.println("");
	}

	public void diagSec(){
		Cell tmp = start;
		while(tmp.right != null) tmp = tmp.right;
		System.out.print(tmp.data + " ");
		tmp = tmp.left;
		if(tmp!=null) tmp = tmp.down;
		while(tmp != null){
			System.out.print(tmp.data + " ");
			tmp = tmp.left;
			if(tmp!=null) tmp = tmp.down;
		}
		System.out.println("");
	}

	public Matriz prod(Matriz other){
	}


}


public class Main{

	/*public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		Matriz m = Matriz.read(4, 4, sc);
		m.diagMain();
	}*/

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int nMats = 2 * sc.nextInt();
		Matriz[] mats = new Matriz[nMats];
		for(int i=0; i<nMats; i++){
			int l = sc.nextInt();
			int c = sc.nextInt();
			mats[i] = Matriz.read(l, c, sc);
		}

		/*
		for(int i=0; i<nMats; i++){
			mats[i].print();
			System.out.println("");
		}
		*/

		int cases = nMats / 2;
		for(int i=0; i<cases; i++){
			mats[i].diagMain();
			mats[i].diagSec();
			mats[i].soma(mats[i+1]).print();
		}

	}

}
