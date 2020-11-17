package day1113.xml.down;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import common.file.FileManager;

public class DownLoader extends JFrame {

	JButton bt_down;
	JProgressBar bar;
	MovieHandler movieHandler;
	Thread parsingThread;

	public DownLoader() {
		bt_down = new JButton("�ٿ�ε�");
		bar = new JProgressBar();
		
		
		//��Ÿ��
		bar.setPreferredSize(new Dimension(580, 45));
		bar.setForeground(Color.YELLOW);
		bar.setBackground(Color.BLACK);

		bar.setFont(new Font("Verdana", Font.BOLD, 25));
		bar.setStringPainted(true);
		
		setLayout(new FlowLayout());
		add(bt_down);
		add(bar);
		
		
		bt_down.addActionListener((e)->{
			parsingThread = new Thread() {
				public void run() {
					parseData();
					//�� ����� �����ϴ��� ���
					System.out.println(movieHandler.movieList.size());
					int len = movieHandler.movieList.size();  //�� �ٿ�ε� ���� ���� len
					for(int i=0; i<movieHandler.movieList.size(); i++) {
						Movie movie = movieHandler.movieList.get(i);
						download(movie.getUrl());
					}
					//�ݺ����� ��� ����� ���� ������ �ٷ�, �ٿ�ε尡 ��� �Ϸ�� ����
					JOptionPane.showMessageDialog(DownLoader.this, "�� " + len + "���� ���� �ٿ�ε� �Ϸ�");
				}
			};
			parsingThread.start();
		});
		
		setSize(600, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void parseData() {
		//xml�� �Ľ��Ͽ� url�� �����ؾ� ��
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();  //�ļ� ��ü ����
			URL url = this.getClass().getClassLoader().getResource("res/marvel.xml");
			File file = new File(url.toURI());
			saxParser.parse(file, movieHandler = new MovieHandler());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	//���ͳ� ���� �ڿ��� ������ ��, ��Ʈ������ �����͸� �о�� ���� �ϵ� ��ο� ��������
	public void download(String path) {  //�Ű������� ������ �ڿ��� �����Ѵ�
		InputStream is = null;
		FileOutputStream fos = null;  //�ٿ� ���� ������ ������ ��Ʈ��
		int total = 0;  //�ٿ�ε� ���� �ڿ��� �� ����Ʈ ��
		int readCount = 0;  //������� ���� ����Ʈ ��
		bar.setValue(0);
		
		try {
			URL url = new URL(path);
			URLConnection con = url.openConnection();
			HttpURLConnection http = (HttpURLConnection)con;  //���� Ưȭ�� connection ��ü. ���� get/post �� ������� ��û ����
			
			http.setRequestMethod("GET");
			
			//Ŀ�ؼ� ��ü�� �̿��ϸ�, ��� �ڿ��� ũ����� ���� �� �ִ�
			total = con.getContentLength();  //����� �ڿ��� �� ����Ʈ ��ȯ
			
			is = http.getInputStream();  //����� URL�κ��� �Է½�Ʈ�� ���
			long time = System.currentTimeMillis();  //���ϸ����� �������
			String ext = FileManager.getExtend2(path);
			String filename = time + "." + ext;  //�������� ���ϸ�
			System.out.println("������ ���ϸ��� " + filename);
			
			fos = new FileOutputStream("C:/workspace/java_workspace/SeProject/res/download/" + filename);
			int data = -1;
			while(true) {
				data = is.read();
				bar.setValue((int)getPercent(readCount, total));  //int���� �μ��� �־�� �ϹǷ�, �� ��ȯ
				System.out.println(getPercent(readCount, total));
				if(data == -1) break;
				readCount++;
				fos.write(data);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

	//�ۼ�Ʈ�� ���ϴ� �޼��� ����
	public float getPercent(int read, float total) {
		//���� ����Ʈ ��/�� ����Ʈ �� * 100
		return (read/total)*100;
	}
	
	public static void main(String[] args) {
		new DownLoader();
	}
	
}
