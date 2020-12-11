//SingleTon Pattern : ��ü�� �ν��Ͻ��� 1���� �����ϴ� pattern

package test;

public class Dog {
	String name = "�ǹ�";
	private static Dog instance;
	
	//private���� �����ڸ� ���´�
	private Dog() {
		
	}
	
	//���ƹ�������, ���� �ν��Ͻ��� ������ �ǹ��� �����
	public static Dog getInstance() {
		if(instance == null) {
			instance = new Dog();
		}
		return instance;
	}
	
}
