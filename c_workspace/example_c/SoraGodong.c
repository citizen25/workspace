#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(void){

    char advice[][10] = {"hello1", "hello2", "hello3", "hello4", "hello5", "hello6", "hello7", "hello8", "hello9", "hello10"};

    srand((unsigned int) time(NULL));

    printf("%s", advice[rand()%10]);

    return 0;
}