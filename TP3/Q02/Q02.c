#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <time.h>

#define strSize 80

void flag(){
	printf("FLAG\n");
}

int commaIndex(char* s){
	bool found = false;
	int i=0;
	for(; i<strlen(s) && !found; i++){
		if(s[i]=='.') found = true;
	}
	return i-1;
}

double parseDouble(char* s){
	int i = 0;
	double result = 0.0;
	int leng = strlen(s);
	int comma = commaIndex(s),
		amtLeft = leng-comma+2,
		j = comma-1;
	for(i=0; i<comma; i++){
		result += (double)((s[i]-48)*pow(10, j));
		j--;
	}
	j = 1;
	for(i=i+1; i<leng; i++){
		result += (double)((s[i]-48)/pow(10, j));
		j++;
	}
	return result;
}

int parseYear(char* s){
    int result = 0;
	int exp = 3;
	int i = 0;
    while(i<4){
        result += (int)(s[i]-48) * (int)pow(10.0, (double)exp);
        i++;
		exp--;
    }
    return result;
}

int parseInt(char* s){
    int result = 0;
    int i = strlen(s)-1,
        exp = 0;
    while(i>=0){
        result += (int)(s[i]-48) * (int)pow(10.0, (double)exp);
        i--;
		exp++;
    }
    return result;
}

typedef struct {
	int day;
	int month;
	int year;
} Date;

Date strToDate(char* s){
	char* d = strtok(s, "/");
	char* m = strtok(NULL, "/");
	char* y = strtok(NULL, "/");
	Date result;
	result.day = parseInt(d);
	result.month = parseInt(m);
	result.year = parseYear(y);
	return result;
}

char* dateToStr(Date i){
	char* result = malloc(40*sizeof(char));
	sprintf(result, "%02d/%02d/%04d", i.day, i.month, i.year);
	return result;
}

typedef struct {
	int n;
	char type1[strSize];
	char type2[strSize];
} Types;

typedef struct {
	int n;
	char ab1[strSize];
	char ab2[strSize];
	char ab3[strSize];
	char ab4[strSize];
	char ab5[strSize];
	char ab6[strSize];
} Abilities;

typedef struct {
	int id;
	int generation;
	char name[strSize];
	char description[strSize];
	Types types;
	Abilities abilities;
	double weight;
	double height;
	int captureRate;
	bool legendary;
	Date captureDate;
} Pokemon;

Pokemon* makeMon(char* line){
	Pokemon* result = calloc(1, sizeof(Pokemon));
	
	int leng = strlen(line);
	char* format = malloc(leng * sizeof(char));
	bool control = true;
	int j = 0;
	for(int i=0; i<leng; i++){
		if(line[i] == '"') control = !control;
		else if(line[i] == ',' && control) format[j++] = ';';
		else if(line[i] != '[' && line[i] != ']') format[j++] = line[i];
	}
	//printf("%s\n", format);

	char** aux = calloc(12, sizeof(char*));
	int nSplits=0;
	for(aux[nSplits++]=strtok(format,";"); aux[nSplits]=strtok(NULL,";"); nSplits++);

	//for(int x=0; x<nSplits; printf("%s\n", aux[x++]));

	result->id = parseInt(aux[0]);
	result->generation = parseInt(aux[1]);
	strcpy(result->name, aux[2]);
	strcpy(result->description, aux[3]);

	result->types.n = nSplits==11 ? 1 : 2;
	strcpy(result->types.type1, aux[4]);
	if(result->types.n == 2) strcpy(result->types.type2, aux[5]);

	bool commaInAbilities = false;
	int posAbs = nSplits==10 ? 6 : nSplits==11 ? 5 : 6;
	for(int j=0; j<strlen(aux[posAbs]); j++)
		if(aux[posAbs][j]==',') commaInAbilities = true;
	if(!commaInAbilities){
		result->abilities.n = 1;
		strcpy(result->abilities.ab1, aux[posAbs]);
	}
	else{

		//remove spaces after commas
		char* formt = malloc(strSize * sizeof(char));
		strcpy(formt, aux[posAbs]);
		for(int x=0; x<strlen(formt); x++)
			if(formt[x]==' ' && formt[x-1]==',') formt[x] = ',';

		char* buffer;
		buffer = strtok(formt, ",");
		result->abilities.n = 1;
		strcpy(result->abilities.ab1, buffer);

		if(buffer = strtok(NULL, ",")){
			result->abilities.n = 2;
			strcpy(result->abilities.ab2, buffer);

			if(buffer = strtok(NULL, ",")){
				result->abilities.n = 3;
				strcpy(result->abilities.ab3, buffer);

				if(buffer = strtok(NULL, ",")){
					result->abilities.n = 4;
					strcpy(result->abilities.ab4, buffer);

					if(buffer = strtok(NULL, ",")){
						result->abilities.n = 5;
						strcpy(result->abilities.ab5, buffer);

						if(buffer = strtok(NULL, ",")){
							result->abilities.n = 6;
							strcpy(result->abilities.ab6, buffer);
						}
					}
				}
			}
		}
	}


	if(nSplits>10){
		result->weight = parseDouble(aux[7-(12-nSplits)]);
		result->height = parseDouble(aux[8-(12-nSplits)]);
	}
	else{
		result->weight = 0.0;
		result->height = 0.0;
	}

	result->captureRate = parseInt(aux[9-(12-nSplits)]);

	if(strcmp(aux[10-(12-nSplits)], "1") == 0) result->legendary = true;
	else result->legendary = false;

	//printf("%s\n", aux[11-(12-nSplits)]);
	result->captureDate = strToDate(aux[11-(12-nSplits)]);


	return result;
}

void printMon(Pokemon* poke){
	printf("[#%d -> %s: %s - [", poke->id, poke->name, poke->description);

	if(poke->types.n == 1) printf("'%s'] - [", poke->types.type1);
	else printf("'%s', '%s'] - [", poke->types.type1, poke->types.type2);

	if(poke->abilities.n == 1)
		printf("%s] - ", poke->abilities.ab1);
	else if(poke->abilities.n == 2)
		printf("%s, %s] - ", poke->abilities.ab1, poke->abilities.ab2);
	else if(poke->abilities.n == 3)
		printf("%s, %s, %s] - ", poke->abilities.ab1, poke->abilities.ab2, poke->abilities.ab3);
	else if(poke->abilities.n == 4)
		printf("%s, %s, %s, %s] - ", poke->abilities.ab1, poke->abilities.ab2, poke->abilities.ab3, poke->abilities.ab4);
	else if(poke->abilities.n == 5)
		printf("%s, %s, %s, %s, %s] - ", poke->abilities.ab1, poke->abilities.ab2, poke->abilities.ab3, poke->abilities.ab4, poke->abilities.ab5);
	else if(poke->abilities.n == 6)
		printf("%s, %s, %s, %s, %s, %s] - ", poke->abilities.ab1, poke->abilities.ab2, poke->abilities.ab3, poke->abilities.ab4, poke->abilities.ab5, poke->abilities.ab6);

		printf("%.1lfkg - %.1lfm - %d%c - ", poke->weight, poke->height, poke->captureRate, '%');

		if(poke->legendary) printf("true ");
		else printf("false ");

		printf("- %d gen] - ", poke->generation);

		char* capDate = dateToStr(poke->captureDate);
		printf("%s\n", capDate);
}

Pokemon** readFile(const char* path){
	Pokemon** result = calloc(801, sizeof(Pokemon*));
	FILE* csv = fopen(path, "rt");
	char* trash = malloc(1000 * sizeof(char));
	fgets(trash, 999, csv);
	free(trash);
	for(int i=0; i<801; i++){
		char* buffer = malloc(1000 * sizeof(char));
		fgets(buffer, 999, csv);
		result[i] = makeMon(buffer);
		free(buffer);
	}
	fclose(csv);
	return result;
}

//_____________________________________________________________________________________________________________________________________________________
//=====================================================================================================================================================
//_____________________________________________________________________________________________________________________________________________________


double diff(clock_t start, clock_t end){
	return ((double)(end - start)) / CLOCKS_PER_SEC;
}

void logTP(const char* fileName, double time, int comps, int moves){
	FILE* fil = fopen(fileName, "w");
	if(fil){
		fprintf(fil, "Matrícula: 855947\t");
		fprintf(fil, "Tempo de Execução: %lf\t", time);
		fprintf(fil, "Comparações: %d\t", comps);
		fprintf(fil, "Movimentações: %d", moves);
		fclose(fil);
	}
	else printf("Erro ao abrir o arquivo");
}

void logSearch(const char* fileName, double time, int comps){
	FILE* fil = fopen(fileName, "w");
	if(fil){
		fprintf(fil, "Matrícula: 855947\t");
		fprintf(fil, "Tempo de Execução: %lf\t", time);
		fprintf(fil, "Comparações: %d", comps);
		fclose(fil);
	}
	else printf("Erro ao abrir o arquivo");
}

//_____________________________________________________________________________________________________________________________________________________
//=====================================================================================================================================================
//_____________________________________________________________________________________________________________________________________________________


typedef struct {
	Pokemon** arr;
	int size;
	int n;
} Lista;

Lista* makeLista(int x){
	Lista* ret = calloc(1, sizeof(Lista));
	ret->n = 0;
	ret->size = x;
	ret->arr = calloc(x+1, sizeof(Pokemon*));
	return ret;
}

Pokemon* pop(Lista* l, int x){
	Pokemon* ret = NULL;
	if(l->n>0 && x>=0 && x<=l->n){
		ret = l->arr[x];
		int i = x;
		while(i<l->n){
			l->arr[i] = l->arr[i+1];
			i++;
		}
		l->n--;
	}
	return ret;
}

Pokemon* popStart(Lista* l){
	return pop(l, 0);
}

Pokemon* popEnd(Lista* l){
	return pop(l, l->n);
}

void push(Lista* l, Pokemon* p, int x){
	if(l->n < l->size){
		int i = l->n;
		while(i>=x){
			l->arr[i] = l->arr[i-1];
			i--;
		}
		l->arr[x] = p;
		l->n++;
	}
}

void pushEnd(Lista* l, Pokemon* p){
	if(l->n < l->size){
		l->arr[l->n] = p;
		l->n++;
	}
}

void pushStart(Lista* l, Pokemon* p){
	push(l, p, 0);
}




//_____________________________________________________________________________________________________________________________________________________
//=====================================================================================================================================================
//_____________________________________________________________________________________________________________________________________________________



int comps = 0;
int moves = 0;

bool equals(const char* s1, const char* s2){
	return 0==strcmp(s1, s2);
}

void swapInt(int* arr, int a, int b){
	int tmp = arr[a];
	arr[a] = arr[b];
	arr[b] = tmp;
}

void swapPoke(Pokemon** arr, int a, int b){
	Pokemon* tmp = arr[a];
	arr[a] = arr[b];
	arr[b] = tmp;
	moves += 3;
}

int charAt(const char* s, int x){
	if(x<strlen(s))
		return s[x];
	else return 0;
}

int maxAbi(Pokemon** pokes, int n){
	int max = 0;
	for(int i=0; i<n; i++){
		int len = strlen(pokes[i]->abilities.ab1);
		if(len>max) max = len;
	}
	return max;
}

void radix(Pokemon** pokes, int n, int maxAb){
	
	for(int pos = maxAb-1; pos>=0; pos--){
		int count[256] = {0};
		Pokemon** tmp = calloc(n, sizeof(Pokemon*));

		for(int i=0; i<n; i++){
			int cha = charAt(pokes[i]->abilities.ab1, pos);
			count[cha]++;
		}

		for(int i=1; i<256; i++)
			count[i] += count[i-1];

		for(int i=n-1; i>=0; i--){
			int cha = charAt(pokes[i]->abilities.ab1, pos);
			tmp[--count[cha]] = pokes[i];
			moves++;
		}

		for(int i=0; i<n; i++){
			pokes[i] = tmp[i];
			moves++;
		}

		free(tmp);
	}
}

bool shouldSwap(char* a, char* b){ 
	bool result = false;
	int i=-1;
	do{
		i++;
		if(a[i] > b[i]) result = true;
	} while(a[i] == b[i]);
	return result;
}

void insertion(Pokemon** arr, int n){
	for(int i=1; i<n; i++){
		Pokemon* tmp = arr[i];
		int j = i-1;
		while(j>=0 && 0==strcmp(arr[j]->abilities.ab1, tmp->abilities.ab1) && shouldSwap(arr[j]->name, tmp->name)){
			comps++;
			moves++;
			arr[j+1] = arr[j];
			j--;
		}
		arr[j+1] = tmp;
		moves++;
	}
}



void sort(Pokemon** pokes, int n){
	radix(pokes, n, maxAbi(pokes, n));
	insertion(pokes, n);
}

int main(){
	Pokemon** pokes = readFile("/tmp/pokemon.csv");

	int i=0;
	int* usingIds = malloc(100*sizeof(int));
	
	char* input = malloc(20*sizeof(char));
	scanf(" %s", input);
	while(!equals(input, "FIM")){
		usingIds[i] = parseInt(input);
		i++;
		free(input);
		input = malloc(20*sizeof(char));
		scanf(" %s", input);
	}
	free(input);

	Pokemon** using = calloc(i, sizeof(Pokemon*));
	for(int j=0; j<i; j++){
		using[j] = pokes[usingIds[j]-1];
	}
	free(usingIds);

	clock_t start = clock();
	sort(using, i);
	for(int j=0; j<i; printMon(using[j++]));
	clock_t end = clock();

	free(using);
	free(pokes);
	logTP("855947_radixsort.txt", diff(start, end), comps, moves);

	return 0;
}
