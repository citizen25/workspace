package day1103.gallery;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import common.image.ImageUtil;

public class AutoGallery extends JFrame{
	JPanel can;
	JButton bt_prev, bt_pause, bt_auto, bt_next;
	
	//os에 종속된 경로로 가져올 때는 Toolkit을 이용한다.
	Toolkit kit;
	Image[] img = new Image[10];  //[][][][][][][][][][]
	int index=0;  //사진 배열의 인덱스
	Thread thread;  //자동 사진 넘기기용 thread
	boolean flag = false;
	
	public AutoGallery() {
		kit = Toolkit.getDefaultToolkit();
		img[0] = ImageUtil.getCustomSize(kit.getImage("C:/workspace/java_workspace/SeProject/res/travel2/aa.jpg"), 680, 550);
		img[1] = ImageUtil.getCustomSize(kit.getImage("C:/workspace/java_workspace/SeProject/res/travel2/ab.jpg"), 680, 550);
		img[2] = ImageUtil.getCustomSize(kit.getImage("C:/workspace/java_workspace/SeProject/res/travel2/ax.jpg"), 680, 550);
		img[3] = ImageUtil.getCustomSize(kit.getImage("C:/workspace/java_workspace/SeProject/res/travel2/bm.jpg"), 680, 550);
		img[4] = ImageUtil.getCustomSize(kit.getImage("C:/workspace/java_workspace/SeProject/res/travel2/et.jpg"), 680, 550);
		img[5] = ImageUtil.getCustomSize(kit.getImage("C:/workspace/java_workspace/SeProject/res/travel2/kg.jpg"), 680, 550);
		img[6] = ImageUtil.getCustomSize(kit.getImage("C:/workspace/java_workspace/SeProject/res/travel2/mx.jpg"), 680, 550);
		img[7] = ImageUtil.getCustomSize(kit.getImage("C:/workspace/java_workspace/SeProject/res/travel2/pk.jpg"), 680, 550);
		img[8] = ImageUtil.getCustomSize(kit.getImage("C:/workspace/java_workspace/SeProject/res/travel2/ub.jpg"), 680, 550);
		img[9] = ImageUtil.getCustomSize(kit.getImage("C:/workspace/java_workspace/SeProject/res/travel2/ya.jpg"), 680, 550);
		
		can = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(img[index], 0, 0, this);
			}
		};
		
		thread = new Thread() {
			public void run() {
				while(true) {
					if(flag) {
						next();								
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		};
		thread.start();
		
		bt_prev = new JButton(ImageUtil.getIcon(this.getClass(), "res/button_prev.png", 100, 35));
		bt_pause = new JButton(ImageUtil.getIcon(this.getClass(), "res/button_pause.png", 100, 35));
		bt_auto = new JButton(ImageUtil.getIcon(this.getClass(), "res/button_auto.png", 100, 35));
		bt_next = new JButton(ImageUtil.getIcon(this.getClass(), "res/button_next.png", 100, 35));
		
		//스타일 적용
		can.setPreferredSize(new Dimension(680, 550));
		can.setBackground(Color.YELLOW);
		bt_prev.setPreferredSize(new Dimension(100, 35));
		bt_pause.setPreferredSize(new Dimension(100, 35));
		bt_auto.setPreferredSize(new Dimension(100, 35));
		bt_next.setPreferredSize(new Dimension(100, 35));
		
		setLayout(new FlowLayout());
		add(can);
		add(bt_prev);
		add(bt_pause);
		add(bt_auto);
		add(bt_next);
		
		//버튼과 리스너 연결
		bt_prev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prev();				
			}
		});
		bt_pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				flag=false;				
			} 
		});
		bt_auto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				flag=true;
			}
		});
		bt_next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				next();				
			} 
		});
		
		setSize(700, 650);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//다음 사진 나오게
	public void next() {
		index++;
		can.repaint();
	}
	
	//다음 사진 나오게
	public void prev() {
		index--;
		can.repaint();
	}
	
	public static void main(String[] args) {
		new AutoGallery();
	}

}
