package day1102.event;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class WindowApp extends JFrame{

	public WindowApp() {
		this.addWindowListener(new WindowAdapter(){
			//이름없는 클래스. WindowAdapter의 자식 클래스. == "내부 익명 클래스".
			public void windowClosing(WindowEvent e) {
				System.out.println("WindowClosing");
				System.exit(0);  //프로세스 종료
			}

		});
		
		setSize(300, 400);
		setVisible(true);
	}

	public static void main(String[] args) {
		new WindowApp();
	}
	
}
