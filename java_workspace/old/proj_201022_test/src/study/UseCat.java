package study;

import pet.Cat;		// ���� ��δ� classpath�� ��ϵǾ� �ִ�.

class UseCat{
	public static void main(String args[]){
		Cat c = new Cat();		// ����̸� ����Ϸ���,
		// classpath �󿡼��� ������� ��ġ�� ����ؾ� �Ѵ�.
		// classpath����  bin���� ��ϵǾ� ������, �� ���� ��θ� import�� ����Ѵ�.
		c.eat();
	}
}