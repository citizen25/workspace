/*���ݱ����� Sun���� �����ڰ� �������� �״�� ������ƮƲ�� ���ƿ�����,
�� ���������� �츮�� ������Ʈ�� �׷����� ����� �����Ͽ�
���ϴ� �׸����� ������Ʈ�� ���������� ó���غ���
== �׷��� ó���� �����غ���. �츮 �ֵ� �Ͽ� �׸� �׸���.*/
package day1028.graphic;

import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JFrame;

public class PaintTest extends JFrame{
	MyCanvas can;  //��ȭ���� ǥ���� ������Ʈ.
	
	public PaintTest() {
		can = new MyCanvas();
		
		can.setBackground(Color.YELLOW);
		//ĵ������ �׸��� �׸�����, ĵ������ ������ �׸��� �޼����� .paint() �޼��带 ������.
		add(can);  //ĵ������ �����ӿ� ����.
		
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	/*���� Ŭ������ PaintTest�� JFrame�� paint �޼��带 �������̵��ϸ�,
		���� ��, �ڽ��� �������� �޼��带 �켱������ ȣ�����ش�.(�������� �ڽ� Ŭ������..)
		��ư X, JFrame X, Choice X, .. Sun���� ������ �������� ����.
		�׷���, �����ڰ� �ֵ��ؼ� ������ �� �ִ� ������ �ִ�. == ĵ����, �г�. 
		Canvas(AWT), JPanel(�� ����ִ� ������Ʈ)*/
	/*
	public void paint(Graphics g) {
		System.out.println("�� ���� ���� �����θ� �׷���");
	}
	*/
	public static void main(String[] args) {
		new PaintTest();
	}
}
