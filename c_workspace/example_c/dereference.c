#include <stdio.h>

int main(void){
    int data = 100;
    char ch = 'A';
    int *ptrint = &data;
    char *ptrchar = &ch;
    printf("Print indirect reference : %d %c \n", *ptrint, *ptrchar);

    *ptrint = 200;
    *ptrchar = 'B';
    printf("Print direct reference : %d %c \n", data, ch);

    return 0;
}