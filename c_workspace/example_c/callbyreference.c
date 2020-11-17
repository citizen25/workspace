#include <stdio.h>

void increase(int *origin, int increment);

int main(void){
    int amount = 10;
    // &amount: amount의 주소로 호출
    increase(&amount, 20);
    printf("%d\n", amount);

    return 0;
}

void increase(int *origin, int increment){
    // *origin은 origin이 가리키는 변수 자체
    *origin += increment;   // 그러므로 origin이 가리키는 변수값이 20 증가
}