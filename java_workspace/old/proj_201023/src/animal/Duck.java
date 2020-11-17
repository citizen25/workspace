package animal;

public class Duck extends Bird{
	/* 
	Duck is a Bird
	Duck형은 Bird형이다.
	오리는 새다.
	*/
	String name = "난 오리";

	public void quack(){
		System.out.println("꽥꽥");
	}
	public static void main(String[] args){
		Duck d1 = new Duck();
		Duck d2 = new Duck();

		Bird b = new Bird();

		int x = 6;	// 4byte
		byte c = 3; // 1byte
		x = c;		// 가능하다. 숫자형끼리는 같은 자료형으로 보니깐..

		//b = d1;		// 가능? ...... 가능하다.
		// 큰 자료형에 작은 자료형을 넣을 수 있듯이,
		// b = d1도 가능하다.

		//b = new Duck();


		byte k = 6;
		short = 8;

		//k = s;	// 불가능: 작은 자료형으로 큰 자료형을 받는것은 불가.
				// 손실이 발생한다.
				// 1) k를 short 이상의 크기의 자료형으로 변경
		k = (byte)s;// 2) 손실을 감안하고, 강제 형변환.

		// 객체 자료형도 자료형이다.
		Dock a = new Duck();
		Bird r = new Bird();
		r = a;		// 가능.
		//a = r;		// 불가능.

		// 객체 자료형도 기본 자료형과 다를것 없다.
		a = (Duck)r;	// 가능. - 강제형변환.(downcasting). 
		r = (Bird)a;	// 작은 자료형에서 큰 자료형으로 올라감 == upcasting

		// 결론 : 객체자료형도 자료형이므로, 기본 자료형의 원칙이
		// 상당부분 그대로 적용되고 있다.

	}
}
