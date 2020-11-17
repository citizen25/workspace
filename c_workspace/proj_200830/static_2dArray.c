#include <stdio.h>
#include <stdlib.h>

void main(){
    int arr[3][5];
    int x=1;
    for (int j=0; j<3; j++){
        for (int i=0; i<5; i++){
            arr[j][i] = x++;
        }
    }
    for (int j=0; j<3; j++){
        for (int i=0; i<5; i++){
            printf("%d ", arr[j][i]);
        }
        printf("\n");        
    }
}