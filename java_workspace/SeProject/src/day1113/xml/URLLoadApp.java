/*���ݱ����� URL���� �ڿ��� ������ ��, �� ����� �̹����� �Ͽ���
	ex) ImageIO.read() ��

���� �� �ǽ������� �̹��� �Ӹ� �ƴ϶� �������� ��� ������ �ڿ��� ������� �����Ͽ�
	��Ʈ������ �����͸� �о�� �ǽ��� �����غ���*/

package day1113.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLLoadApp {
	//������ ��� �ڿ��� �������, ���� �� �����͸� �о�� �� �ִ� ��ü
	URLConnection con;  //�߻� Ŭ�����̹Ƿ�, URL ��ü�κ��� �ν��Ͻ��� ��´�
	HttpURLConnection http;
	URL url;
	FileOutputStream fos;
	
	public URLLoadApp() {
		try {
			url = new URL("https://images.unsplash.com/photo-1503072000956-b1ba82f2a278?ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80");
			//������ �������� �ڿ��� ������ �õ�
			con = url.openConnection();
			http = (HttpURLConnection)con;
			
			//http�� �ڿ��� GET ������� �ڿ��� ��û����
			http.setRequestMethod("GET");
			
			//���� ��ü�κ��� ��Ʈ���� ���ͼ� �����͸� �о��
			InputStream is = http.getInputStream();
			
			//���Ϸ� �����غ��� (�׳� �Ϲ� ���丮 res�� ��������)
			File file = new File("C:/workspace/java_workspace/SeProject/res/copy.jpg");
			fos = new FileOutputStream(file);
			
			//�� ����Ʈ�� �о�ͼ� ��½�Ʈ���� �̿��Ͽ� ���Ͽ� ����
			int data = -1;  //ó������ �о���� �����Ͱ� ���ٰ� ����
			
			while(true) {
				data = is.read();
				if(data == -1) break;
				fos.write(data);
			}
			System.out.println("���ͳ� ������ ���÷� ���� �Ϸ�");
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
		}
	}
	
	public static void main(String[] args) {
		new URLLoadApp();
	}
}
