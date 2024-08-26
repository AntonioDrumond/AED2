#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

void flag(){
	printf("\nFLAG\n"); getchar();
}

bool stop(char* s){
	return (strlen(s)==3 && s[0]=='F' && s[1]=='I' && s[2]=='M');
}

char* alter(char* s, char target, char new, int i){
	if(s[i]==target) s[i] = new;
	if(i<strlen(s)-1) return alter(s, target, new, i+1);
	else return s;
}

char* alterar(char* s){
	char target = abs(rand())%26;
	char new = abs(rand())%26;
	return alter(s, target, new, 0);
}

int main(){
	srand(time(NULL));
	//srand(4);
	char* s;
	bool work = true;
	while(work){
		s = malloc(501*sizeof(char));
		scanf(" %500[^\n]", s);
		if(stop(s)) work = false;
		else {
			char* result = alterar(s);
			printf("%s\n", result);
		}
		free(s);
	}
	return 0;
}
