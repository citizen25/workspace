#include <stdio.h>
void increament(void);  // 함수원형

void main(void){
    int count = 0;  // 자동 지역변수
    for ( ; count<3; count++ )
        increament();       // 3번 함수 호출
}

void increament(void){
    static int sindex = 1;  // 정적 지역변수
    auto int aindex = 1;    // 자동 지역변수
    printf("static local value: %2d, \t", sindex++);
    printf("auto local value: %2d\n", aindex++);
}