package day1030.album;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

//썸네일 하나를 정의한다.
public class Thumb extends JPanel implements MouseListener{
	
	Toolkit kit;  //플랫폼에 의존적인 경로를 이용할 때 툴킷이 필요. window c:/
	Image img;
	
	public static final int WIDTH = 75;
	public static final int HEIGHT = 55;
	
	GalleryApp galleryApp;  //필요하면 has a 로 참조한다. 현재는 값이 null이다.
	
	public Thumb(String src, GalleryApp galleryApp) {
		this.galleryApp = galleryApp;  //injection: 주입받다.
		kit = Toolkit.getDefaultToolkit(); 
		img = kit.getImage(src);
		img = img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.BLACK);
		
		this.addMouseListener(this);  //리스너와 연결
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		//n을 지금 나의 .index값으로 바꾸자.
		//현재 패널이  ArayList 내에서의 몇번째 인덱스에 들어있는지 역추적.
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
