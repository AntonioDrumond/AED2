#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

bool stop(char* s){
	if(strlen(s)==3 && s[0]=='F' && s[1]=='I' && s[2]=='M') return true;
	else return false;
}

int qnt(char* s, int i){
	if(i>=strlen(s)) return 0;
	else return qnt(s, i+1) + (s[i]>='A' && s[i]<='Z');
}

int qnts(char* s){
	return qnt(s, 0);
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
