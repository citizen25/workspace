
class Dog{
	String name="도그";
	int age=5;
	static String color = "white";		// 이 변수는 클래스로부터 생성된 이스턴스에 딸려올라가가지 말고,
										// 클래스 원본에 딱 달라붙어 있어라.. 즉 인스턴스 소속이 아니라, 클래스 원본 소속으로 선언!

	public void bark(){
		System.out.print("웍웍");
	}

	public static void main(String[] args){		// java.exe 매개변수
	// static의 의미 : 원본에 고정시켜놓음. instance화 될 때, heap영역에 같이 올라가지 않음.

		// 같은 static 끼리는 서로를 볼 수 있다. 따라서 아래의 표현이 가능.
		// Dog.color = "black";		// FM적인 표현방식
		color = "black";
		int a=8;
		Dog d1 = new Dog();
		Dog d2 = new Dog();

		d1.age = 2;
		d2.age = 10;

		boolean k=true;
	}
}


/*
[static] == 고정적
	- class Dog{
		String name="도그";
		int age=5;

		public void bark(){
			System.out.print("웍웍");
		}

		public static void main(String[] args){		// java.exe 매개변수
			int a=8;
			Dog d = new Dog();
			boolean k=true;
		}
	}
[stack]
	- int a=8;
	- String[] args
	- Dog instance의 reference 값
[heap]
	- Dog instance 멤버들
		String name="도그";
		int age=5;

		public void bark(){
			System.out.print("웍웍");
		}
*/