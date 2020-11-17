#include <stdio.h>

void oufunction(){
    extern int global, sglobal;

    printf("%d\n", ++global);
    // 와부 파일에 선언된 정적 젼역변수이므로 실행시 오류
    // printf("%d\n", ++sglobal);
}