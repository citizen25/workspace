package day1103.game;

import java.awt.Graphics2D;
import java.awt.Image;

public class Enemy extends GameObject{
	public Enemy(Image img, int x, int y, int width, int height, int velX, int velY) {
		super(img, x, y, width, height, velX, velY);
	}

	@Override
	public void tick() {
		//우측끝에서 좌측으로 이동
		this.x += this.velX;
		//변경된 좌표는 즉시 사각형에 반영되어야 총알과 적군이 충돌검사가 될 수 있다.
		rect.x = x;
		rect.y = y;
	}

	@Override
	public void render(Graphics2D g2) {
		g2.drawRect(rect.x, rect.y, rect.width, rect.height);  //충동검사를 위한 사각형.
		g2.drawImage(img, x, y, null);  //게임 유저들을 위한 이미지.
	}
}
