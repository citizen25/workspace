package day1027.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

/* Action�� ������ �д�. ��ư���� Ŭ��, �ؽ�Ʈ�ڽ����� ���� ��... 
	��ư�� Ŭ�� �̺�Ʈ�� �����غ��� */
public class MyActionListener implements ActionListener{
	JTextField t_input;  //null. �ּҰ��� ���� ����.
	JTextArea area;  //null. �ּҰ��� ���� ����.
	
	 public MyActionListener(JTextField t_input, JTextArea area){
		 // �ؽ�Ʈ �ʵ�� area�� ���� ���� �����;���, ���Ӱ� �����ϸ� �ǹ̰� ����.
		 this.t_input = t_input;
		 this.area = area;
	 }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("��ư ������?");
		
		String msg = t_input.getText();
		area.append(msg + "\n");
		t_input.setText("");
	}
}
