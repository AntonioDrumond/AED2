#ifndef SELECTION_H
#define SELECTION_H

#include "swap.h"

void selectionSort(int* arr, int n){
	int reps = n-1;
	for(int i=0; i<reps; i++){
		int menor = i;
		for(int j=i+1; j<n; j++){
			if(arr[menor] > arr[j]) menor = j;
		}
		swap(arr, menor, j);
	}
}

void selOpt(int* arr, int n){
	int left = 0,
		right = n;
	while(left < right){
		int lowest = left,
			max = left;

		for(int i=left; i<right; i++){
			if(arr[i] < arr[lowest])
				lowest = i;
			if(arr[i] > arr[max])
				max = i;
		}

		swap(arr, lowest, left);
		if(max == left)
			max = lowest;
		swap(arr, max, right-1);

		left++;
		right--;
	}
}

#endif
