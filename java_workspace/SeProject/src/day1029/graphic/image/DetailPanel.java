package day1029.graphic.image;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/*상세 이미지가 그려질 패널. 이 패널을 별도로 정의하는 이유는??
	paint 메서드를 재정의 하기 위함*/
public class DetailPanel extends JPanel{
	private Image img;
	
	//이 메서드를 호출하는 자는 어떤 이미지를 보여줄지를 매개변수로 넘기면 된다.
	public void setImg(Image img) {
		this.img = img;
		this.img = this.img.getScaledInstance(770, 500, Image.SCALE_SMOOTH);
	}
	
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}

}
