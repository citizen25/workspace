/*진행상황을 직관적으로 알 수 있는 프로그레스바를 활용해보자*/

package day1103.thread;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class ProgressApp extends JFrame{

	JLabel la;
	JProgressBar bar;
	Thread barThread;  //bar를 증가시킬 thread
	int n=0;
	
	public ProgressApp() {
		la = new JLabel("0", SwingConstants.CENTER);
		bar = new JProgressBar();
		
		barThread = new Thread() {
			@Override
			public void run() {
				while(true) {
					n++;
					setBarValue();
					la.setText(Integer.toString(n));
					if(n>99){break;}
					try {
						Thread.sleep(50);  //non-runnable에 빠져있다가 0.05초 뒤 복귀하라는 명령
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		//스타일
		la.setPreferredSize(new Dimension(500, 150));
		la.setFont(new Font("Verdana", Font.BOLD|Font.ITALIC, 90));
		bar.setPreferredSize(new Dimension(500, 70));
		
		setLayout(new FlowLayout());
		add(la);
		add(bar);
		
		setSize(600, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		barThread.start();  //thread 동작.
	}
	
	//bar의 값 제어
	public void setBarValue() {
		bar.setValue(n);  //20퍼센트가 채워짐.
	}
	
	public static void main(String[] args) {
		new ProgressApp();
	}
}
