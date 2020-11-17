#include <stdio.h>

int main(void){
    int n;

    printf("Enter Integer : ");
    scanf("%d", &n);

    printf("The number is ");

    if (n % 2)
        printf("odd number\n");
    else
        printf("even number\n");

    return 0;
}