package use;

// 개념상으로만 가능.. 아래처럼 명시하면, c: 경로때문에 문제가 발생
// 이 자바 코드는 window, linux, mac에서 모두 실행되어야 하므로,
// 특정 os에 의존적인 경로로 명시하면 안된다.

// 
// os 환경변수 - path : 실행하고픈 파일의 경로 위치.
//			classpath == 실행하고픈 클래스 파일의 경로 위치.(C:\workspace\java_workspace\proj_201021\bin)

// JVM은 파일을 실행하기 전에 classpath 먼저 확인한다. 이 classpath를 참조해서 접근한다.


import fashion.down.Pants;		// 실행을 위해서는 사용하고픈 클래스의 위치를 등록해야한다. == bin의 위치를 등록한다.

class UsePants{

	public static void main(String[] args){
		// 현재 클래스와는 물리적으로 떨어져 있는, 다른 패키지에 들어있는 클래스 사용해보기
		// js : <script src="경로/파일명"></script>
		Pants p = new Pants();		// 찾지 못함..
		System.out.println(p);
	}
}