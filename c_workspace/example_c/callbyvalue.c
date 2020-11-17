#include <stdio.h>

void increase(int origin, int increment);

int main(void){
    int amount = 10;
    // amount가 20 증가하지 않음
    increase(amount, 20);
    printf("%d\n", amount);
    
    return 0;
}

void increase(int origin, int increment){
    origin += increment;
}