#include <stdio.h>
#include <stdlib.h>

void main(){
    int *pa;
    int sum = 0, i, j;
    float avg;

    printf("how many numbers do you want? >> ");
    scanf("%d", &j);

    pa = (int *)malloc(sizeof(int)*j);
    
    for(i=0; i<j; i++){
        printf("enter %dth number: ", i+1);
        scanf("%d", &pa[i]);
        sum = sum + pa[i];
    }

    free(pa);

    avg = sum / (float)j;
    printf("sum = %d\n", sum);
    printf("avg = %5.3f\n", avg);
}