#include <stdio.h>

int main(void){
    char name[20], univ[30];
    // char *name, *univ; 실행 오류 발생

    printf("%s", "Enter name >> ");
    scanf("%s", name);
    printf("%s", "Enter university >> ");
    scanf("%s", univ);
    printf("Output: %10s %10s\n", name, univ);

    return 0;
}