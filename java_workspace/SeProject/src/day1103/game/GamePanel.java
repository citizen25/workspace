package day1103.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import common.image.ImageUtil;

//��ǻ� ��� ������ �׷���ó���� �г��� ����Ѵ�.
public class GamePanel extends JPanel{
	
	Thread loopThread;  //���� ������ ���ѷ����� ������ thread.
	public static final int WIDTH=1600;
	public static final int HEIGHT=900;
	int x, y;
	
	Hero hero;
	//Bullet bullet;
	//�ټ��� �Ѿ��� ��� ���� �÷��� �����ӿ� �� List�� �̿��غ���.
	ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	ArrayList<Block> blockList = new ArrayList<Block>();
	ArrayList<Hp> hpList = new ArrayList<Hp>();
	
	
	GameBg[] gameBg = new GameBg[2];
	boolean flag = false;  //���ʿ��� ������ �����־�� �ϹǷ�..
	boolean over = false;  //game over ����� �����ϴ� ����.
	int score=0;  //����
	
	public GamePanel() {		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		createBg();  //��� ����.
		createHero();  //���ΰ� ����.
		createEnemy();  //���� ����.
		createBlock();  //�� ����.
		createHp();  //hp ����.
		
		loopThread = new Thread() {
			@Override
			public void run() {
				while(true) {
					if(flag)gameLoop();
					try {
						Thread.sleep(10);  //10/1000��
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		loopThread.start();
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;  //2D�� �� ������ graphics ��ü�� ����ȯ.
		
		g2.setColor(Color.WHITE);
		g2.fillRect(0,  0,  WIDTH,  HEIGHT);  //�г��� ũ�⸸ŭ �簢���� ä������.(ȭ���� ������ �۴� ȿ��)
		
		render(g2);
	}
	
	public void createHero() {
		//�÷��� ���ӵ� ��� : Toolkit    VS    Ŭ�����н� : classLoader.getResources()
		Image img = ImageUtil.getIcon(this.getClass(), "res/game/hero.png", 100, 65).getImage();
		hero = new Hero(this, img, 200, 200, 100, 65, 0, 0);
	}
	
	//GameWindow�κ��� � ����Ű�� ���ȴ��� ���޹���.
	public void moveKey(int key) {
		//37���� �ð��������..
		switch(key) {
			case KeyEvent.VK_LEFT: hero.velX=-5; break;
			case KeyEvent.VK_RIGHT: hero.velX=5; break;
			case KeyEvent.VK_UP: hero.velY=-5; break;
			case KeyEvent.VK_DOWN: hero.velY=5; break;
			case KeyEvent.VK_SPACE: fire(); break;
		}
	}
	public void stopKey(int key) {
		switch(key) {
			case KeyEvent.VK_LEFT: hero.velX=0; break;
			case KeyEvent.VK_RIGHT: hero.velX=0; break;
			case KeyEvent.VK_UP: hero.velY=0; break;
			case KeyEvent.VK_DOWN: hero.velY=0; break;
		}
	}
	
	//�Ѿ� �߻�
	public void fire() {
		Image img = ImageUtil.getIcon(this.getClass(), "res/game/bullet.png", 20, 20).getImage();
		Bullet bullet = new Bullet(this, img, hero.x+hero.width, hero.y, 20, 20, 10, 0);
		bulletList.add(bullet);  //������ �Ѿ��� bulletList�� ����.
	}
	
	//��� �̹��� ����
	public void createBg() {
		Image img = ImageUtil.getIcon(this.getClass(), "res/game/background.jpg", WIDTH, HEIGHT).getImage();
		
		//������ �̹����� ��水ü 2���� ��������.
		gameBg[0] = new GameBg(img, 0, 0, WIDTH, HEIGHT, -1, 0);
		gameBg[1] = new GameBg(img, WIDTH, 0, WIDTH, HEIGHT, -1, 0);
		
		
	}
	
	//���� ����
	public void createEnemy() {
		String[] path = {"enemy1.png", "enemy2.png", "enemy3.png", "enemy4.png", "enemy5.png"};
//		System.out.println(n);
		for(int i=0; i<20; i++) {
			double r = Math.random();
			int n = (int)(r*path.length);
			Image img = ImageUtil.getIcon(this.getClass(), "res/game/"+path[n], 80, 60).getImage();			
			Enemy enemy = new Enemy(img, WIDTH-50, 50+(80*i), 80, 60, -2, 0);
			enemyList.add(enemy);  //���� ��Ͽ� �߰�.
		}
	}
	
	//�� ����
	public void createBlock() {
		for(int i=0; i<20; i++) {
			Image img = ImageUtil.getIcon(this.getClass(), "res/game/block.png", 60, 60).getImage();			
			Block block = new Block(img, 300+(i*60), 600, 60, 60, 0, 0);
			blockList.add(block);  //���� ��Ͽ� �߰�.
		}
	}
	
	//HP����
	public void createHp() {
		Image img = ImageUtil.getIcon(this.getClass(), "res/game/heart.png", 30, 30).getImage();
		for(int i=0; i<4; i++) {
			Hp hp = new Hp(img, 50+(35*i), 85, 30, 30, 0, 0);
			hpList.add(hp);
		}
	}
	
	//������ ��Ȳ, ���� ���.
	public void printData(Graphics2D g2) {
		g2.setFont(new Font("Arial Black", Font.BOLD, 30));
		StringBuffer sb = new StringBuffer();
		sb.append("Bullet: "+bulletList.size());
		sb.append("    Enemy: "+enemyList.size());
		sb.append("    Score: "+score);
		
		g2.drawString(sb.toString(), 50, 50);
		
		if(over)showGameOver(g2);  //�������� �޽��� ����
	}
	
	public void showGameOver(Graphics2D g2) {
		g2.setFont(new Font("Arial Black", Font.BOLD, 200));
		
		StringBuffer sb = new StringBuffer();
		sb.append("Game Over");
		
		g2.drawString(sb.toString(), 50, 400);
	}
	
	//������ ����
	public void tick() {
		hero.tick();
		
		//java ver5 impoved for statement�� for���� ������ ���⼱ ���� �ʴ´�.
		for(int i=0; i<bulletList.size(); i++) {
			Bullet bullet = bulletList.get(i);
			bullet.tick();  //�ټ��� �Ѿ˿� ���� .tick()			
		}
		
		//������ ���� tick()
		for(int i=0; i<enemyList.size(); i++) {
			Enemy enemy = enemyList.get(i);
			enemy.tick();
		}
		
		//���� ���� tick()
		for(int i=0; i<blockList.size(); i++) {
			Block block = blockList.get(i);
			block.tick();
		}
		
		//��濡 ���� tick()
		for(int i=0; i<gameBg.length; i++) {
			gameBg[i].tick();
		}
		
		//hp�� ���� tick()
		for(int i=0; i<hpList.size(); i++) {
			Hp hp = hpList.get(i);
			hp.tick();
		}
	}
	
	public void render(Graphics2D g2) {
		//��濡 ���� render()
		for(int i=0; i<gameBg.length; i++) {
			gameBg[i].render(g2);
		}
		
		hero.render(g2);
		for(int i=0; i<bulletList.size(); i++) {
			Bullet bullet = bulletList.get(i);
			bullet.render(g2);  //�ټ��� �Ѿ˿� ���� .render()			
		}
		for(int i=0; i<enemyList.size(); i++) {
			Enemy enemy = enemyList.get(i);
			enemy.render(g2);
		}
		for(int i=0; i<blockList.size(); i++) {
			Block block = blockList.get(i);
			block.render(g2);
		}
		//hp�� ���� render()
		for(int i=0; i<hpList.size(); i++) {
			Hp hp = hpList.get(i);
			hp.render(g2);
		}
		printData(g2);
	}
	
	//��� ������ tick(), render()�� ȣ��. ��, ���� ����.
	public void gameLoop() {
		tick();
		this.repaint();
		
		//System.out.println("gameLoop() called..");
	}
	
}
