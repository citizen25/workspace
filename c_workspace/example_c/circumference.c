// 이미 외부에서 선언된 전역변수임을 알리는 선언
extern double PI;

double getCircum(double r){
    // extern double PI;    // 함수 내부에서만 참조 가능
    return 2*r*PI;          // 전역변수 PI 참조
}