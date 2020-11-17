#include <stdio.h>

int main(void){
    int i = 0;
    char *java = "java";
    printf("%s ", java);

    // 문자 포인터가 가리키는 문자 이후를 하나 하나 출력
    while (java[i] != '\0')
        printf("%c", java[i++]);
    printf(" ");

    i = 0;
    // java[i]와 *(java + i)는 동일한 표현방식이다
    while (*(java + i) != '\0')
        printf("%c", *(java + i++));
    printf("\n");

    return 0;
}