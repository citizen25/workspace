/*�����̶�?
	�ϻ��Ȱ������ ���� �忡 �Ŵ� ���� �����̴�
	����?
		���� ������ ��� ������ ���۽�ų �� �ִ� (�ϻ��Ȱ)
		== ��Ʈ��ũ ������ ���, ��Ʈ��ũ ���� ���α׷����� �����ϰ� �ϴ� �� (���α׷��� �о�)
		����, ��ǻ� ���α׷��� ���� �����ڰ� �����ϴ� ����� ��Ʈ���� �� ���̴�
	
	������ �ڹ� �� �����Ѵ� (X)
		�����ϴ� ��κ��� ���� ���ø����̼ǿ��� �����ϰ� �ִ�(������ ���: c, java, c#, python(��������������))*/

package day1110.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	int port=8989;  //Ŭ���̾�Ʈ�� �޾Ƶ��� ��Ʈ��ȣ. �� ��Ʈ��ȣ�� ���� �ٸ� ��Ʈ��ũ ���μ����� ���е� �� �ִ�. ex)����Ŭ 1521, mysql 3306
	ServerSocket server;  //��ȭ�� ������ �ƴ϶�, �����ڸ� �����ϰ� �����ڰ� �߰ߵǸ� ��ȭ�� ������ ��ȯ���ִ� ��ü
						  //��ġ ��ȭ���� ���� �︮�� �� ���ĺ��� ��ȭ�� �ް� ��ȭ�ϴ� �Ͱ� ���(��ȭ �޴¿� ����)
	
	public EchoServer() {
		try {
			server = new ServerSocket(port);  //�������� ����(���� ������ �ƴ�)
			Socket socket = server.accept();  //�������� �� Ŭ���̾�Ʈ ���� ��ٸ�(������ ���� ������ ���Ѵ�� �Ѵ�. �����߻�)
			System.out.println("������ �߰�");
			//�����ڴ� ��ȯ���� �������κ��� ��ſ� �ʿ��� ����� ��Ʈ���� ���� �� �ִ�
			//�̶� �����ڴ� ��Ʈ��ũ �Ϻο� ���� �ƹ��� ������ ���� �׳� ��Ʈ�� ó���� �ϸ� �˾Ƽ� �������� ��ȭ����
			//����� �����ϸ� �� ���͵��� ������ �˾Ƽ� ���ִ� �Ŵ�
			InputStream is = socket.getInputStream();  //����Ʈ ����� �Է½�Ʈ���̹Ƿ� ������ �ؼ� ������ ����
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader buffr = new BufferedReader(reader);
			
			//int data;
			String data;
			while(true) {
				//data = reader.read();  //1byte �б�(���ϰ� ����� ��Ʈ�����κ���)
				data = buffr.readLine();  //���پ� �б�(���ϰ� ����� ��Ʈ�����κ���)
				//System.out.print((char)data);				
				System.out.print(data);				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EchoServer();
	}

}
