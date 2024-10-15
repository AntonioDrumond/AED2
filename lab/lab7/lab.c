#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

int main(){
    int input;
    while (scanf(" %d", &input) != EOF){
        int arrStart[input];

        for (int i = 0; i < input; i++){
            scanf(" %d", &arrStart[i]);
        }

        int arrEnd[input];

        for (int i = 0; i < input; i++){
            scanf(" %d", &arrEnd[i]);
        }

        int movInit = 0;

        for (int i = 0; i < input - 1; i++){
            for (int j = 0; j < input - i - 1; j++){
                if (arrStart[j] < arrStart[j + 1]){
                    int temp = arrStart[j];
                    arrStart[j] = arrStart[j + 1];
                    arrStart[j + 1] = temp;
                    movInit++;
                }
            }
        }

        int movFim = 0;

        for (int i = 0; i < input - 1; i++){
            for (int j = 0; j < input - i - 1; j++){
                if (arrEnd[j] < arrEnd[j + 1]){
                    int temp = arrEnd[j];
                    arrEnd[j] = arrEnd[j + 1];
                    arrEnd[j + 1] = temp;
                    movFim++;
                }
            }
        }

        int ans = movFim - movInit;
        if(ans < 0){
            ans = ans * (-1);
        }

        printf("%d\n", ans);
    }
}
