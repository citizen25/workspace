#include <stdio.h>

void add(int *, int, int);

int main(void){
    int m = 0, n = 0, sum = 0;

    printf("Enter two integer numbers >> ");
    scanf("%d %d", &m, &n);
    add(&sum, m, n);
    printf("Sum of two numbers : %d", sum);

    return 0;
}

void add(int *psum, int a, int b){
    *psum = a + b;
}