/*
고양이를 정의하시오
클래스명: Cat
이름: 묘
나이: 3
결혼여부: 미혼
걷는 동작
우는 동작

UseCat 클래스로 Cat의 정보를 화면에 출력해본다.
특히 걷는 동작, 우는 동작 메서드 호출까지 해보기.
*/
class Cat{
	String name = "묘";
	int age = 3;
	boolean isMarried = false;
	
	public void walk(){
		System.out.println("걷는다. 저벅저벅..");
	}

	public void cry(){
		System.out.println("미야옹~");
	}
}