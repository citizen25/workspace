#include <stdio.h>

void add(double*, double, double);
void subtract(double*, double, double);
void multiply(double*, double, double);
void devide(double*, double, double);

int main(void){
    double m, n, result;
    int i;
    char op[4] = {'+', '-', '*', '/'};
    void(*fpary[4])(double*, double, double) = {add, subtract, multiply, devide};

    printf("Enter 2 float numbers. >> ");
    scanf("%lf %lf", &m, &n);
    // 사칙연산을 배열의 첨자를 이용하여 수행
    for (i=0; i<4; i++){
        fpary[i](&result, m, n);
        printf("%.2lf %c %.2lf == %.2lf\n", m, op[i], n, result);
    }

    return 0;
}

void add(double *z, double x, double y){
    *z = x + y;
}

void subtract(double *z, double x, double y){
    *z = x - y;
}

void multiply(double *z, double x, double y){
    *z = x * y;
}

void devide(double *z, double x, double y){
    *z = x / y;
}