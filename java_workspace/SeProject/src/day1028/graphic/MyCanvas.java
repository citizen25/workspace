package day1028.graphic;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class MyCanvas extends Canvas{
	Toolkit kit;  //플렛폼(os종류:win,mac,linux)에 따라 알맞게 자원을 가져올 수 있도록 해주는 객체.
	Image img;  //얘도 추상이다. 그러나 툴킷을 통해 인스턴스를 얻을 수 있음.
	
	public MyCanvas() {
		kit = Toolkit.getDefaultToolkit();  //자체 클래스 메서드(static)를 통해 인스턴스를 얻는 방식.
		img = kit.getImage("C:/workspace/java_workspace/SeProject/res/travel2/aa.jpg");  //윈도우 os.
	}
	
	/*부모인 캔버스가 보유한 paint메서드를 무시해버리고, 우리 상황에 맞게 재정의하자.
		== override */
	public void paint(Graphics g) {
		/*매개변수인 Graphics 객체는 현실에서의 팔레트와 비유할 수 있지만, 
			단지 생삭만 관여하는 것이 아니라, 도형, 텍스트, 이미지, 선, 점 등
			다양한 그래픽 처리를 위한 기능을 지원하는 실질적 그래픽 처리 담당자이다.*/
		g.drawLine(50,50,300,350);  //캔버스에 선 그리기.
		g.drawOval(0,50,200,200);
		g.drawRect(50,100,100,200);
		
		//붓의 페인트 색상 변경
		g.setColor(Color.RED);
		g.fillRect(200,100,50,50);
		
		g.setColor(Color.BLUE);
		g.drawString("이건 그림 글씨야",50,200);
		
		/*Image : 이미지를 표현한 객체.
			조사해보니 이미지는 추상 클래스이다.
			실망 X : 추상 클래스인 경우, 개발자가 자식 클래스로 구현 후, 자식을 new 해서 사용해야 함이 원칙이지만,
			Sun사가 제공해주는 대부분의 추상 클래스는 사실, 이미 인스턴스를 생성해놓고 간접적으로 쉬게 얻는 메서드를
			공해준다. 또는 인스턴스를 얻을 수 있는 자체 메서드를 지원해주는 경우가 더 많다.
			시스템의 하드디스크를 이용하여 이미지를 가져올 경우엔 플렛폼을 통해 이미지 객체를 가져올 수 있는
			Toolkit 객체를 이용해야하므로, 이미지를 얻기 전에 우선 툴킷 객체의 인스턴스부터 얻자.*/
		g.drawImage(img,20,20,this);  //이미지 관찰자는 캔버스로 하자.
	}
}
