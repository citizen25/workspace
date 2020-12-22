package food;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UseCook {
	
	public static void main(String[] args) {
		
		
		//�������� �̿����� �ʰ� ������ ��
		/*
		FryPen pen = new FryPen();  //fryPen�� �ø���
		Cook cook = new Cook();
		
		cook.setPen(pen);  //pen�� �丮�翡�� ���Խ�Ű��
		
		cook.makeFood();
		*/
		
		//�������� �̿��� �� (��ü�� ����)
		//xml�� ���ϴ� ��ü�� ����ϸ�, �� ��ü�� �ۼ��� xml�� �ľ��Ͽ� ��ü���� instance�� ���� �� �������ش�
		//�̷��� ������ �����ϴ� ��ü�� ������ Spring Context ��ü�� �Ѵ�
		ClassPathXmlApplicationContext context = null;  
			//Spring xml ���� ���� ������ �о �ۼ��� ��ü�� instance�� ���� �� �������ش� (���Ե� ���ش�)
		context = new ClassPathXmlApplicationContext("spring/config/context.xml");
		
		//xml�� �̹� ������ �����̹Ƿ�, �޸𸮿��� �ν��Ͻ����� ������ ���̰�,
		//�� �� � instance�� ���������� getBean �޼���� �������� �ȴ�
		Cook cook = (Cook)context.getBean("cook");
		cook.makeFood();
		
		
	}

}
