#include <stdio.h>

#define KPOP 50000000   // 정수 매크로 상수
#define PI 3.14     // 실수 매크로 상수
#define PRT printf("shut down\n")

int main(void){
    double radius = 2.83;
    printf("Korea Population : %d\n", KPOP);
    printf("Area of circle : %f\n", radius * radius * PI);
    PRT;

    return 0;
}