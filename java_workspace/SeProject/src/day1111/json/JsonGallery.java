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
	
	JPanel p_center;  //�׸��带 ������ ��� �г�
	JPanel p_south;  //������� ������ ���� �г�
	JPanel p_can;  //ū �׸��� �׷��� �г�
	JPanel p_detail;  //�� ������ ��µ� �г�
	JLabel[] la = new JLabel[4];  //[][][][]
	String[] la_title = {"Title", "Phase", "Category", "Release"};
	Image big;  //�� �̹���
	
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
			//��Ÿ��
			la[i].setPreferredSize(new Dimension(380, 50));
			la[i].setFont(new Font("Verdana", Font.BOLD, 28));
			p_detail.add(la[i]);
		}
		
		p_center.setLayout(new GridLayout(1, 2));  //1�� 2ȣ�� �׸��� ����
		
		//��Ÿ�� ����
		p_center.setBackground(Color.YELLOW);
		p_south.setPreferredSize(new Dimension(800, 100));
		p_south.setBackground(Color.GREEN);
		p_can.setBackground(Color.PINK);
		p_detail.setBackground(Color.ORANGE);
		
		//����
		p_center.add(p_can);  //1�� 1ȣ���� �ֱ�
		p_center.add(p_detail);  //1�� 2ȣ���� �ֱ�
		add(p_center);
		add(p_south, BorderLayout.SOUTH);
		
		setVisible(true);
		setSize(800, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		createThumb();
	}
	
	//��ȭ ����� �����ϱ�
	public void createThumb() {
		BufferedReader buffr = null;
	
		//Ŭ�����н� �� �ִ� �ؽ�Ʈ ���� �б�
		try {
			URL url = this.getClass().getClassLoader().getResource("res/data.json");
			URI uri = url.toURI();  //URL�� URI�� ����
			FileReader reader = new FileReader(new File(uri));
			buffr = new BufferedReader(reader);
			
			StringBuffer sb = new StringBuffer();
			String data = null;
			while(true) {
				data = buffr.readLine();
				if(data == null)break;	
				sb.append(data);
			}
			//System.out.println(sb.toString());  //����� ��Ʈ�� ����غ���
			//�Ľ�
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(sb.toString());  //���ڿ��� �Ұ��ߴ� jsonǥ��� ���ڿ��� ���� json��ü�� ��ȯ
			JSONArray jsonArray = (JSONArray)jsonObject.get("marvel");
			//���� �� �������� ��ġ ��üó�� �����Ͽ� ��� �����ϴ�
			for(int i=0; i<jsonArray.size(); i++) {
				JSONObject obj = (JSONObject)jsonArray.get(i);  //��ȭ ���� ��ȯ
				
				//System.out.println(obj.get("title"));  //�丣
				//System.out.println(obj.get("phase"));  //�丣
				
				String u = (String)obj.get("url");
				String title = (String)obj.get("title");
				String phase = (String)obj.get("phase");
				String category_name = (String)obj.get("category_name");
				String release_year = ((Long)obj.get("release_year")).toString();
				
				Movie movie = new Movie(this, 45, 45, u, title, phase, category_name, release_year);
				p_south.add(movie);  //������ ������� p_south�гο� ����	
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
	
	//�� ���� ȣ���ϱ�
	public void getDetail(Image big, String title, String phase, String category, String release) {
		//�̹��� ó��
		this.big = big;
		p_can.repaint();
		
		//���� ���� ��ȭ ���� ���
		la[0].setText(la[0].getText() + " : " + title);
		la[1].setText(la[1].getText() + " : " + phase);
		la[2].setText(la[2].getText() + " : " + category);
		la[3].setText(la[3].getText() + " : " + release);
		
	}
	
	public static void main(String[] args) {
		new JsonGallery();
	}
}
