package day1029.graphic.color;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorPickerApp extends JFrame{
	
	JPanel p_north;
	JPanel p_center;
	
	ThumbPanel[] thumb = new ThumbPanel[7];
	//7���� ������ �迭�� ��������.(�ݺ������� �ٸ� ���� ����ؾ��ϱ� ����)
	Color[] colorArray = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.PINK};
	
	public ColorPickerApp() {
		p_north = new JPanel();
		p_center = new JPanel();
		
		for(int i=0; i<thumb.length; i++) {
			thumb[i] = new ThumbPanel(p_center, colorArray[i]);			
			p_north.add(thumb[i]);
		}
		
		p_center.setBackground(Color.WHITE);
		add(p_north, BorderLayout.NORTH);  //p_north�� �������� ���ʿ� ����.
		add(p_center);  //�������� ���Ϳ� ����.
		
		setSize(770, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		//main���� ���̻� ����� ���� �����Ƿ� ���۷��� ���� ���� �ʴ´�.
		new ColorPickerApp();
	}
}
