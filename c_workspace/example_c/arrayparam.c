#include <stdio.h>

// int sumary(int ary[], int SIZE);
int sumary(int *ary, int SIZE);

int main(void){
    int i, sum = 0;
    int point[] = {95, 88, 76, 54, 85, 33, 65, 78, 99, 82};
    int *address = point;
    int aryLength = sizeof(point) / sizeof(int);

    for (i=0; i<aryLength; i++)
        sum+= *(point+i);
        // sum += *(point++);   // error
        // sum += *(address++); // 가능

    printf("sum from main is %d\n", sum);
    address = point;
    printf("sum from sumary() is %d\n", sumary(point, aryLength));
    printf("sum from sumary() is %d\n", sumary(&point[0], aryLength));
    printf("sum from sumary() is %d\n", sumary(address, aryLength));
    
    return 0;
}

// int sumary(int ary[], int SIZE)
int sumary(int *ary, int SIZE){
    int sum = 0, i = 0;

    for (i=0; i<SIZE; i++){
        // sum += ary[i];       // 가능
        // sum += *(ary + i);   // 가능
        sum += *ary++;
        // sum += *ary++;
    }
    
    return sum;

}