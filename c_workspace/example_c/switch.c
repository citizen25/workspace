#include <stdio.h>

int main(void){
    double x, y, result;
    int op;

    printf("Enter two float number : ");
    scanf("%lf %lf", &x, &y);
    printf("Operation type is... 1(+), 2(-), 3(*), 4(/) : ");
    scanf("%d", &op);

    switch (op){
        case 1:
            printf("%.2f + %.2f = %.2f\n", x, y, x + y);
            break;
        case 2:
            printf("%.2f - %.2f = %.2f\n", x, y, x - y);
            break;
        case 3:
            printf("%.2f * %.2f = %.2f\n", x, y, x * y);
            break;
        case 4:
            printf("%.2f / %.2f = %.2f\n", x, y, x / y);
            break;
        default:
        printf("Entered wrong number");
    }

    return 0;
}