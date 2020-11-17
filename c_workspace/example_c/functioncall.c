#include <stdio.h>

int add2(int a, int b);     // int add2(int, int)도 가능
int findMax2(int, int);     // int findMax2(int a, int b)도 가능
void printMin(int, int);    // int printMin(int a, int b)도 가능

int main(void){
    int a = 3, b = 5;

    int max = findMax2(a, b);
    int sum = add2(a, b);
    printf("Max : %d\n", max);
    printf("Sum : %d\n", sum);

    printMin(2, 5);

    return 0;
}

int add2(int a, int b){
    int sum = a + b;

    return (sum);
}

int findMax2(int a, int b){
    int max = a > b ? a : b;

    return max;
}

int findMin2(int x, int y){
    int min = x < y ? x : y;

    return (min);
}

void printMin(int a, int b){
    int min = a < b ? a : b;
    printf("Min : %d\n", min);

    return;     // 생략가능
}