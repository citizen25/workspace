#include <stdio.h>
#include <stdlib.h>

int main(){
    int *pa;
    int capacity;

    printf("Enter the capacity of Integer numbers : ");
    scanf("%d", &capacity);

    pa = (int *)malloc(sizeof(int)*capacity);

    for(int i=0; i<capacity; i++){
        printf("%dth number : ", i+1);
        scanf("%d", &pa[i]);
    }
    for(int i=0; i<capacity; i++){
        printf("%d ", pa[i]);
    }

    free(pa);
}