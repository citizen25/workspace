#include <stdio.h>

// 연산자 우선순위
// 괄호 > 단항 > 산술 > 이동 > 관계 > 비트 > 논리 > 조건 > 대입 > 콤마

int main(void){
    int a = 3, b = 5;
    double x = 3.5, y = 2.7;

    printf("%d ", a + b > y && x < y);  // 산술 > 관계 > 논리
    printf("%d ", a++ - --b * 2);   // 단항 > 곱셈 > 뺄셈
    printf("%f ", a > b ? x + 1 : y * 2);   // 산술 > 관계 > 조건
    printf("%f ", x += 3 && y+2);   // 산술 > 논리 > 대입
    printf("%f\n", (x = x + 1, y = y + 1)); // 괄호 > 산술 > 대입 > 콤마

    return 0;
}