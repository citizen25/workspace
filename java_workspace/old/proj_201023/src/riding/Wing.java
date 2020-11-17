package riding;

/* 현대의 제품들은 다중상속성이 크다. ex) 스마트폰. */

// 날개를 정의한다.
// 인터페이스란? 메서드만을 보유한 기능 집합니다.
// 객체는 객체인데, 메서드만을 보유한 단위이므로, sun사에서 금지하고 있는
// 다중상속을 우회하여 구현할 수 있게 해준다.
abstract public interface class Wing{
    // 단 하나라도 추상메서드가 들어있을 경우, 전체가 추상 클래스가 되어버림.
    abstract public void fly();
}
