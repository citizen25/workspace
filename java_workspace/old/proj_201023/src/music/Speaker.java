/* 스피커 정의 */
package music;
import riding.Wing;
public class Speaker extends AudioDevice implements Wing{
    /* 상속이란? 부모의 모든 재산, 빛을 자식이 물려받는 제도.
        부모클래스가 불완전한 추상클래스인 경우, 이건 재산이 아니라 "빛더미"다
        즉 부모의 추상적인 메서드의 완성을 자식에게 떠넘긴 것이다. 
        따라서 자식 클래스에서는 부모의 불완전한 메서드를 완전히 재정의해야,
        이 클래스가 완전한 클래스가 된다.
        이런 제한사항을 가리켜 구현강제라 한다. */

    boolean ooper;  // 우퍼지원 여부
    String color = "red";

    // 스피커는 오디오 디바이스이자, 제트윙이다.
    // 서로간 같은 자료형이다.
    // -> 서로간 형변환이 가능하다. (★★★★★)
    public void sound(){

    }
    /* pm의 부탁을 실수로 까먹음. 따라서 기능 누락된 상태임. */
    public void setVolume(){
        System.out.println("볼륨을 조절해요");
    }    // 브레이스가 존재하기만 해도, 재정의를 완성한 것임.
    public void playMp3(){
        System.out.println("MP3 파일 실행");
    }
    public void fly(){
        System.out.println("나는 기능 추가");
    }
}
