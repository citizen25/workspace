#include <stdio.h>

int main(void){
    int ch = 'A';
    // 문자 하나 하나 저장 시 마지막에 '\0' 문자 저장
    char java[] = {'J', 'A', 'V', 'A', '\0'};
    char c[] = "C language";    // 크기를 생략하는 것이 간편
    char csharp[3];
    csharp[0] = 'C';
    csharp[1] = '#';
    csharp[2] = '\0';

    printf("%c %d\n", ch, ch);
    printf("%c%c\n", csharp[0], csharp[1]);
    printf("%s %s\n", java, c);     // 문자열 출력을 위해 배열 이름과 형식제어문자 %s를 이용한다.
    puts(csharp);
    printf(c); printf("\n");

    return 0;
}