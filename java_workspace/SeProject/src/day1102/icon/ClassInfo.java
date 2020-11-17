/*java.lang�� �ִ� Ŭ���� ��, Ŭ������ ���� ������ ���� Ŭ������ �����ȴ�.
	Class Ŭ����*/

package day1102.icon;

import java.lang.reflect.Method;
import java.net.URL;

public class ClassInfo {
	Class myClass;
	
	public void test() {
		
	}
	
	public ClassInfo() {
		//���� ��� ���� ClassInfo��� Ŭ������ ���� ������ ����غ���.
		//���α׷��������� ���� Ŭ������ ���� ������ ���غ���.
		myClass = this.getClass();  //���� �ν��Ͻ��� ������ ����.
		Method[] methods = myClass.getMethods();  //���� �ν��Ͻ��� ������ �޼������ ��ȯ.
		
		for(int i=0; i<methods.length; i++) {
			System.out.println("���� ��ü�� ������ �޼���� " + methods[i]);
		}
		//����� �޼������ �����Ϸ��� �׷��� �ƴϴ�.
		//Class Ŭ������ �̿��ϸ�, �ش� Ŭ������ ȯ�� ���� ��� ������ �� �ִ�.
		//����, �츮�� class path�� �����ؼ� �츮�� ������� res��� ��Ű���� ��θ� ������ ����.
		//�Ʒ��� �޼��带 �̿��ϸ�, Ŭ���� �н��� �������� �ڿ��� ������ �� �ִ�.
		//��Ű���� ���� �ڿ��� Ŭ������ �ƴ� �Ϲ������� ��� .(dot)�� �ƴ� /(slash)�� �����ؾ� ��.
		URL url = myClass.getClassLoader().getResource("res/icon_youtube.png");
		System.out.println(url);
		
	}
	
	public static void main(String[] args) {
		new ClassInfo();
	}
}
