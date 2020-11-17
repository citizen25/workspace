/*���ΰ��� �����Ѵ�.*/
package day1103.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

public class Hero extends GameObject{
	GamePanel gamePanel;  //enemyList�� �ִ� Ŭ������..
	
	public Hero(GamePanel gamePanel, Image img, int x, int y, int width, int height, int velX, int velY) {
		super(img, x, y, width, height, velX, velY);	
		this.gamePanel = gamePanel;  //�θ�(super()) �Ʒ��� �־�� �Ѵ�.
	}
	
	//������ ��ȭ(�������� ��ȭ)
	public void tick() {
		this.x += this.velX;
		this.y += this.velY;
		
		rect.x=x;
		rect.y=y;
		
		if(gamePanel.hpList.size() >= 1) {  //hp�� 1�� �̻��� ��츸
			collisionCheck();				
		}else {
			//System.out.println("��������");
			gamePanel.over = true;
		}
	}
	
	//������ ���� �浹���� �Ǵ�
	public void collisionCheck() {
		//������ ���� �浹���θ� �Ǵ��ϰ�, ���� �浹�ϸ� ���� hp���̰�, ���װ�.
		for(int i=0; i<gamePanel.enemyList.size(); i++) {
			Enemy enemy = gamePanel.enemyList.get(i);
			if(this.rect.intersects(enemy.rect)) {  //�浹..
				gamePanel.hpList.remove(gamePanel.hpList.size()-1);  //hp ���̰�
				gamePanel.enemyList.remove(enemy);  //���װ�
				gamePanel.score += 10;
				break;
			}
		}
	}
	
	//�׷��� ó��(�͸� �׷��� ó��)
	//��� ���� ĳ���ʹ� �гο� �׷��� �ϹǷ�, g2�� �г��� paint() �޼����� ���������� �޾ƿ���.
	public void render(Graphics2D g2) {
		//g2.setColor(Color.RED);
		g2.drawRect(rect.x, rect.y, rect.width, rect.height);			
		
		//�츮�� �̹� �����ϰ� �ִ� �簢���� �ð�ȭ���Ѻ���.
		g2.drawImage(img, rect.x, rect.y, null);
	}
}
