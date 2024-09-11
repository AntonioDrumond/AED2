#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include<string.h>

void selSort(int* arr, int m, int n){
	for(int i=0; i<(n-1); i++){
		int menPos = i;
		for(int j=i+1; j<n; j++){
			if(arr[menPos]%m > arr[j]%m){
				menPos = j;
				//printf("cond1\n");
			}
			else if( (arr[menPos]%m == arr[j]%m) && arr[menPos]%2==0 && arr[j]%2!=0 ){
				menPos = j;
				//printf("cond2\n");
			}
			else if( (arr[menPos]%m == arr[j]%m) && arr[menPos]%2!=0  && arr[j]%2!=0 && arr[menPos]<arr[j] ){
				menPos = j;
				//printf("cond3\n");
			}
			else if( (arr[menPos]%m == arr[j]%m) && arr[menPos]%2==0  && arr[j]%2==0 && arr[menPos]>arr[j] ){
				menPos = j;
				//printf("cond4\n");
			}
		}
		int tmp = arr[menPos];
		arr[menPos] = arr[i];
		arr[i] = tmp;
	}
}

int main(){
	int reps = 0,
		m = 0;
	scanf(" %d %d", &reps, &m);
	while(reps!=0 && m!=0){
		int* arr = malloc(reps * sizeof(int));
		for(int i=0; i<reps; i++){
			scanf(" %d", &arr[i]);
		}
		selSort(arr, m, reps);
		printf("%d %d\n", reps, m);
		for(int i=0; i<reps; i++){
			printf("%d\n", arr[i]);
		}
		scanf(" %d %d", &reps, &m);
	}
	printf("0 0 ");
	return 0;
}
