#include <stdio.h>

// 이차원 배열값을 모두 더하는 함수원형
double sum(double data[][3], int, int);
// 이차원 배열값을 모두 출력하는 함수원형
void printarray(double data[][3], int, int);

int main(void){
    int i = 0;
    // 4x3 행렬
    double x[][3] = { {1, 2, 3}, {7, 8, 9}, {4, 5, 6}, {10, 11, 12} };

    int rowsize = sizeof(x) / sizeof(x[0]);
    int colsize = sizeof(x[0]) / sizeof(x[0][0]);
    printf("----data values of 2D array----\n");
    printarray(x, rowsize, colsize);
    printf("sum of all elements : %.3lf", sum(x, rowsize, colsize));

    return 0;
}

// 배열값을 모두 더하는 함수
double sum(double data[][3], int rowsize, int colsize){
    int i, j;
    double total = 0;

    for (i = 0; i < rowsize; i++)
        for(j = 0; j < colsize; j++)
            total += data[i][j];

    return total;
}

// 배열값을 모두 출력하는 함수
void printarray(double data[][3], int rowsize, int colsize){
    int i, j;

    for (i = 0; i < rowsize; i++){
        printf("elements of row %d : ", i+1);
        for (j = 0; j < colsize;  j++)
            printf("x[%d][%d] = %5.2lf ", i, j, data[i][j]);
        printf("\n");
    }
    printf("\n");
}
