class UseCat{
	public static void main(String[] args){
		Cat c = new Cat();		// 고양이 인스턴스 1개 생성.
		
		System.out.println("고양이 한마리 탄생!");
		System.out.println("이름: " + c.name);
		System.out.println("나이: " + c.age);
		System.out.println("결혼여부: " + c.isMarried);

		c.walk();
		c.cry();
	}
}