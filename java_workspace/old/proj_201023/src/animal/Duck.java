package animal;

public class Duck extends Bird{
	/* 
	Duck is a Bird
	Duck���� Bird���̴�.
	������ ����.
	*/
	String name = "�� ����";

	public void quack(){
		System.out.println("�в�");
	}
	public static void main(String[] args){
		Duck d1 = new Duck();
		Duck d2 = new Duck();

		Bird b = new Bird();

		int x = 6;	// 4byte
		byte c = 3; // 1byte
		x = c;		// �����ϴ�. ������������ ���� �ڷ������� ���ϱ�..

		//b = d1;		// ����? ...... �����ϴ�.
		// ū �ڷ����� ���� �ڷ����� ���� �� �ֵ���,
		// b = d1�� �����ϴ�.

		//b = new Duck();


		byte k = 6;
		short = 8;

		//k = s;	// �Ұ���: ���� �ڷ������� ū �ڷ����� �޴°��� �Ұ�.
				// �ս��� �߻��Ѵ�.
				// 1) k�� short �̻��� ũ���� �ڷ������� ����
		k = (byte)s;// 2) �ս��� �����ϰ�, ���� ����ȯ.

		// ��ü �ڷ����� �ڷ����̴�.
		Dock a = new Duck();
		Bird r = new Bird();
		r = a;		// ����.
		//a = r;		// �Ұ���.

		// ��ü �ڷ����� �⺻ �ڷ����� �ٸ��� ����.
		a = (Duck)r;	// ����. - ��������ȯ.(downcasting). 
		r = (Bird)a;	// ���� �ڷ������� ū �ڷ������� �ö� == upcasting

		// ��� : ��ü�ڷ����� �ڷ����̹Ƿ�, �⺻ �ڷ����� ��Ģ��
		// ���κ� �״�� ����ǰ� �ִ�.

	}
}
