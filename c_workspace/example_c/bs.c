#include <stdio.h>
#include <windows.h>

int main(void){
    while (1){
        int number = 0;
        printf("Junmin is bottle god.\n1. True\n2. False\nEnter the number >> ");
        scanf("%d", &number);
        if (number == 1){
            printf("Congratuation! That's right.");
            Sleep(5000);
            break;
        }
        else if (number == 2){
            printf("Ddang~! lee junmin is bottle god. It is THE REASON OF THE WORLD.");
            Sleep(5000);
            break;
        }
        else {
            printf("\nWrong number is entered.\n\n");
        }
    }

    return 0;
}
