#include <stdio.h>

void infunction(void);
void outfunction(void);
int global = 10;        // 전역변수
static int sglobal = 20;       // 정적 전역변수

int main(void){
    int x = 100;        // main() 함수의 지역변수
    printf("%d, %d, %d\n", global, sglobal, x);
    infunction(); outfunction();
    infunction(); outfunction();
    infunction(); outfunction();
    printf("%d, %d, %d\n", global, sglobal, x);

    return 0;
}

void infunction(void){
    auto int fa = 1;
    static int fs;
    printf("%d, %d, %d, %d\n", ++global, ++sglobal, fa, ++fs);
}