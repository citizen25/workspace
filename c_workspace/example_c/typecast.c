#include <stdio.h>

int main(void){
    int a = 3.4;    // 자동으로 내림변환되어 변수 a에는 3이 저장
    double d = 3;   // 자동으로 올림변환되어 변수 d에는 3.0이 저장

    printf("%5d %10f ", a, d);
    printf("%10f\n", 3 + 4.5);

    printf("%5d ", 10 / 4);
    printf("%10f ", (double) 10 / 4);
    printf("%10f ", 10 / (double) 4);
    printf("%10f\n", (double) (10 / 4));
    
    printf("%5d ", (int) (3.4 + 7.8));
    printf("%10d ", (int) 3.4 + (int) 7.8);
    printf("%10f ", (int) 3.4 + 7.8);
    printf("%10f\n", 3.4 + (int) 7.8);
    
    return 0;
}