#include <stdio.h>

int main(void){
    int a = 100, b = 50, c;

    printf("%d ", sizeof (short));
    printf("%d ", sizeof a);
    printf("%d ", sizeof 3.5F);
    printf("%d\n", sizeof 3.14);


    // 콤마연산자 , : 왼쪽과 오른쪽 연산식을 각각 계산하며 결과값은 오른쪽에서 수행한 연산의 결과이다. ex) 2, 4의 결과값은 4.
    c = ++a, b++;   // 괄호를 치지 않았기 때문에 c에는 a++값이 들어가고, b는 따로 증감연산을 실행한다.
    printf("%d %d %d\n", a, b, c);
    c = (3 + a, b * 2);     // 괄호가 있어 3+a, b*2를 먼저 수행한 뒤에 콤마연산으로 c에 b*2값이 할당된다.
    printf("%d %d %d\n", a, b, c);

    return 0;
}