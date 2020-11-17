package day1029.graphic.color;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorPickerApp extends JFrame{
	
	JPanel p_north;
	JPanel p_center;
	
	ThumbPanel[] thumb = new ThumbPanel[7];
	//7가지 색상을 배열로 보유하자.(반복문마다 다른 색을 사용해야하기 때문)
	Color[] colorArray = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.PINK};
	
	public ColorPickerApp() {
		p_north = new JPanel();
		p_center = new JPanel();
		
		for(int i=0; i<thumb.length; i++) {
			thumb[i] = new ThumbPanel(p_center, colorArray[i]);			
			p_north.add(thumb[i]);
		}
		
		p_center.setBackground(Color.WHITE);
		add(p_north, BorderLayout.NORTH);  //p_north를 프레임의 북쪽에 부착.
		add(p_center);  //프레임의 센터에 부착.
		
		setSize(770, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		//main에서 더이상 사용할 일이 없으므로 레퍼런스 변수 주지 않는다.
		new ColorPickerApp();
	}
}
