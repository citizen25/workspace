#include <stdio.h>

int main(void){
    register int count;

    for (count = 1; count <= 100; count++);
        // scanf("%d", &count);     // 문법 오류 발생
        // printf("%ㅕ", &count);     // 문법 오류 발생
        printf("%d\n", count);

    return 0;
}