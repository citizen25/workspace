package study;

import pet.Cat;		// 앞의 경로는 classpath에 등록되어 있다.

class UseCat{
	public static void main(String args[]){
		Cat c = new Cat();		// 고양이를 사용하려면,
		// classpath 상에서의 고양이의 위치를 명시해야 한다.
		// classpath에는  bin까지 등록되어 있으니, 그 이하 경로를 import해 명시한다.
		c.eat();
	}
}