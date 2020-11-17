/*�Է½�Ʈ�� ó���� ���Ͽ� ���ѵ� ����� �ƴϴ�.
	��, �������� ���α׷����� �����Ͱ� �귯���´ٸ�, �� ��� ���� �Է½�Ʈ���̴�.!
	���� �� ���������� ������ ������� �����͸� �д� ���� �ƴ϶�,
	Ű���带 ������� �����͸� �о�ͺ���!
	����) ���ϰ��� �޸�, Ű������ ��Ʈ���� �ڹٿ��� ������ �� �ִ� ���� �ƴ϶�, 
		�̹� OS�������� ��Ʈ���� �����ϹǷ�, 
		�ڹٴ� ���� �̹� �����ϴ� ��Ʈ���� ���� �� �� ���� ���̴�.*/

package day1102.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KayBoardInputApp {

	public static void main(String[] args) {
		/*
		//Ű���� ��Ʈ���� �̹� �ý��������� �����ϹǷ�, �ڹ��� System Ŭ�������� ������ �ȴ�.
		InputStream is = System.in;  //ǥ�� �Ϻ� ��Ʈ��(Ű���� or ��Ÿ �Էµ����� ���� ��Ʈ��)
			
		int data;
		try {  //shift + alt + z
			while(true) {
				data = is.read();  //1byte �б�
				//read()�� Ư¡ : �Է��� �Ϸ���� ������, ���� ��� ���·� �ӹ��� ����.
				//��, �Է��� �Ǿ�� read() �޼��� ������ ������ ����� �� �ִ�.
				System.out.print((char)data);  //ǥ�� ��� ��Ʈ��				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		
		/*----�ѱ��� �������� ���ڷ� ó��----*/
		InputStream is = System.in;
		InputStreamReader reader = new InputStreamReader(is);
		int data;
		
		try {
			while(true) {
				data = reader.read();
				if(data == -1) {break;}
				System.out.print((char)data);				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
