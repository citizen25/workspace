#include <stdio.h>

double readarray(double [], int);   // 배열 원소값을 모두 표준입력으로 받는 함수 원형
double sum(double [], int);    // 배열 원소값을 모두 더하는 함수원형
void printarray(double [], int);    // 배열 원소값을 모두 출력하는 함수원형

int main(void){
    int i = 0;
    double data[5];
    
    int arraysize = sizeof(data) / sizeof(data[0]);
    printf("Input 5 number of float type. \n");
    readarray(data, arraysize);
    printf("The data values entered are as follows. \n");
    printarray(data, arraysize);
    printf("Sum : %.3lf \n", sum(data, arraysize));

    return 0;
}

// 배열 원소값을 모두 표준입력으로 받는 함수
double readarray(double data[], int n){
    int i;
    for (i = 0; i < n; i++){
        printf("data[%d] = ", i);
        scanf("%lf", &data[i]);
    }
    return;
}

// 배열 원소값을 모두 더하는 함수
double sum(double data[], int n){
    int i;
    double total = 0;
    for (i = 0; i < n; i++)
        total += data[i];
    return total;
}

// 배열 원소값을모두 출력하는 함수
void printarray(double data[], int n){
    int i;
    for(i = 0; i < n; i++)
        printf("data[%d] = %.2lf   ", i, data[i]);
    printf("\n");
    return;
}