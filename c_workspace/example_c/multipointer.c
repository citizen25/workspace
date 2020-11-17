#include <stdio.h>

int main(void){
    int i = 20;
    int *pi = &i;       // 포인터 선언
    int **dpi = &pi;    // 이중 포인터 선언

    printf("%p  %p  %p\n", &i, pi, *dpi);   // 모두 변수 i의 주소값을 참조하는 방식이다.

    *pi = i + 2;        // i = i + 2
    printf("%d  %d  %d\n", i, *pi, **dpi);

    **dpi = *pi + 2;    // i = i + 2;
    printf("%d  %d  %d\n", i, *pi, **dpi);

    return 0;
}