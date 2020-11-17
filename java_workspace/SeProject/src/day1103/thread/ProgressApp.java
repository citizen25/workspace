/*�����Ȳ�� ���������� �� �� �ִ� ���α׷����ٸ� Ȱ���غ���*/

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
	Thread barThread;  //bar�� ������ų thread
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
						Thread.sleep(50);  //non-runnable�� �����ִٰ� 0.05�� �� �����϶�� ���
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		//��Ÿ��
		la.setPreferredSize(new Dimension(500, 150));
		la.setFont(new Font("Verdana", Font.BOLD|Font.ITALIC, 90));
		bar.setPreferredSize(new Dimension(500, 70));
		
		setLayout(new FlowLayout());
		add(la);
		add(bar);
		
		setSize(600, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		barThread.start();  //thread ����.
	}
	
	//bar�� �� ����
	public void setBarValue() {
		bar.setValue(n);  //20�ۼ�Ʈ�� ä����.
	}
	
	public static void main(String[] args) {
		new ProgressApp();
	}
}
