/*
이 클래스는 현실의 사물을 표현할 용도로 정의 X
단지 실행하기 위한 목적이다. == 엔트리 포인드
*/

class UseDog{
	// main 메서드를 보유한 클래스만이 실행되어질 수 있음.
	// 하나의 프로젝트 내엔 실행부는 단 1개만 있으면 됨.
	public static void main(String[] args){

		// Dog는 개발자가 정의한 새로운(기존에 없었던) 자료형.
		// == 사용자 정의 자료형, 모든 클래스는 사용자 정의 자료형이다.
		// 따라서, Dog의 자료형은 Dog형이다.
		// 자바 개발자는 세상에 없는 자료형을 새롭게 정의하므로 마치 조물주.
		Dog d = new Dog();
		System.out.println("강아지 탄생 성공");
		System.out.println("강아지 이름: " + d.name);
		System.out.println("강아지 색상: " + d.color);
		System.out.println("강아지 나이: " + d.age);
	}

}