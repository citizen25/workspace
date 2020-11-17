#include <stdio.h>

void main(){
    int a[100], sum=0, i, j;
    float avg;
    printf("number of integer numbers: ");
    scanf("%d", &j);
    for(i=0; i<j; i++){
        printf("enter %dth data: ", i+1);
        scanf("%d", &a[i]);
        sum=sum+a[i];
    }
    avg=sum/(float)j;
    printf("sum: %d\n", sum);
    printf("average; %5.3f\n", avg);
}