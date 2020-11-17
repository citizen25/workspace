/* 참새 정의 */
package animal;

public class Sparrow extends Bird{
    String name = "난 참새";
    public void jjack(){
        System.out.println("짹짹");
    }
    /*
    자식 클래스에서 부모와 100% 동일한 메서드를 정의하는 기법 : 오버라이딩 (Overriding).
    왜? 부모의 메서드를 자식에서 기능 변경, 추가 하는 등의 업그레이드를 하기 위해서.
    */
    /* 주의!
        오버로딩 vs 오버라이딩
        오버로딩 : 같은 클래스 내에서 기능이 비슷한 메서드명을 굳이 새로 만들지 말고,
        매개변수 갯수와 자료형으로 구분하면 중복 정의를 인정해 주는것.
        오버라이딩 : 상속관계에서 자식이 부모의 메서드를 재정의 하는 기법. (=업그레이드)
    */
    public void fly(){  // 재정의 : 오버라이딩
        System.out.println("참새가 날아요");
    }
    public static void main(String[] args){
        Sparrow sp = new Sparrow();
        sp.fly();   // 참새의 메서드 호출

        Bird bird = new Bird();
        bird.fly();
        
        Bird bird2 = new Sparrow();
        bird2.fly();    // 결과는?


    }
}
