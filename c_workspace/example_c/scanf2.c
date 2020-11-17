#include <stdio.h>

int main(void){
    int height, weight;

    printf("your height and weight : ");
    scanf("%d%d", &height, &weight);
    printf("height : %d\tweight : %d", height, weight);

    return 0;
}