#include <stdio.h>
#define ROW 2
#define COL 3

int main(void){
    int td[][COL] = {{8, 5, 4}, {2, 7, 6}};
    int i = 0, j = 0, cnt = 0;

    printf("%d, %d, %d\n", sizeof(td), sizeof(td[0]), sizeof(td[1]));
    printf("%u, %u, %u\n", td, td[0], td[1]);
    printf("%u, %u\n", &td[0][0], &td[1][0]);

    **td = 10;      // td[0][0] = 10;
    *td[1] = 20;    // td[1][0] = 20;

    for (i=0; i<ROW; i++){
        for(j=0; j<COL; j++, cnt++){
            printf("%d %d, ", *(*td+cnt), *(td[i]+j)); 
        }
        printf("\n");
    }
    
    return 0;
}