#include <stdio.h>

int factorial(int);

int main(void){
    int i;

    for ( i = 1; i <= 10; i++ )
        printf( "%2d! = %d\n", i, factorial( i ) );

    return 0;
}

// n! 구하는 함수
int factorial(int number){
    if (number <= 1)
        return 1;
    else
        return (number * factorial(number - 1));
}