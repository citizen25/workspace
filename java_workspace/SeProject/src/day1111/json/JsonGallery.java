package day1111.json;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonGallery extends JFrame{
	
	JPanel p_center;  //그리드를 적용할 가운데 패널
	JPanel p_south;  //썸네일을 부착할 남쪽 패널
	JPanel p_can;  //큰 그림이 그려질 패널
	JPanel p_detail;  //상세 내용이 출력될 패널
	JLabel[] la = new JLabel[4];  //[][][][]
	String[] la_title = {"Title", "Phase", "Category", "Release"};
	Image big;  //상세 이미지
	
	public JsonGallery() {
		p_center = new JPanel();
		p_south = new JPanel();
		p_can = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(big, 0, 0, p_can);
			}
		};
		p_detail = new JPanel();		
		for(int i=0; i<la.length; i++) {
			la[i] = new JLabel(la_title[i]);
			//스타일
			la[i].setPreferredSize(new Dimension(380, 50));
			la[i].setFont(new Font("Verdana", Font.BOLD, 28));
			p_detail.add(la[i]);
		}
		
		p_center.setLayout(new GridLayout(1, 2));  //1층 2호수 그리드 적용
		
		//스타일 적용
		p_center.setBackground(Color.YELLOW);
		p_south.setPreferredSize(new Dimension(800, 100));
		p_south.setBackground(Color.GREEN);
		p_can.setBackground(Color.PINK);
		p_detail.setBackground(Color.ORANGE);
		
		//조립
		p_center.add(p_can);  //1층 1호수에 넣기
		p_center.add(p_detail);  //1층 2호수에 넣기
		add(p_center);
		add(p_south, BorderLayout.SOUTH);
		
		setVisible(true);
		setSize(800, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		createThumb();
	}
	
	//영화 썸네일 생성하기
	public void createThumb() {
		BufferedReader buffr = null;
	
		//클래스패스 상에 있는 텍스트 파일 읽기
		try {
			URL url = this.getClass().getClassLoader().getResource("res/data.json");
			URI uri = url.toURI();  //URL을 URI로 변경
			FileReader reader = new FileReader(new File(uri));
			buffr = new BufferedReader(reader);
			
			StringBuffer sb = new StringBuffer();
			String data = null;
			while(true) {
				data = buffr.readLine();
				if(data == null)break;	
				sb.append(data);
			}
			//System.out.println(sb.toString());  //모아진 스트링 출력해본다
			//파싱
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(sb.toString());  //문자열에 불과했던 json표기법 문자열을 실제 json객체로 변환
			JSONArray jsonArray = (JSONArray)jsonObject.get("marvel");
			//따라서 이 시점부터 마치 객체처럼 접근하여 사용 가능하다
			for(int i=0; i<jsonArray.size(); i++) {
				JSONObject obj = (JSONObject)jsonArray.get(i);  //영화 한편 반환
				
				//System.out.println(obj.get("title"));  //토르
				//System.out.println(obj.get("phase"));  //토르
				
				String u = (String)obj.get("url");
				String title = (String)obj.get("title");
				String phase = (String)obj.get("phase");
				String category_name = (String)obj.get("category_name");
				String release_year = ((Long)obj.get("release_year")).toString();
				
				Movie movie = new Movie(this, 45, 45, u, title, phase, category_name, release_year);
				p_south.add(movie);  //생성된 썸네일을 p_south패널에 부착	
				//p_south.updateUI();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	//상세 내용 호출하기
	public void getDetail(Image big, String title, String phase, String category, String release) {
		//이미지 처리
		this.big = big;
		p_can.repaint();
		
		//제목 등의 영화 정보 출력
		la[0].setText(la[0].getText() + " : " + title);
		la[1].setText(la[1].getText() + " : " + phase);
		la[2].setText(la[2].getText() + " : " + category);
		la[3].setText(la[3].getText() + " : " + release);
		
	}
	
	public static void main(String[] args) {
		new JsonGallery();
	}
}
