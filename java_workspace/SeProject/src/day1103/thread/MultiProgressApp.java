/*�����Ȳ�� ���������� �� �� �ִ� ���α׷����ٸ� Ȱ���غ���*/

package day1103.thread;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class MultiProgressApp extends JFrame{

	JLabel la;
	JProgressBar bar1, bar2, bar3;
	BarThread t1, t2, t3;  //�����ڸ� �����ε��߱� ������ �ڷ����� �θ��� Thread���ϴ� �ͺ��� Ȯ���ϰ� �ڽ�(BarThread)���� �����Ѵ�. 

	
	public MultiProgressApp() {
		la = new JLabel("0", SwingConstants.CENTER);
		bar1 = new JProgressBar();
		bar2 = new JProgressBar();
		bar3 = new JProgressBar();
		
		//��Ÿ��
		la.setPreferredSize(new Dimension(500, 150));
		la.setFont(new Font("Verdana", Font.BOLD|Font.ITALIC, 90));
		bar1.setPreferredSize(new Dimension(500, 70));
		bar2.setPreferredSize(new Dimension(500, 70));
		bar3.setPreferredSize(new Dimension(500, 70));
		
		setLayout(new FlowLayout());
		add(la);
		add(bar1);
		add(bar2);
		add(bar3);
	
		//thread 3�� �����Ͽ� runnable�� ���Խ�Ű��.
		t1 = new BarThread(bar1, 150);
		t2 = new BarThread(bar2, 250);
		t3 = new BarThread(bar3, 100);
		t1.start();
		t2.start();
		t3.start();
		
		setSize(600, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		new MultiProgressApp();
	}
}
