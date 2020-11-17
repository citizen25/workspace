#include <stdio.h>

#define ROWSIZE 2
#define COLSIZE 3

int main(void){
    int i = 0, j = 0;

    int td[][3] = { {1}, {1, 2, 3} };   // 행(세로)은 맘대로 정해도 되고 열(가로)는 크기가 정해져야 함.

    printf("print with 'for'\n");
    for (i = 0; i < ROWSIZE; i++){
        for (j = 0; j < COLSIZE; j++){
            printf("%d ", td[i][j]);
        }
        printf("\n");
    }

    return 0;
}