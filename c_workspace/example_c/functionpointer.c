#include <stdio.h>

void add(double*, double, double);
void subtract(double*, double, double);

int main(void){
    double m, n, result = 0;
    void (*pf)(double*, double, double) = NULL;

    printf("Enter 2 float number. >> ");
    scanf("%lf %lf", &m, &n);

    // 사칙연산을 수행
    pf = add;
    pf(&result, m, n);      // add(&result, m, n);
    printf("pf = %p, function add() address = %p\n", pf, add);
    printf("perform plus: %lf + %lf == %lf\n\n", m, n, result);

    pf = subtract;
    pf(&result, m, n);      // subtract(&result, m, n);
    printf("pf = %p, function subtract() address = %p\n", pf, subtract);
    printf("perform plus: %lf - %lf == %lf\n\n", m, n, result);

    return 0;
}

void add(double *z, double x, double y){
    *z = x + y;
}

void subtract(double *z, double x, double y){
    *z = x - y;
}