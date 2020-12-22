package food;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UseCook {
	
	public static void main(String[] args) {
		
		
		//스프링을 이용하지 않고 구현한 예
		/*
		FryPen pen = new FryPen();  //fryPen을 올리자
		Cook cook = new Cook();
		
		cook.setPen(pen);  //pen을 요리사에게 주입시키자
		
		cook.makeFood();
		*/
		
		//스프링을 이용한 예 (객체를 주입)
		//xml에 원하는 객체를 명시하면, 이 객체가 작성됨 xml을 파악하여 객체들의 instance를 생성 및 관리해준다
		//이러한 역할을 수행하는 객체를 가리겨 Spring Context 객체라 한다
		ClassPathXmlApplicationContext context = null;  
			//Spring xml 파일 설정 파일을 읽어서 작성된 객체의 instance를 생성 및 관리해준다 (주입도 해준다)
		context = new ClassPathXmlApplicationContext("spring/config/context.xml");
		
		//xml이 이미 읽혀진 상태이므로, 메모리에는 인스턴스들이 존재할 것이고,
		//그 중 어떤 instance를 가져올지는 getBean 메서드로 가져오면 된다
		Cook cook = (Cook)context.getBean("cook");
		cook.makeFood();
		
		
	}

}
