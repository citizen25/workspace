/*
실행부에 매개변수 전달하여 실행해보기
*/
class MainArgs{
	/*
	main의 정체 - 메서드 임에도 우리가 호출한 적도 없는데 실행이 된다.
		메인 메서드는 java.exe에 의해 호출된다.
		따라서 코드 내에서 호출하는 방식이 아니라, 자바의 실행 파일에 의해 호출된다.
	main이 메서드라면, 그 안에 선언된 변수는 매개변수이다.
		그렇다면, java.exe에 의해 main 메서드 호출 시 매개변수의 호출도,
		함께 넘겨야하는 시점 또한 java.exe로 실행될 때이다.
	*/
	public static void main(String[] args){
		System.out.println("실행시 넘겨받은 매개변수의 수 " + args.length);
		// 넘어온 매개변수를 모두 출력하시오.
		for(int i=0; i<args.length; i++){
			System.out.println(args[i]);
		}
	}
}