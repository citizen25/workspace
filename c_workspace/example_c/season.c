#include <stdio.h>

int main(void){
    int month;

    printf("Enter the month : ");
    scanf("%d", &month);

    switch ( month ){
        case 4: case 5:
            printf("Spring");
            break;
        case 6: case 7: case 8:
            printf("Summer");
            break;
        case 9: case 10: case 11:
            printf("Autumn");
            break;
        case 12: case 1: case 2: case 3:
            printf("Winter");
            break;
        default:
        printf("Entered wrong number");
    }

    return 0;
} 