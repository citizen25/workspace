package day1030.io;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/*Stream�̶�? ���ǿ����� �帣�� ���ٱ⸦ �ǹ�
	���꿡���� �帧�� ����� ���� �ƴ� �������̴�.
	But ���꿡���� �帧�� ���⿡ ���� �ٸ��� ���� �з�.(������ ���� ���� ���α׷�)
	1) �Է�(Input): �������� ���α׷����� �����Ͱ� �귯 ���� ��.
	2) ���(Output): �������� ���α׷����� �����Ͱ� �귯 ������ ��.
	
	�ڹٿ����� ����°� ���õ� ��Ű������ java.io�̴�. ���⿡�� ����� ó���� ���� ������ api ����
	--------------------------------------------------------------------------
���ܶ�? ���α׷��� ���� ����Ǿ��� �� ���� �������� ��Ȳ�� �ǹ�. (==����)
	������ �߻��ϸ�? ���α׷��� ������ ���ᰡ �Ǿ������.
	����ó���� ����(try-catch): ������ ������ ����.*/

public class FileReadApp {
	//������ ������� �����͸� �о���̴� FileInputStream�� �н��غ���.
	FileInputStream fis;

	public FileReadApp(){
		File file = new File("C:/workspace/java_workspace/SeProject/res/data/memo.txt");

		//���û��� ������ ������� ��Ʈ��(��)�� ��������
		try{  //�� ���� ������ �߻��� ���ɼ��� �ִ� �ڵ����� ���.
			fis = new FileInputStream(file);
			System.out.println("Stream ���� �����Դϴ�.");

			int data;

			while(true){  //���ѷ���
				data = fis.read();  //1����Ʈ �о���̱�
				if(data==-1)break;  //������ ���� �����ϸ�, �ݺ��� ��������.
				System.out.print((char)data);				
			}
			
		}catch(FileNotFoundException e){  /*Ȥ���� ����ߴ� ������ �߻��ߴٸ�, ������ ������������,
									����δ� �� catch�� ���� �����϶�.(==������ġ)*/
			/*FileNotFoundException e: sun�翡�� �̸� ���ɼ� �ִ� ������ ��üȭ ���ѳ��� �� �� �ϳ�.
				���� ����ߴ� ������ �߻��ϸ�, jvm�� ���� FileNotFoundException �ν��Ͻ��� �޸𸮿� �ö����,
				catch���� �μ��� �����Ͽ�, �����ڷ� �Ͽ��� ������ ���� ������ �м��� �� �ִ� ��ȸ�� �ִ� ���̴�.*/

			System.out.println("������ ã�� �� �����ϴ�.");
			e.printStackTrace();  //stack ������ ������ ������ ����϶�.
		}catch(IOException e){
			System.out.println("������ ���� �� �����ϴ�.");  //�Ϲ����� ���� �̸�.
			e.printStackTrace();  //�����ڸ� ���� ����.
		}finally {  /*�� ������ try���� �����ϴ�, catch���� �����ϴ� ������ ���ļ� �����ϴ� �����̹Ƿ�,
						�� ������ �ڿ��� �ݴ� �ڵ带 �ۼ��ؾ� �Ѵ�.
						�ַ� �����ͺ��̽����� ���� ����, ��Ʈ�� ���� ����*/
			
			//null�� �ƴ� ���� �ݱ� ó��.
			if(fis != null) {
				//db, stream ���� �� �ݵ�� null ���θ� �������� ������ ������.
				try {
					fis.close();  //��Ʈ���� ����.
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		}
	}

	public static void main(String[] args){
		new FileReadApp();
	}
}
