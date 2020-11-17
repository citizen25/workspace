package day1103.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class KeyBoardFileApp {

	String dir;
	FileWriter writer;
	
	public KeyBoardFileApp() {
		URL url = this.getClass().getClassLoader().getResource("res/");
		
		  
		
		try {
			URL url2 = new URL(url, "empty.txt");  //���丮 + ���ϸ�
			
			URI uri = url2.toURI();
			System.out.println(uri);
			
			writer = new FileWriter(new File(uri));
			
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public void saveFile() {
		//Ű����κ��� �Է¹��� �����͸� ���Ϸ� �����غ���.
		//Ű���� ��Ʈ���� System���κ��� ���;� �Ѵ�.
		InputStream is = System.in;

		InputStreamReader reader = new InputStreamReader(is);  //���ڱ�� ��Ʈ������ ���׷��̵�
		BufferedReader buffr = new BufferedReader(reader);  //���� ����� �����Է½�Ʈ������ ���׷��̵�
		
		//���� ��� ��Ʈ�� �迭�� ��(empty) ������ �������ش�.
		//FileWriter writer = new FileWriter();
		
		String msg = null;
		
		try {
			msg = buffr.readLine();
			System.out.println(msg);  //����Ϳ� ���
			writer.write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
		
	
	public static void main(String[] args) {
		new KeyBoardFileApp().saveFile();
	}
}
