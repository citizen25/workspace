#include <stdio.h>

int main(void){
    int input;

    printf("Enter an integer : ");
    scanf("%d", &input);
    printf("Entered value : %d\n", input);
    printf("Address : %u(decimal), %p(hexadecimal)\n", &input, &input);
    printf("Address : %d(decimal), %#p(hexadecimal)\n", &input, &input);
    printf("Address value size : %d\n", sizeof (&input));

    return 0;
}