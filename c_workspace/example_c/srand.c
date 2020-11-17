#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX 100

int main(void){
    int i, number;

    srand((long) time(NULL));
    printf("random number between 0 ~ %5d : rand()\n", MAX);
    for (i = 0; i < 5; i++ ){
        number = rand()%MAX + 1;
        printf("%5d ", number);
    }
    puts("");

    return 0;
}
