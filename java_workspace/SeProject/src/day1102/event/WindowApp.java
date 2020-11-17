package day1102.event;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class WindowApp extends JFrame{

	public WindowApp() {
		this.addWindowListener(new WindowAdapter(){
			//�̸����� Ŭ����. WindowAdapter�� �ڽ� Ŭ����. == "���� �͸� Ŭ����".
			public void windowClosing(WindowEvent e) {
				System.out.println("WindowClosing");
				System.exit(0);  //���μ��� ����
			}

		});
		
		setSize(300, 400);
		setVisible(true);
	}

	public static void main(String[] args) {
		new WindowApp();
	}
	
}
