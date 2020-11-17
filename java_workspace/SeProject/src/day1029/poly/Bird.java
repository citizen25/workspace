//다형성에 대해 다시 한번 공부해보자.

package day1029.poly;

import java.awt.List;
import java.util.ArrayList;

public class Bird {
	String name="난새";
	String local="한국";
	
	public void fly() {
		System.out.println("새가 날아요.");
	}
	
	public static void main(String[] args) {
		//새들을 대상으로 다형성 공부하기.
		Bird b1 = new Bird();
		Bird b2 = new Duck();  //b2는 Bird클래스 변수, 메서드 접근. 다형성의 특징으로 자식 메서드에 접근 가능. 자료형이 Bird이기 때문에 유연함.
		Duck d = new Duck();  //부모꺼도 내꺼. 범위는 b2보다 더 넒음.
		Bird b3 = new Sparrow();  //유연해진다.
		
		b2.fly();  //새의 행동이 다양해진다.
		b3.fly();
		System.out.println(b2.name);
		System.out.println(d.color);
		
		//유연해지는 것의 이점은 나중에 배우는 부분에서 깨달을 수 있다. (JSP).
	}
}
