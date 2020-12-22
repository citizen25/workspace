package gui;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UseClient {

	public static void main(String[] args) {
		//ChatClient�� has a ����� �����ϰ� �ִ� ��ü���� ���� new���� ����
		//Spring�� ApplicationContext�� �̿��Ͽ� instance���� ����(==Injection)����		
		ClassPathXmlApplicationContext context = null;
		context = new ClassPathXmlApplicationContext("spring/config/gui-context.xml");
		
		ChatClient chatClient = (ChatClient)context.getBean("chatClient");
		chatClient.init();
	}

}
