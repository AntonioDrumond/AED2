import java.util.Random;

class TimeLog{
    private static double startTime = 0;
    private static double endTime = 0;
    private static int totalComp = 0;
    private static int totalMove = 0;

    static void countComp(int x){
        totalComp += x;
    }

    static void countMove(int x){
        totalMove += x;
    }

    public static void startTimer() {
        startTime = System.nanoTime();
    }

    public static void endTimer() {
        endTime = System.nanoTime();
    }

    public static double getTime() {
        return (endTime - startTime) / 1000000;
    }
	
	public static int getMove(){
		return totalMove;
	}
	
	public static int getComp(){
		return totalComp;
	}

}

public class QuickSort{

	static Random pivoR;

	public static void main(String[] args){
		
		//_______Primeiro elemento_______
		System.out.println("Pivo no primeiro elemento:");

		int[] arr = makeArray(10000);
		TimeLog.startTimer();
		QuickSortFirstPivot(arr, 0, arr.length-1);
		TimeLog.endTimer();
		System.out.println("Arranjo com 10000 elementos: " + TimeLog.getTime() + " ms");
		
		arr = makeArray(100000);
		TimeLog.startTimer();
		QuickSortFirstPivot(arr, 0, arr.length-1);
		TimeLog.endTimer();
		System.out.println("Arranjo com 100000 elementos: " + TimeLog.getTime() + " ms");
		
		arr = makeArray(1000000);
		TimeLog.startTimer();
		QuickSortFirstPivot(arr, 0, arr.length-1);
		TimeLog.endTimer();
		System.out.println("Arranjo com 1000000 elementos: " + TimeLog.getTime() + " ms");
		
		arr = makeArray(10000000);
		TimeLog.startTimer();
		QuickSortFirstPivot(arr, 0, arr.length-1);
		TimeLog.endTimer();
		System.out.println("Arranjo com 10000000 elementos: " + TimeLog.getTime() + " ms");
		
		//_______Ultimo elemento_______
		System.out.println("\nPivo no ultimo elemento:");

		arr = makeArray(10000);
		TimeLog.startTimer();
		QuickSortLastPivot(arr, 0, arr.length-1);
		TimeLog.endTimer();
		System.out.println("Arranjo com 10000 elementos: " + TimeLog.getTime() + " ms");
		
		arr = makeArray(100000);
		TimeLog.startTimer();
		QuickSortLastPivot(arr, 0, arr.length-1);
		TimeLog.endTimer();
		System.out.println("Arranjo com 100000 elementos: " + TimeLog.getTime() + " ms");
		
		arr = makeArray(1000000);
		TimeLog.startTimer();
		QuickSortLastPivot(arr, 0, arr.length-1);
		TimeLog.endTimer();
		System.out.println("Arranjo com 1000000 elementos: " + TimeLog.getTime() + " ms");

		arr = makeArray(10000000);
		TimeLog.startTimer();
		QuickSortLastPivot(arr, 0, arr.length-1);
		TimeLog.endTimer();
		System.out.println("Arranjo com 10000000 elementos: " + TimeLog.getTime() + " ms");
		
		//_______Elemento aleatorio_______
		System.out.println("\nPivo em elemento aleatorio:");

		pivoR = new Random(3123);

		arr = makeArray(10000);
		TimeLog.startTimer();
		QuickSortRandomPivot(arr, 0, arr.length-1);
		TimeLog.endTimer();
		System.out.println("Arranjo com 10000 elementos: " + TimeLog.getTime() + " ms");
		
		arr = makeArray(100000);
		TimeLog.startTimer();
		QuickSortRandomPivot(arr, 0, arr.length-1);
		TimeLog.endTimer();
		System.out.println("Arranjo com 100000 elementos: " + TimeLog.getTime() + " ms");
		
		arr = makeArray(1000000);
		TimeLog.startTimer();
		QuickSortRandomPivot(arr, 0, arr.length-1);
		TimeLog.endTimer();
		System.out.println("Arranjo com 1000000 elementos: " + TimeLog.getTime() + " ms");

		arr = makeArray(10000000);
		TimeLog.startTimer();
		QuickSortRandomPivot(arr, 0, arr.length-1);
		TimeLog.endTimer();
		System.out.println("Arranjo com 10000000 elementos: " + TimeLog.getTime() + " ms");
		
		//_______Mediana de tres_______
		System.out.println("\nPivo na mediana de tres elementos:");

		arr = makeArray(10000);
		TimeLog.startTimer();
		QuickSortMedianOfThree(arr, 0, arr.length-1);
		TimeLog.endTimer();
		System.out.println("Arranjo com 10000 elementos: " + TimeLog.getTime() + " ms");
		
		arr = makeArray(100000);
		TimeLog.startTimer();
		QuickSortMedianOfThree(arr, 0, arr.length-1);
		TimeLog.endTimer();
		System.out.println("Arranjo com 100000 elementos: " + TimeLog.getTime() + " ms");
		
		arr = makeArray(1000000);
		TimeLog.startTimer();
		QuickSortMedianOfThree(arr, 0, arr.length-1);
		TimeLog.endTimer();
		System.out.println("Arranjo com 1000000 elementos: " + TimeLog.getTime() + " ms");

		arr = makeArray(10000000);
		TimeLog.startTimer();
		QuickSortMedianOfThree(arr, 0, arr.length-1);
		TimeLog.endTimer();
		System.out.println("Arranjo com 10000000 elementos: " + TimeLog.getTime() + " ms");
		
	}

	public static int[] makeArray(int size){
		Random rnd = new Random();
		rnd.setSeed(1425734251);
		int[] result = new int[size];
		for(int i=0; i<size; i++){
			result[i] = rnd.nextInt();
		}
		return result;
	}

	static void swap(int[] arr, int a, int b){
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

	static void QuickSortFirstPivot(int[] arr, int left, int right){
		int i = left,
			j = right;
		int pivo = arr[left];
		while(i<=j){
			while(arr[i]<pivo)
				i++;
			while(arr[j]>pivo)
				j--;
			if(i<=j){
				swap(arr, i, j);
				i++;
				j--;
			}
		}
		if(left<j)
			QuickSortFirstPivot(arr, left, j);
		if(i<right)
	 		QuickSortFirstPivot(arr, i, right);
	}

	static void QuickSortLastPivot(int[] arr, int left, int right){
		int i = left,
			j = right;
		int pivo = arr[right];
		while(i<=j){
			while(arr[i]<pivo)
				i++;
			while(arr[j]>pivo)
				j--;
			if(i<=j){
				swap(arr, i, j);
				i++;
				j--;
			}
		}
		if(left<j)
			QuickSortLastPivot(arr, left, j);
		if(i<right)
	 		QuickSortLastPivot(arr, i, right);
	}

	static void QuickSortRandomPivot(int[] arr, int left, int right){
		int i = left,
			j = right;
		int pivo = arr[pivoR.nextInt(right-left) + left];
		while(i<=j){
			while(arr[i]<pivo)
				i++;
			while(arr[j]>pivo)
				j--;
			if(i<=j){
				swap(arr, i, j);
				i++;
				j--;
			}
		}
		if(left<j)
			QuickSortRandomPivot(arr, left, j);
		if(i<right)
	 		QuickSortRandomPivot(arr, i, right);
	}

	static int getMedian(int[] arr, int a, int b, int c){
		/*int[] tmp = new int[3];
		tmp[0] = arr[a];
		tmp[1] = arr[b];
		tmp[2] = arr[c];
		if(tmp[0]>tmp[1]) swap(tmp, 0, 1);
		if(tmp[1]>tmp[2]) swap(tmp, 1, 2);
		if(tmp[0]>tmp[1]) swap(tmp, 0, 1);
		return tmp[2];*/
		int res = 0;
		if(arr[a] > arr[b]){
			if (arr[b] > arr[c]) res = arr[b];
			else res = arr[c];
		}
		else {
			if(arr[a] > arr[c]) res = arr[a];
			else res = arr[b];
		}
		return res;
	}

	static void QuickSortMedianOfThree(int[] arr, int left, int right){
		int i = left,
			j = right;
		int pivo = getMedian(arr, left, right, (left+right)/2);
		while(i<=j){
			while(arr[i]<pivo)
				i++;
			while(arr[j]>pivo)
				j--;
			if(i<=j){
				swap(arr, i, j);
				i++;
				j--;
			}
		}
		if(left<j)
			QuickSortLastPivot(arr, left, j);
		if(i<right)
	 		QuickSortLastPivot(arr, i, right);
	}
}
