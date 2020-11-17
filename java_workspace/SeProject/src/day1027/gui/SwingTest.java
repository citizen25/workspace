/* �̱� ���� �ּ� : ctrl + /(������)
 * �̱� ���� �ּ� ���� : ctrl +\(��������)
 * ��Ƽ ���� �ּ� : ctrl + shift + /(������)
 * ��Ƽ ���� �ּ� ���� : ctrl + shift + \(��������)
 * 
 * AWT�� �÷����� ���� �������� �ٸ��� ǥ���Ǵ� ������ �ֱ⿡, AWT�� ������ Swing�� ����غ���.
 * Swing�� ���Ӱ� ��� �ʿ� ������, ���� �츮�� ����ؿ��� AWT ������Ʈ�� ��κ� J���ξ �ٴ´�. 
 * ����) AWT�� ������ ���ʿ��� ��Ű���� �ƴϴ�.
 * 		AWT ��Ű���� event�� ��ġ �����ڴ� ��õ�� AWT�� �̿��Ѵ�.*/
package day1027.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class SwingTest extends JFrame{
	JCheckBox ch;
	JButton bt;
	
	public SwingTest() {
		ch = new JCheckBox("��ȭ");
		bt = new JButton("�� ��ư");
		setLayout(new FlowLayout());
		add(ch);
		add(bt);
		setVisible(true);
		setSize(300, 400);
		
		 /* Swing�� �������� �ݱ� ��ư�� ������, ȭ�鿡�� �� ���̰� �Ǵµ�, 
		 �̶�, ���α׷��� ����� ���� �ƴ϶� ����, setVisible() �޼��尡 false�� ��ȯ�� ���̴�.
		 ���� ������ ���α׷��� �����Ű����, �ܼ� �����ư�� ��������, �Ʒ��� �ڵ带 �ۼ��ϸ� �ȴ�. */
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new SwingTest();
	}
}
