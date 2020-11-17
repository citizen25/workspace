#include <stdio.h>

int main(void){
    char sq = '\'';

    printf("BCPL\tB\tC\tJava\n");
    printf("%c\7\n", '\a');
    printf("%cJava Language'\n", sq);
    printf("\"funny C language!\n");

    return 0;
}