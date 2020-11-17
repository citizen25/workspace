#include <stdio.h>
void multiply(double *, const double *, const double *);
void devideandincrement(double *, double *, double *);

int main(void){
    double m = 0, n = 0, mult = 0, dev = 0;

    printf("Enter two float numbers >> ");
    scanf("%lf %lf", &m, &n);
    multiply(&mult, &m, &n);
    devideandincrement(&dev, &m, &n);
    printf("multiply: %.2f, devide: %.2f\n", mult, dev);
    printf("numbers after calculating: %.2f, %.2f\n", m, n);

    return 0;
}

// 매개변수 포인터 a, b가 가리키는 변수의 내용은 수정하지 못함
void multiply(double *result, const double *a, const double *b){
    *result = *a * *b;
    // 오류발생 : a, b는 수정할 수 없다.
    // *a = *a + 1;
    // *b = *b + 1;
}

void devideandincrement(double *result, double *a, double *b){
    *result = *a / *b;
    ++*a;   // const가 아닌 포인터 인자 *result, *a, *b는 모두 수정할 수 있다.
    (*b)++;
}