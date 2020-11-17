#include <stdio.h>

int * add(int *, int, int);
int * subtract(int *, int, int);
int * multiply(int, int);

int main(void){
    int m = 0, n = 0, sum = 0, diff = 0;
    
    printf("Enter two numbers of integer >> ");
    scanf("%d %d", &m, &n);

    printf("Summation: %d\n", *add(&sum, m, n));
    printf("Subtraction: %d\n", *subtract(&diff, m, n));
    printf("Multiplication: %d\n", *multiply(m, n));

    return 0;
}

int * add(int *psum, int a, int b){
    *psum = a + b;
    return psum;

}
int * subtract(int *pdiff, int a, int b){
    *pdiff = a - b;
    return pdiff;
}
int *multiply(int a, int b){
    int mult = a * b;
    return &mult;       // ERROR: 지역변수 또는 임시 변수의 주소를 반환하고 있습니다.
}