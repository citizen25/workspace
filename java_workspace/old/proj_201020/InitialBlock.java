/*
�ʱ�ȭ ��
Why??
*/
class InitialBlock{
	int sum=0;

	// �ν��Ͻ��� ������ ��, �ݺ����� ������ ������ �־��ְ� �ʹ�.
	// Ŭ�������� ����, �޼��常 ������ �� �ִ�. Ŭ���� �ٷ� �ؿ� ����� �� �� ����!

	// ��� ���� �ȿ� {����ȭ}������ �� ���� �ǹ̴�?
	{
		// �� Ŭ������ �ν��Ͻ��� ������ ������ �� ������ ȣ��.
		// �ν��Ͻ� �ʱ�ȭ ��.
		for(int i=1; i<=10; i++){
			sum += i;
		}
		System.out.println("�ν��Ͻ� �ʱ�ȭ �� ȣ��" + sum);
	}
	// static �ʱ�ȭ ��
	// main() �޼��忡 ���� ���� ������ ����Ǵ� �ʱ�ȭ ��
	// ���� ������ ���� �ʱ�ȭ�ϰ� ���� �� ���
	static{
		System.out.println("�����ϱ� �� �ʱ�ȭ �� �����ϰڽ��ϴ�.");
		System.out.println("A");
	}
	public static void main(String[] args){
		System.out.println("B");
		int a=3;
		// �׳� ��ȭ ���ѳ��� ����. Ȥ���� �� �ȿ� ������ �����ϸ�,
		// �ش� �� �������� ��ȿ�ϴ�.
		/*
		{
			int b=5;
		}
		System.out.println(b);
		*/
		new InitialBlock();
		new InitialBlock();
		new InitialBlock();
	}
}