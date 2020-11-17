package day1103.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

public class Bullet extends GameObject{
	GamePanel gamePanel;

	public Bullet(GamePanel gamePanel, Image img, int x, int y, int width, int height, int velX, int velY) {
		super(img, x, y, width, height, velX, velY);
		this.gamePanel = gamePanel;
	}

	//총알에 알맞은 물리량변화 코드 작성
	@Override
	public void tick() {
		this.x += this.velX;
		this.y += this.velY;  //총알이 y축으로도 날아갈 경우 구현.
		rect.x = x;
		rect.y = y;
		
		
		//화면 밖으로 나가면, 화살을 BulletArray에서 제거해야, 그려질 대상이 되지 않음.
		//또한 BulletArray의 크기를 줄여놓아야, 추후 충돌 검사시 반복문의 횟수를 줄일 수 있음.
		if(this.x > GamePanel.WIDTH) {
			gamePanel.bulletList.remove(this);  //나를 리스트에서 지워주세요.
		}

		//총알과 기타 오브젝트와의 충돌검사.
		collisionCheck();
	}

	public void collisionCheck() {
		//총알인 나와 다수의 적군과 교차여부를 판단하여, 교차했다면 -> 나죽고, 너죽자. 점수도 10 올리자.
		for(int i=0; i<gamePanel.enemyList.size(); i++) {
			Enemy enemy = gamePanel.enemyList.get(i);
	
			if(this.rect.intersects(enemy.rect)) {
				//나죽고,(List에서 제거하면, 더이상 tick(), render()호출이 더이상 일어나지 않는다.)
				gamePanel.bulletList.remove(this);
				//너죽자.
				gamePanel.enemyList.remove(enemy);
				//점수 증가.
				gamePanel.score += 10;
				break;
			}
		}
		//총알인 나와 다수의 블럭 간 교차여부
		for(int i=0; i<gamePanel.blockList.size(); i++) {
			Block block = gamePanel.blockList.get(i);
			
			if(this.rect.intersects(block.rect)) {
				gamePanel.bulletList.remove(this);
				gamePanel.blockList.remove(block);
				break;
			}
		}
		
	}
	//총알에 알맞은 그래픽 처리
	@Override
	public void render(Graphics2D g2) {
		g2.drawRect(rect.x, rect.y, rect.width, rect.height);
		g2.drawImage(img, x, y, null);
	}

}
