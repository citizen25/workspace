package use;
// 원래는 "C:\\workspace\\java_workspace\\proj_201022\\bin\\use" 인데
// use는 명시되었기 때문에 bin까지의 경로를 classpath로 등록하면 된다.

import animal.Dog;		// 사용하고자 하는 클래스의 위치 명시

class UseDog{

	public static void main(String[] args){
		// 현재 클래스와 다른 경로에 있는 클래스 사용.
		// 컴파일러가 Dog.class를 찾을 수 있는 방법.
		// 현재로서는 컴파일러가 UseDog와 같은 디렉터리만 찾아 헤멘다.
		// 해결하기 위해, 클래스의 경로를 알려줘야 하는데
		// 일반적으로 경로를 등록할 때, 환경변수를 이용.
		// 경로가 일반 파일일 경우 : path 환경변수 사용.
		// 경로가 클래스 파일일 경우 : classpath 환경변수 사용.
		//
		Dog d = new Dog();	// cant find symbol error
		d.run();		// 메서드 호출 추가.
	}
}