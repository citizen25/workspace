class Shoes{
	String color;		// String은 객체이므로 컴파일러에 의해 null이 대입되어 있을 것이다.
	int price;		// 정수는 컴파일러에 의해 최소한의 관여 결과인 0으로 초기화.

	/* 아래의 두 메서드로 속성을 변경하는 것과, 생성자로 초기화하는 것의 차이는?
		생성자
			생성 시점부터 개성부여. == 물체를 초기화 하는 "타이밍"이 가장 빠르다.
		set 메서드 방식
			개성없는 신발은 만들고, 나중에서야 개성을 부여한다.
			== 다 만들고 나중에 기능 추가
	*/
	public Shoes(String color, int price){
		this.color = color;
		this.price = price;
	}

	public void setColor(String color){		// 색상을 변경하는 메서드
		this.color = color;
	}
	public void setPrice(int price){		// 가격을 변경하는 메서드
		this.price = price;
	}

	public static void main(String[] args){
		Shoes s = new Shoes("yellow", 30000);
		s.setColor("red");
		s.setPrice(20000);

		System.out.println("신발의 색상은 " + s.color + ", 가격은 " + s.price);
	}
}