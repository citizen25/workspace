#include <stdio.h>

int main(void){
    int a[] = {8, 2, 8, 1, 3};
    int *p = a;

    int ary[][4] = {5, 7, 6, 2, 7, 8, 1, 3};
    int (*ptr)[4] = ary;    // 열이 4인 배열을 가리키는 포인터
    // int *ptr[4] = ary    // 포인터 배열

    printf("%2d, %2d\n", *(p+1), *(p+4));
    printf("%2d, %2d\n", p[0], p[4]);
    printf("sizeof(a) = %d, sizeof(p) = %d\n", sizeof(a), sizeof(p));

    printf("%2d, %2d\n", **ary, **ptr++);
    printf("%2d, %2d\n", **(ary+1), **(ptr++));
    ptr = ary;
    printf("%2d, %2d\n", *(ary[1] + 1), *(ptr[1] + 1));
    printf("%2d, %2d\n", *(*(ary+1) + 3), *(*(ptr+1) + 3));
    printf("sizeof(ary) = %d, sizeof(ptr) = %d\n", sizeof(ary), sizeof(ptr));

    return 0;
}
