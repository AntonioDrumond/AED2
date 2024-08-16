#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

bool stop(char* s){
	return (strlen(s) && s[0]=='F' && s[1]=='I' && s[2]=='M');
}

bool palin(char* s, int i){
	if(i>=((strlen(s)/2)-1)) return (s[i]==s[strlen(s)-i-1]);
	else return (s[i]==s[strlen(s)-i-1] && palin(s, i+1));
}

bool pali(char* s){
	return palin(s, 0);
}

int main(){
	char* input = NULL;
	bool stopper = false;
	while(!stopper){
		input = malloc(1001 * sizeof(char));
		scanf(" %1000[^\n]", input);
		if(stop(input)) stopper = true;
		else if(pali(input)) printf("SIM\n");
		else printf("NAO\n");
		free(input);
	}
	return 0;
}
