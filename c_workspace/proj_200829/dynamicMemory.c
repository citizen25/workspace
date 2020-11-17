#include <stdio.h>
#include <stdlib.h>

int main(void){

    int *pi;    // Pointer Integer
    pi = (int *)malloc(sizeof(int));    // malloc = 메모리를 할당해라.
    if(pi == NULL){
        printf("Fail\n");
        exit(1);       // 실행 중인 program 자체를 종료하는 것을 의미
    }
    *pi = 100;
    printf("%d\n", *pi);
    /* 동적 메모리 사용한 이후에는 무조건 해당 메모리를 반환합니다. */
    free(pi);
    return 0;
}
