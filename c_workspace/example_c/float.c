#include <stdio.h>

int main(void){
    float x = 3.14;
    double y = -3.14e3;
    long double z = 3.14E03L;

    printf("value : %f %f %f\n", x, y, z);

    return 0;
}