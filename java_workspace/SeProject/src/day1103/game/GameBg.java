package day1103.game;

import java.awt.Graphics2D;
import java.awt.Image;

public class GameBg extends GameObject{

	public GameBg(Image img, int x, int y, int width, int height, int velX, int velY) {
		super(img, x, y, width, height, velX, velY);
	}

	@Override
	public void tick() {
		this.x += this.velX;  //음수방향으로 이동할 예정
		if(this.x <= -GamePanel.WIDTH) {
			this.x = GamePanel.WIDTH;
		}
	}

	@Override
	public void render(Graphics2D g2) {
		g2.drawImage(img, x, y, null);
		
	}

}
