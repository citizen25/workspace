#include <stdio.h>

int main(void){
    unsigned char uc = 255 + 1;
    short s = 32767 + 1;
    float min = 1.175E-50;
    float max = 3.403E39;

    // 아래의 결과물들은 모두 오버플로 또는 언더플로가 발생한 것
    printf("%u\n", uc);
    printf("%d\n", s);
    printf("%e\n", min);    // 매우 작은 수로 언더플로 발생. 0이 저장됨
    printf("%f\n", max);    // 매우 큰 수로 오버플로 발생. 무한대를 의미하는 1.#INF00가 저장 출력됨.

    return 0;
}
