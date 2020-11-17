package day1027.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

/* Action은 범위가 넓다. 버튼에는 클릭, 텍스트박스에는 엔터 등... 
	버튼에 클릭 이벤트를 감지해보자 */
public class MyActionListener implements ActionListener{
	JTextField t_input;  //null. 주소값이 없는 상태.
	JTextArea area;  //null. 주소값이 없는 상태.
	
	 public MyActionListener(JTextField t_input, JTextArea area){
		 // 텍스트 필드와 area는 기존 것을 가져와야지, 새롭게 생성하면 의미가 없다.
		 this.t_input = t_input;
		 this.area = area;
	 }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("버튼 눌렀어?");
		
		String msg = t_input.getText();
		area.append(msg + "\n");
		t_input.setText("");
	}
}
