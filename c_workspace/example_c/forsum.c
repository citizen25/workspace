#include <stdio.h>

int main(void){
    int i, sum;

    for (i=1, sum=0; i<=10; i++){
        sum += i;
    }
    printf("Sum from 1 to 10 : %3d\n", sum);
    
    for (i=1, sum=0; i<=10; ++i){
        sum += i;
    }
    printf("Sum from 1 to 10 : %3d\n", sum);
    
    for (i=1, sum=0; i<=10; i=i+1){
        sum += i;
    }
    printf("Sum from 1 to 10 : %3d\n", sum);
    
    for (i=1, sum=0; i<=10; ){
        sum += i++;
    }
    printf("Sum from 1 to 10 : %3d\n", sum);
    
    for (i=0, sum=0; i<=9; ){
        sum += ++i;
    }
    printf("Sum from 1 to 10 : %3d\n", sum);
    
    for (i=1, sum=0; i<=10; sum += i++);
    printf("Sum from 1 to 10 : %3d\n", sum);
    
    return 0;
}