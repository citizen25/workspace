/*���� ���� ���α׷����� �����͸� �аų� �� ��,
	�� ����Ʈ, �� ���ھ� ������� �����ϸ� 
	�����ͷ��� ���� �� �ʹ� ���� ������� �����ϰ� �ȴ�. == �ӵ�����
	��ġ cmd â�� ���� ó��ó��, �޸𸮰��� ���ۿ� �����͸� ��Ƴ���,
	�� ���� ������ �������� �Է� �� ����� ó���ϸ� ���α׷� ������ ȿ������ �ش�ȭ ��.
	��Ʈ�� �� ���۸� �����ϴ� ��Ʈ���� ���ξ�� Buffered~~ �� �ٴ´�.
	
	�ǽ�) Ű����� �Է¹��� */

package day1102.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BufferedIOApp {
	public static void main(String[] args) {
		//keyboard�� ����� ��Ʈ���� �����ڰ� ���ϴ� Ÿ�ӿ� new �� �� ����.
		//    ��? OS�� �̹� ���� �������Ƿ�. ���� �ڹٿ����� �ý������κ��� ���� �� �ִ�.
	
		//�Է½�Ʈ���� �ֻ��� �߻�  Ŭ�����̴�. (Ű����, ��ĳ�� ����� �� ��Ʈ������ ���� �� �ִ�.)
		InputStream is = System.in;
		
		//�ѱ��� ������ �ʵ���, ���� ������� ���׷��̵�.
		InputStreamReader reader = new InputStreamReader(is);  //����Ʈ ����� ���ڱ�� ��Ʈ������ ����
		BufferedReader buffr = new BufferedReader(reader);  //����ó���� ���� ����� �Է� ��Ʈ��
		
		int data;
		String str = null;
		
		try {
			while(true) {
				//data = reader.read();  //1���� ����
				str = buffr.readLine();
				System.out.print(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
