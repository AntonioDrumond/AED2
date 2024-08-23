#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include<string.h>

void flag(){
	printf("\nFLAG\n");
	getchar();
}

char* comb(char* str1, char* str2){
	int size1 = strlen(str1),
		size2 = strlen(str2),
		size = size1 + size2 + 1;	
	char* result = calloc(size, sizeof(char));
	int i1 = 0,
		i2 = 0,
		ir = 0;
	while(i1<size1 || i2<size2){
		if(i1<size1){
			result[ir] = str1[i1];
			i1++; ir++;
		}
		if(i2<size2){
			result[ir] = str2[i2];
			i2++; ir++;
		}
	}
	return result;
}

int main(){
	bool work = true;
	char* str1 = malloc(53 * sizeof(char));
	char* str2 = malloc(53 * sizeof(char));
	while(scanf(" %51[^ ]", str1) != EOF){
		scanf(" %s", str2);
		char* result = comb(str1, str2);
		printf("%s\n", result);
		free(result);
	}
	free(str1);
	free(str2);
	return 0;
}
