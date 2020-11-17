package day1111.json;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Movie extends JPanel implements Runnable{
	Image big;  //전달할 큰 이미지
	Image img;  //썸네일로 그려질 이미지
	BufferedImage buffImg;
	int width;
	int height;
	Thread thread;
	JsonGallery jsonGallery;
	
	//이 객체는 한편의 영화를 표현하는 클래스이다
	String url;
	String title;
	String phase;
	String category_name;
	String release_year;
	
	public Movie(JsonGallery jsonGallery, int width, int height, String url, String title, String phase, String category_name, String release_year) {
		this.jsonGallery = jsonGallery;
		this.width = width;
		this.height = height;
		this.url = url;
		this.title = title;
		this.phase = phase;
		this.category_name = category_name;
		this.release_year = release_year;
		
		this.setPreferredSize(new Dimension(width, height));
		
		thread = new Thread(this);  //Runnable을 구현한 객체를 인수로 넣어준다
		thread.start();  //생성과 동시에 쓰렉드 동작
		
		//리스너와 현재 패널과 연결
		this.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				//System.out.println("mouseReleased() called..");
				System.out.println("클릭한 저의 영화제목은 "+title);
				jsonGallery.getDetail(big, title, phase, category_name, release_year);
			}
		});
	}
	
	public void getErrorImage() {
		URL url = this.getClass().getClassLoader().getResource("res/error.png");
		try {
			BufferedImage buffImg = ImageIO.read(url);
			img = buffImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//그림 그리기
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}

	public void run() {
		//1. 이미지가 로컬 하드에 있을 경우, Toolkit을 사용
		//2. 이미지가 클래스패스(패키지)에 있을 경우, ClassLoader() 이용
		//3. url을 가져오는 경우, BufferedImage 이용 -> ImageIO 이용
		try {
			URL path = new URL(url);
			buffImg = ImageIO.read(path);
			big = buffImg.getScaledInstance(400, 550, Image.SCALE_SMOOTH);  //큰 이미지
			img = buffImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);  //작은 이미지
			
			jsonGallery.p_south.updateUI();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("이미지를 찾을 수 없습니다.");
			getErrorImage();
		}
	}

}
