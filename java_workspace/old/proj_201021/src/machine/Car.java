
class Car{
	String name = "Benz";
	int price = 500;
	String color = "red";	
	Wheel wheel = null;		// 이 코드가 낮설지 않은 이유?
	// 클래스 안에는 자료형을 둘 수 있는데, 자바의 자료형은 총 4개이다.
	// 따라서 이 클래스 안에는 문자, 숫자, 논리값 이외에도 객체형도 올 수 있다.
	// 객체 자료형도 자료형이니깐..

	// 생성자 : 클래스명과 동일한 이름의 메서드를 가리켜 "생성자"라 하고,
	// 생성자는 이름에서도 알 수 있듯이 객체의 생성 타임에 무언가 초기화 작업이 있을 때,
	// 작업을 수행하는 용도의 메서드이다.
	public Car(){
		this.wheel = new Wheel();
		// wheel = new Wheel();    // 앞의 this를 빼도 된다.
	}

	public static void main(String[] args){
		// 자동차를 생성.
		Car c = new Car();

		// 이 자동차의 이름 출력.
		System.out.println("name: " + c.name);

		// 이 자동차 바퀴의 브랜드, 색상, 가격 출력.
		System.out.println("brand of wheel: " + c.wheel.brand);
		System.out.println("color of wheel: " + c.wheel.color);
		System.out.println("price of wheel: " + c.wheel.price);
	}
}
