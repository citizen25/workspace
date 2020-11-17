#include <stdio.h>

int main(void){

    int arr[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    SelectionSort(arr, 10);

    return 0;
}

void SelectionSort(int arr[], int n){
    for(int i=0; i<n-1; i++){
        int tmp = i;
        for(int j=i+1; j<n; j++){
            if (arr[tmp] > arr[i]){
                tmp = j;
            }
            Swap(&arr[tmp], &arr[i]);
        }
    }
}

void Swap(int *a, int *b){
    int tmp = a;
    a = b;
    b = tmp;
}