//일반 프라이펜이건, 전기 프라이펜이건, 증기 프라이펜이건 모두를 가리킬 수 있는
//최상위 interface(추상 클래스도 가능하지만, 다중 상속이 될 우려가 있으므로,
//interface가 압도적으로 많이 사용됨)

package food;

public interface Pen {
	//구현 강제할 메서드
	abstract public void boil();
}
