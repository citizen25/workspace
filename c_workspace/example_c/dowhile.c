#include <stdio.h>

# define MAX 10

int main(void){
    int input;

    do{
        printf("Enter positive integer or 0(exit) : ");
        scanf("%d", &input);
        if (input > 0){
            int sum = 0, i;
            for (i=1; i<= input; i++)
                sum += i;
            printf("Sum from 1 to %d : %d\n", input, sum);
        }
    } while (input > 0);

    return 0;
}