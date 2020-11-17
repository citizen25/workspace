/*���ӿ� �����ϴ� ��� ��Ҵ� �� ��ü�� �ڽ��̴�.
	���� GameObject Ŭ������ �������� Ư¡�� �����ؾ� �Ѵ�.*/ 

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
	
	//�浹 �˻縦 ���ؼ��� ��� ��ü�� �簢���� �����ؾ� �Ѵ�.
	Rectangle rect;
	
	public GameObject(Image img, int x, int y, int width, int height, int velX, int velY) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velX = velX;
		this.velY = velY;
		rect = new Rectangle(x, y, width, height);  //������ ������ �ִ� �·�, �ʺ�, ���� ������ �̿��Ͽ� �簢���� ����.
	}

	//���� ��ü�� � �������� �������� ��ȭ��ų�� �θ��� ���� ���������� �˼��� ���ŴϿ�, �˾Ƽ��� �ȵȴ�.
	//�̷��� �̿ϼ����� ���ܳ�����, �̷� ��� ������ �ڽ��� �� �޼��带 �ڽ��� ��Ȳ�� �°� �������� ���� �ð��̴�.. (==overriding)
	public abstract void tick();   
	public abstract void render(Graphics2D g2);
	
}
