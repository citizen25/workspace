package use;
// ������ "C:\\workspace\\java_workspace\\proj_201022\\bin\\use" �ε�
// use�� ��õǾ��� ������ bin������ ��θ� classpath�� ����ϸ� �ȴ�.

import animal.Dog;		// ����ϰ��� �ϴ� Ŭ������ ��ġ ���

class UseDog{

	public static void main(String[] args){
		// ���� Ŭ������ �ٸ� ��ο� �ִ� Ŭ���� ���.
		// �����Ϸ��� Dog.class�� ã�� �� �ִ� ���.
		// ����μ��� �����Ϸ��� UseDog�� ���� ���͸��� ã�� ����.
		// �ذ��ϱ� ����, Ŭ������ ��θ� �˷���� �ϴµ�
		// �Ϲ������� ��θ� ����� ��, ȯ�溯���� �̿�.
		// ��ΰ� �Ϲ� ������ ��� : path ȯ�溯�� ���.
		// ��ΰ� Ŭ���� ������ ��� : classpath ȯ�溯�� ���.
		//
		Dog d = new Dog();	// cant find symbol error
		d.run();		// �޼��� ȣ�� �߰�.
	}
}