package day1028.graphic.album;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

//Canvas를 사용하지 않고도, 패널을 이용하면 동일한 효과를 낼 수 있다.
public class AlbumPanel extends JPanel{
	Toolkit kit = Toolkit.getDefaultToolkit();  //플랫폼에 알밎게 자원을 얻어줌.
	//보여질 이미지 10개를 배열로 준비하면 효율성이 있을 것이다.
	String dir = "C:/workspace/java_workspace/SeProject/res/travel2/";
	String[] src = {"aa.jpg","ab.jpg","ax.jpg","bm.jpg","et.jpg","kg.jpg","mx.jpg","pk.jpg","ub.jpg","ya.jpg"};
	Image[] img = new Image[src.length];  //[][][][][][][][][][]
	
	int n = 0;  //인스턴스 변수는 인스턴스가 소멸할 때까지 유지.
	
	//생성자에서 이미지를 생성하자.
	public AlbumPanel() {
		for(int i=0; i<img.length; i++) {
			img[i] = kit.getImage(dir+src[i]);
			img[i] = img[i].getScaledInstance(500, 400, Image.SCALE_SMOOTH);
		}
	}
	
	//우리가 패널을 눈으로 보고 있다는 것은, 이미 paint 메서드 호출에 의해, 그림이 완성되었기 때문이다.
	public void paint(Graphics g) {
		g.drawImage(img[n],0,0,this);
	}

}