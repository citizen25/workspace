package day1029.graphic.color;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

//������ �г� �����ϱ�  - ���� �г��� customizing.
public class ThumbPanel extends JPanel implements MouseListener{
	JPanel p_center;  //null
	Color color;
	
	//�ʺ�, ����, ������ ���� �гη� Ÿ���!
	public ThumbPanel(JPanel p_center, Color color) {
		this.p_center = p_center;
		this.color = color;
		this.setPreferredSize(new Dimension(100, 100));
		this.setBackground(color);
		this.addMouseListener(this);  //��(this:���� �г�)�� �����ʿ��� ����.
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Ŭ���ߴ�");
		//���Ϳ��� �г��� ������ ��(�����г�)�� ���� �������� ��������.
		p_center.setBackground(color);
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
