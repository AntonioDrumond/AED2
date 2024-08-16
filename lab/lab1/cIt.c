#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

bool stop(char* s){
	if(strlen(s)==3 && s[0]=='F' && s[1]=='I' && s[2]=='M') return true;
	else return false;
}

bool maius(char c){
	return c>='A' && c<='Z';
}

int qnts(char* s){
	int result = 0;
	for(int i=0; i<strlen(s); i++){
		if(maius(s[i])) result++;
	}
	return result;
}

int main(){
	char* s;
	bool work = true;
	while(work){
		s = malloc(128 * sizeof(char));
		scanf(" %127[^\n]", s);
		if(stop(s)) work = false;
		else printf("%d\n", qnts(s));
		free(s);
	}
	return 0;
}
