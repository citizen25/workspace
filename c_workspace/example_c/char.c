#include <stdio.h>

int main(void){
    char c1 = 'a';
    char c2 = 65;
    char c3 = '\132';   // 8잔수

    // %c는 문자 출력
    printf("value(text) : %c %c %c\n", c1, c2, c3);
    // %d는 문자의 코드값 십진수 출력
    printf("value(integer) : %d %d %d\n", c1, c2, c3);

    return 0;
}