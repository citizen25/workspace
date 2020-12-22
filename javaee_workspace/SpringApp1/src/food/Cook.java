//요리사를 정의한다

package food;

public class Cook {
	//상위 자료형으로 has a 관계를 명시했을 때 얻는 장점?
	//  하위 자료형이 삭제되거나 변화가 생기더라도, 현재 클래스와 has a 관계이 있는 클래스 간의
	//  의존성을 약화시켰기 때문에 유지보수성이 좋아진다 -> DI라고 한다
	
	private Pen pen;  //정확한 자료형으로 has a 관계를 표시하지 말자
	
	//외부로부터 필요한 객체를 주입받기 위한 setter 메서드
	public void setPen(Pen pen) {
		this.pen = pen;
	}
	
	/*
	public Cook() {
		//pen = new Frypen();  //new가 있는 한, 유지보수 문제는 영원히 해결될 수 없다
			//해결책) new를 제거하자
	}
	*/
	
	public void makeFood() {
		pen.boil();
		
	}
}
