
class Car{
	String name = "Benz";
	int price = 500;
	String color = "red";	
	Wheel wheel = null;		// �� �ڵ尡 ������ ���� ����?
	// Ŭ���� �ȿ��� �ڷ����� �� �� �ִµ�, �ڹ��� �ڷ����� �� 4���̴�.
	// ���� �� Ŭ���� �ȿ��� ����, ����, ���� �̿ܿ��� ��ü���� �� �� �ִ�.
	// ��ü �ڷ����� �ڷ����̴ϱ�..

	// ������ : Ŭ������� ������ �̸��� �޼��带 ������ "������"�� �ϰ�,
	// �����ڴ� �̸������� �� �� �ֵ��� ��ü�� ���� Ÿ�ӿ� ���� �ʱ�ȭ �۾��� ���� ��,
	// �۾��� �����ϴ� �뵵�� �޼����̴�.
	public Car(){
		this.wheel = new Wheel();
		// wheel = new Wheel();    // ���� this�� ���� �ȴ�.
	}

	public static void main(String[] args){
		// �ڵ����� ����.
		Car c = new Car();

		// �� �ڵ����� �̸� ���.
		System.out.println("name: " + c.name);

		// �� �ڵ��� ������ �귣��, ����, ���� ���.
		System.out.println("brand of wheel: " + c.wheel.brand);
		System.out.println("color of wheel: " + c.wheel.color);
		System.out.println("price of wheel: " + c.wheel.price);
	}
}
