#include <stdio.h>
#include <stdlib.h>

void Swap(int arr[], int a, int b){
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
}
int Partition(int arr[], int left, int right){
    int pivot = arr[left];
    int low = left + 1;
    int high = right;

    while (low <= high){
        while (low <= right && pivot >= arr[low]){
            low++;
        }
        while (high >= (left+1) && pivot <= arr[high]){
            high--;
        }
        if (low <= high){
            Swap(arr, low, high);
        }
    }
    Swap(arr, left, high);
    return high;
}
void QuickSort(int arr[], int left, int right){
    if (left <= right){
        int pivot = Partition(arr, left, right);
        QuickSort(arr, left, pivot-1);
        QuickSort(arr, pivot+1, right);
    }
}

int main(void){
    int n, i;
    int arr[100];

    printf("how many numbers will you align >> ");
    scanf("%d", &n);

    for (i=0; i<n; i++){
        arr[i] = rand()%1000;
    }

    printf("Before : ");
    for(i=0; i<n; i++){
        printf("%d ", arr[i]);
    }
    printf("\n");

    QuickSort(arr, 0, n-1);

    printf("After : ");
    for(i=0; i<n; i++){
        printf("%d ", arr[i]);
    }
    printf("\n");

    return 0;
}