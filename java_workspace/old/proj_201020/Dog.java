
class Dog{
	String name="����";
	int age=5;
	static String color = "white";		// �� ������ Ŭ�����κ��� ������ �̽��Ͻ��� �����ö󰡰��� ����,
										// Ŭ���� ������ �� �޶�پ� �־��.. �� �ν��Ͻ� �Ҽ��� �ƴ϶�, Ŭ���� ���� �Ҽ����� ����!

	public void bark(){
		System.out.print("����");
	}

	public static void main(String[] args){		// java.exe �Ű�����
	// static�� �ǹ� : ������ �������ѳ���. instanceȭ �� ��, heap������ ���� �ö��� ����.

		// ���� static ������ ���θ� �� �� �ִ�. ���� �Ʒ��� ǥ���� ����.
		// Dog.color = "black";		// FM���� ǥ�����
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
[static] == ������
	- class Dog{
		String name="����";
		int age=5;

		public void bark(){
			System.out.print("����");
		}

		public static void main(String[] args){		// java.exe �Ű�����
			int a=8;
			Dog d = new Dog();
			boolean k=true;
		}
	}
[stack]
	- int a=8;
	- String[] args
	- Dog instance�� reference ��
[heap]
	- Dog instance �����
		String name="����";
		int age=5;

		public void bark(){
			System.out.print("����");
		}
*/