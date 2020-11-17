#include <stdio.h>
#include <stdlib.h>

void main(){

    int x=1;
    int row, col;
    int **dbptr;


    /* 동적 메모리 할당 */
    printf("row: ");
    scanf("%d", &row);
    printf("col: ", &col);
    scanf("%d", &col);

    dbptr = (int **)malloc(sizeof(int *)*row);
        // 다른 배열 각각의 시작 주소(pointer)를 저장하기 때문에 double pointer를 선언한다.
    for(int i=0; i<row; i++){
        dbptr[i] = (int *)malloc(sizeof(int)*col);
            // dbptr의 i번째 row에 col수 만큼 int형 자료가 들어갈 수 있도록 메모리 할당
    }

    /* 할당된 메모리에 임의의 정수를 넣고 출력하기 */
    for(int i=0; i<row; i++){
        for(int j=0; j<col; j++){
            dbptr[i][j] = x++;
        }
    }
    for(int i=0; i<row; i++){
        for(int j=0; j<col; j++){
            printf("%d ", dbptr[i][j]);
        }
        printf("\n");
    }

    /* 메모리 해제 */
    for(int i=0; i<row; i++){
        free(dbptr[i]);
    }
    free(dbptr);

}
