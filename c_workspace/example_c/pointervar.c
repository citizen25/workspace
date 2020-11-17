#include <stdio.h>

int main(void){
    int data = 100;
    int *ptrint;

    ptrint  = &data;
    printf("decimal address value : %u\n", ptrint);
    printf("size of pointer valiable : %d\n", sizeof (ptrint));     // 모든 포인터 변수의 크기는 4바이트

    return 0;
}