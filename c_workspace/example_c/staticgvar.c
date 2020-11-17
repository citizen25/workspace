#include <stdio.h>

static int svar;
void increment();

int main(void){
    int count = 1;
    for ( ; count <= 5; count++ )
        increment();
    printf("Function increment() is called %d times.\n", svar);
}

void increment(){
    svar++;
}