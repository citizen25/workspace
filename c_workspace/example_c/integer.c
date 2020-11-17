#include <stdio.h>

int main(void){
    short sVar = 32000;
    int iVar = 2140000000;
    long gVar = -2140000000;

    unsigned short int usVar = 6500;
    unsigned int uiVar = 2140000000;
    unsigned long int ugVar = 4280000000;

    printf("value : %d %d %d\n", sVar, iVar, gVar);
    printf("value : %u %u %u\n", usVar, uiVar, ugVar);
    
    return 0;
}