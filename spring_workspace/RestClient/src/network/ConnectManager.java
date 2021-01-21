// �ڹٵ� html����ó��, web server�� http ����� �����ϴ�

package network;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;


class ConnectManager {
	URL url;
	HttpURLConnection con;    // http ����� ���� ��ü(header + body�� �����Ͽ� ������ �����͸� �ְ�޴� stateless�� ���)


	public void requestByGet() {    // Get������� ��û�� �õ��ϴ� �޼���
		BufferedReader buffr = null;

		try {
			url = new URL("http://192.168.0.26:8888/rest/member");    // ��û �ּ�
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");

			// �����κ��� ���� ������ ��������
			buffr = new BufferedReader(new InputStreamReader(con.getInputStream()));    // ����Ʈ ��� ��Ʈ��

			StringBuilder sb = new StringBuilder();    // ���ڿ��� ������ ��ü
			String data = null;
			while(true) {
				data = buffr.readLine();    // ���� �о���̱�
				if(data == null) {break;}    // �о���� �����Ͱ� ���ٸ� ���ѷ��� ����
				sb.append(data);    // �о���� ���ڿ��� ������Ű��
			}
			System.out.println("������ ���� ���� �����ʹ� " + sb.toString());

			int code = con.getResponseCode();    // �����κ��� ���� �����ڵ� ��ȯ (�� ������ �̹� ������ ��û�� �Ϸ��ϰ� ���䵵 ���� ����)
			System.out.println("�����κ��� ���� ���� �ڵ�� " + code);
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(buffr != null) {
				try {
					buffr.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int requestByPost(String requestUrl, String param) {    // Post������� ��û�� �õ��ϴ� �޼��� (�߿�)
		BufferedWriter buffw = null;    // ���� ó���� ���ڱ�� ��Ʈ��
		int code = 0;    // ������ ���� �ڵ�

		try {
			url = new URL(requestUrl);
			con = (HttpURLConnection)url.openConnection();
			// ������ ������ header�� ÷�������, ���� ������ json�����Ͱ� ���۵Ǿ� �� ������ �ȴ�.
			// �̰� �ٷ� HTTP�������� ���� ����̴�
			con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			con.setRequestMethod("POST");
			con.setDoOutput(true);    // ������ �����͸� ����ϱ� ���� �ʿ��� �ɼ�
			// ��û�� ������ ���� �غ��� ���� �ִٸ�, ���⼭ �غ�����. json���ڿ��� �غ�����
			// JSON��ü ��ü�� �ƴ� ���ڿ��� �غ��ϴ� ���� : ���ڿ��̿��� ����� �����ϹǷ�
			/*
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			sb.append("\"m_id\":\"batman\",");
			sb.append("\"m_pass\":\"1234\",");
			sb.append("\"m_name\":\"��Ʈ��\"");
			sb.append("}");
			*/

			// ���� ���� ���α׷����� ������ �����͸� �������ϹǷ�, ��� ��Ʈ������ �ٲ���
			buffw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));
			buffw.write(param);    // ���� ������
			buffw.flush();    // ���� ����

			code = con.getResponseCode();    // ��û + ������ �߻�
			System.out.println("�����κ��� ���� ���� �ڵ�� " + code);
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(buffw != null) {
				try {
					buffw.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		return code;    // ���� �ڵ� ��ȯ
	}

	public static void main(String[] args) {
		ConnectManager connectManager = new ConnectManager();
		connectManager.requestByPost("http://192.168.0.26:8888/rest/member", );
	}
}