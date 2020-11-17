package day1116.pubapi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class MTMain2 extends JFrame{
	
	//���� ����
	JPanel p_west;
	JTextField t_name;
	JTextField t_op1;
	JTextField t_op2;
	JTextField t_op3;
	JButton bt;
	
	//���� ����
	JTable table;
		//JTable���� �����ϴ� Vector����� 2���� �迭���ٴ� ����������,
		//������ 2���� �迭�� ������ �����ϹǷ�, TableModel�� �̿��� ���� �� VO�� �����ؼ� �����Ѵ�
	MountainModel model;
	JScrollPane scroll;
	
	//��������� �ļ� �����ϱ�
	String apiKey = "Zy%2FGcloPgi2ip5cvJFl4l1f6umtALKjXZOf52uWjv2f4WohIT7RSa31OiAnr1hNLsAZveGqnoRqW6E3eqSinSQ%3D%3D";
	SAXParserFactory factory;
	SAXParser saxParser;
	Thread loadThread;  //��Ʈ��ũ�󿡼� xml�� �ҷ��� �� ����� thread. 
		//��ư�� ���� ������ �ν��Ͻ� �����Ͽ� ��Ʈ��ũ ������ ��Ű�� == �н� ����
	InputStream is;  //xml�����͸� ��� �ִ� ��Ʈ��
	MountainHandler mountainHandler;
	
	//�Ľ��� ������ �ݾ��ֱ� ���� ��������� ����
	BufferedReader rd;
	HttpURLConnection conn;
	
	public MTMain2() {
		//����
		p_west = new JPanel();
		t_name = new JTextField();
		t_op1 = new JTextField();
		t_op2= new JTextField();
		t_op3 = new JTextField();
		bt = new JButton("�˻��ϱ�");
				
		//TableModel�� �̿��� ���� ������� ����
		table = new JTable(model = new MountainModel());
		scroll = new JScrollPane(table);
				
		//��Ÿ�� ����
		p_west.setPreferredSize(new Dimension(200, 600));
		p_west.setBackground(Color.WHITE);
		t_name.setPreferredSize(new Dimension(190, 30));
		t_op1.setPreferredSize(new Dimension(190, 30));
		t_op2.setPreferredSize(new Dimension(190, 30));
		t_op3.setPreferredSize(new Dimension(190, 30));
		
		//����
		p_west.add(t_name);
		p_west.add(t_op1);
		p_west.add(t_op2);
		p_west.add(bt);
		
		add(p_west, BorderLayout.WEST);
		add(scroll);
		
		//��ư�� ������ ����
		bt.addActionListener((e)->{
			//��Ʈ��ũ�� Ÿ�� �����͸� ������ �� ���� thread���� �������� ���� ���� �ʴ�
			//thread�� �����͸����� ó��
			loadThread = new Thread() {
				public void run() {  //��ư ���� �� �� ������ ȣ��
					loadXML();
				}
			};
			//loadXML();
			loadThread.start();  //xml�ε� ������ ȣ��
		});
		
		setSize(900, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	//���� �Ľ��ϴ� �۾��̶�⺸�ٴ� URL���� xml�� �������� �ܰ��̹Ƿ� �޼������ loadXML�� �Ѵ�.
	public void loadXML() {
		//���⿡ ���������� ���п��� ������ �ҽ��ڵ带 �ٿ��ִ´�. Ű���� ��������� ����
		//�Ʒ��� �ڵ带 ��� Exception���� ���Ѵ�(����ó��)
		try {
			StringBuilder urlBuilder = new StringBuilder("http://openapi.forest.go.kr/openapi/service/trailInfoService/getforeststoryservice"); /*URL*/
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + apiKey); /*Service Key*/
			urlBuilder.append("&" + URLEncoder.encode("mntnNm","UTF-8") + "=" + URLEncoder.encode(t_name.getText(), "UTF-8")); /**/
			urlBuilder.append("&" + URLEncoder.encode("mntnHght","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /**/
			urlBuilder.append("&" + URLEncoder.encode("mntnAdd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /**/
			urlBuilder.append("&" + URLEncoder.encode("mntnInfoAraCd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /**/
			urlBuilder.append("&" + URLEncoder.encode("mntnInfoSsnCd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /**/
			urlBuilder.append("&" + URLEncoder.encode("mntnInfoThmCd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /**/
			URL url = new URL(urlBuilder.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			//System.out.println("Response code: " + conn.getResponseCode());
			
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			    rd = new BufferedReader(new InputStreamReader(is = conn.getInputStream()));
			} else {
			    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			
			//�Ʒ��� �ڵ�� ȭ�鿡 ����ϱ� ���� �ڵ��̹Ƿ�, �׽�Ʈ�� ���Ƹ� �����ؾ� �Ѵ�
			//����) read�� ���⼭ �ع����� ������ ���ο����� ��Ʈ���� ���� �����ϰ� �ǹǷ�..
			//�Ľ� �ϱ⵵ ���� �Ҹ���ѹ����� ����
			/*
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
			    sb.append(line);
			}
			*/

			//����� �ҷ���������, sb�� �ֿܼ� ���
			//System.out.println(sb.toString());
			
			parseData();  //�� �������� �Ľ� ����
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//����) ���� �Ľ��� �����ʹ� 
	public void parseData() {
		factory = SAXParserFactory.newInstance();  //���丮 ��ü ����(�� ��ü�� �־�� parser�� ����)
		try {
			saxParser = factory.newSAXParser();  //�ļ� ����
			saxParser.parse(is, mountainHandler = new MountainHandler());  //�Ľ� ����
			
			//�Ľ��� ���Ƹ� TableModel�� ���Ͱ�ü�� �Ľ��� ����� ���� ���ͷ� ��ü�ع����� �ȴ�
			model.data = mountainHandler.mtList;
			table.updateUI();  //���̺� ����
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(rd != null) {
				try {
					rd.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
			if(conn != null) {
				conn.disconnect();				
			}

		}
		
	}
	
	public static void main(String[] args) {
		new MTMain2();
	}

}
