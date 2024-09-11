import java.util.Scanner;

public class Sort{
	
	public static void selSort(int[] arr, int m){
		int n = arr.length;
		for(int i=0; i<(n-1); i++){
			int menPos = i;
			for(int j=i+1; j<n; j++){
				if(arr[menPos]%m > arr[j]%m){
					menPos = j;
				}
				else if( (arr[menPos]%m == arr[j]%m) && arr[menPos]%2==0 && arr[j]%2!=0 ){
					menPos = j;
				}
				else if( (arr[menPos]%m == arr[j]%m) && arr[menPos]%2!=0  && arr[j]%2!=0 && arr[menPos]<arr[j] ){
					menPos = j;
				}
				else if( (arr[menPos]%m == arr[j]%m) && arr[menPos]%2==0  && arr[j]%2==0 && arr[menPos]>arr[j] ){
					menPos = j;
				}
			}
			int tmp = arr[menPos];
			arr[menPos] = arr[i];
			arr[i] = tmp;
		}
	}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int reps = sc.nextInt();
		int m = sc.nextInt();
		while(reps!=0 && m!=0){
			int[] arr = new int[reps];
			for(int i=0; i<reps; i++){
				arr[i] = sc.nextInt();
			}
			selSort(arr, m);
			System.out.printf("%d %d\n", reps, m);
			for(int i=0; i<reps; i++){
				System.out.println(arr[i]);
			}
			reps = sc.nextInt();
			m = sc.nextInt();
		}
		System.out.printf("0 0 ");
	}
}

// Custo do algoritmo
// Utilizando notacao LaTeX
/*

	\sum^{n-2}_{0}{(n-i)}

	8 * ( \sum^{n-2}_{0}{n} - \sum^{n-2}_{0}{i} )

	8 * ( n((n-2)-0+1) - \frac{(n-2)(n-2+1)}{2} )

	8 / 2 * (n^2 - 5n + 2)

	4n^2 - 20n + 8

	Complexidade: \Theta(n^2)

*/
