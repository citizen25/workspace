//�������� ���� �ٽ� �ѹ� �����غ���.

package day1029.poly;

import java.awt.List;
import java.util.ArrayList;

public class Bird {
	String name="����";
	String local="�ѱ�";
	
	public void fly() {
		System.out.println("���� ���ƿ�.");
	}
	
	public static void main(String[] args) {
		//������ ������� ������ �����ϱ�.
		Bird b1 = new Bird();
		Bird b2 = new Duck();  //b2�� BirdŬ���� ����, �޼��� ����. �������� Ư¡���� �ڽ� �޼��忡 ���� ����. �ڷ����� Bird�̱� ������ ������.
		Duck d = new Duck();  //�θ𲨵� ����. ������ b2���� �� ����.
		Bird b3 = new Sparrow();  //����������.
		
		b2.fly();  //���� �ൿ�� �پ�������.
		b3.fly();
		System.out.println(b2.name);
		System.out.println(d.color);
		
		//���������� ���� ������ ���߿� ���� �κп��� ������ �� �ִ�. (JSP).
	}
}
