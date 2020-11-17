package gui;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.TextField;

public class MyActionListener implements ActionListener{
	Button bt1; // null
	Button bt2; //null
	TextField t; //null

	public MyActionListener(Button bt1, Button bt2, TextField t){
		this.bt1=bt1;
		this.bt2=bt2;
		this.t=t;
	}

	//인터페이스의 메서드 오버라이딩 (overriding) : 부모의 메서드 재정의!!
	public void actionPerformed(ActionEvent e){
		Object obj=e.getSource(); //이벤트를 일으킨 컴포넌트 반환!!
		//버튼에 의한 이벤트라면 버튼의 주소값을 Object형(상위 객체형)으로 받게되고 
		//TextField에 의한 이벤트라면 TextField의 주소값을 Object형(상위 객체형)으로 받게됨 
		if(obj==bt1){
			System.out.println("bt1을 눌렀네요");
		}else if(obj==bt2){
			System.out.println("bt2을 눌렀네요");
		}else if(obj==t){
			System.out.println("t를 입력했네요");
		}
		
	}

}
