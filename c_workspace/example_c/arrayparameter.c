#include <stdio.h>

#define ARYSIZE 5
double sum(double ary[], int n);    // 배열 원소값을 모두 더하는 함수 원형

int main(void){
    int i = 0, total = 0;

    // 배열 초기화
    double data[] = {2.3, 3.4, 4.5, 6.7, 9.2};
    for (i=0; i<ARYSIZE; i++)
        printf("%5.1f", data[i]);
    puts("");
    // 배열 원소값을 모두 더하는 함수 호출
    printf("sum : %5.1f\n", sum(data, ARYSIZE));

    return 0;
}

// 배열 원소값을 모두 더하는 함수 정의
double sum(double ary[], int n){
    int i = 0;
    double total = 0.0;
    for (i=0; i<n; i++)
        total += ary[i];

    return total;
}