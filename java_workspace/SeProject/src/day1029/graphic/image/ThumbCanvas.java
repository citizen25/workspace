package day1029.graphic.image;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//�̹��� ����Ͽ� ����� Ŭ����
public class ThumbCanvas extends Canvas implements MouseListener{
	Toolkit kit;
	Image img;
	DetailPanel p_center;  //null
	
	public ThumbCanvas(String path, DetailPanel p_center) {
		kit = Toolkit.getDefaultToolkit();
		img = kit.getImage(path);
		this.setPreferredSize(new Dimension(100, 100));
		this.p_center = p_center; 
		
		this.addMouseListener(this);  //���콺 �����ʿ� ����.
	}
	
	//��ȭ���� �׸��� �׸���. ��� ������Ʈ�� ������ �׸��� ��ü����, �׷��� ����̱⵵ �ϴ�.
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		//p_center �гο�, �̹����� �׸���, ���� ���� �̹����� ������..
		
		
		p_center.setImg(img);  //p_center���� ���� img�� �����ؾ� �Ѵ�.
		p_center.repaint();  //�ٽ� �׸���. update():ȭ������� -> paint():�׸���.
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
