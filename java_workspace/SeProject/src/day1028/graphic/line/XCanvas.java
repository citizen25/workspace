package day1028.graphic.line;

import java.awt.Canvas;
import java.awt.Graphics;

public class XCanvas extends Canvas{	
	//default ���� �����ڴ� ���� ��Ű���� �ִ� Ŭ������ ���� �����ϴ�.
	LineMaker lineMaker;  //������ null�̴�.
	int x1;
	int x2;
	int y1;
	int y2;
	
	//�� �޼��带 ȣ���ϴ� �ڴ�, LineMaker�� �ּҰ��� �ѱ�� �ȴ�.
	public void setLineMaker(LineMaker lineMaker) {
		this.lineMaker = lineMaker;
	}
	
	/*ĵ������ �����ڰ� ���� �׸��� �׸� �� �ִ� �� ��ȭ���̴�.
	���� paint() �޼��带 �������ϸ� �ȴ�*/
	public void paint(Graphics g) {
		x1 = Integer.parseInt(lineMaker.t_x1.getText());
		x2 = Integer.parseInt(lineMaker.t_x2.getText());
		y1 = Integer.parseInt(lineMaker.t_y1.getText());
		y2 = Integer.parseInt(lineMaker.t_y2.getText());
		g.drawLine(x1, y1, x2, y2);  //�� ���� ������ �� �׸���.
	}
}
