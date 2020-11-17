package day1030.album;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

//����� �ϳ��� �����Ѵ�.
public class Thumb extends JPanel implements MouseListener{
	
	Toolkit kit;  //�÷����� �������� ��θ� �̿��� �� ��Ŷ�� �ʿ�. window c:/
	Image img;
	
	public static final int WIDTH = 75;
	public static final int HEIGHT = 55;
	
	GalleryApp galleryApp;  //�ʿ��ϸ� has a �� �����Ѵ�. ����� ���� null�̴�.
	
	public Thumb(String src, GalleryApp galleryApp) {
		this.galleryApp = galleryApp;  //injection: ���Թ޴�.
		kit = Toolkit.getDefaultToolkit(); 
		img = kit.getImage(src);
		img = img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.BLACK);
		
		this.addMouseListener(this);  //�����ʿ� ����
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		//n�� ���� ���� .index������ �ٲ���.
		//���� �г���  ArayList �������� ���° �ε����� ����ִ��� ������.
		galleryApp.n = galleryApp.list.indexOf(this);
		galleryApp.updateData();
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
}
