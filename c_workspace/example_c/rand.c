#include <stdio.h>
#include <stdlib.h> // rand() 위한 헤더 파일

int main(void){
    int i;

    printf("random number between 0 ~ %5d : rand()\n", RAND_MAX);
    for (i=0; i<5; i++)
        printf("%5d ", rand());
    puts("");

    return 0;
}

// 이렇게 하면 시드값이 고정되어 있기 때문에 같은 랜덤값만 나오게 된다. --> srnad() 참고.