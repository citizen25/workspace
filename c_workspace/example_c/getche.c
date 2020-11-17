#include <stdio.h>
#include <conio.h>

int main(void){
    char ch;

    printf("If you type a letter and keep pressing Enter >> \n");
    while ((ch = getchar()) != 'q')
        putchar(ch);
    printf("\n");

    printf("Print twice each time a character is pressed >> \n");
    while ((ch = getche()) != 'q')
        putchar(ch);
    printf("\n");

    printf("Press a character to print it once >> \n");
    while ((ch = getch()) != 'q');
        putch(ch);
    printf("\n");

    return 0;
}