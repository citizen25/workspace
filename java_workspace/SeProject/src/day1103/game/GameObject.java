/*게임에 등장하는 모든 요소는 이 객체의 자식이다.
	따라서 GameObject 클래스는 보편적인 특징만 보유해야 한다.*/ 

package day1103.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class GameObject {
	Image img;
	int x;
	int y;
	int width;
	int height;
	int velX;
	int velY;
	
	//충돌 검사를 위해서는 모든 객체가 사각형을 보유해야 한다.
	Rectangle rect;
	
	public GameObject(Image img, int x, int y, int width, int height, int velX, int velY) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velX = velX;
		this.velY = velY;
		rect = new Rectangle(x, y, width, height);  //기존에 가지고 있는 좌료, 너비, 높이 정보를 이용하여 사각형을 생성.
	}

	//하위 객체가 어떤 내용으로 물리량을 변화시킬지 부모인 현재 시점에서는 알수도 없거니와, 알아서도 안된다.
	//이렇게 미완성으로 남겨놓으면, 이래 어느 시점에 자식이 이 메서드를 자신의 상황에 맞게 재정의할 날이 올것이다.. (==overriding)
	public abstract void tick();   
	public abstract void render(Graphics2D g2);
	
}
