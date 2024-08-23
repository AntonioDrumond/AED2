#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include<string.h>

int main(){
	FILE* fil = fopen("file.txt", "wb");
	int reps = 0;
	scanf(" %d", &reps);
	for(int i=0; i<reps; i++){
		double x = 0.0;
		scanf(" %lf", &x);
		fwrite(&x, sizeof(double), 1, fil);
	}
	fclose(fil);
	fil = NULL;
	fil = fopen("file.txt", "rb");
	for(int i=reps-1; i>=0; i--){
		fseek(fil, i*8, SEEK_SET);
		double real = 0.0;
		fread(&real, sizeof(double), 1, fil);
		int inte = (int)real;
		if(real==inte) printf("%d\n", inte);
		else printf("%g\n", real);
	}
	fclose(fil);
	return 0;
}
