/*���ϰ� ���õ� ������ ����� ��Ƴ��� Ŭ����*/
package common.file;

import java.io.File;

public class FileManager {
	//Ȯ�尡�� �����ϱ�
	public static String getExtend(String path) {
		
		int lastIndex = path.lastIndexOf(".");
		String ext = path.substring(lastIndex+1, path.length());
		
		return ext;
	}
	
	//���� ���� : ȣ���ڴ� �����ϰ� ���� ������ ��θ� �ѱ��
	public static boolean deleteFile(String path) {
		File file = new File(path);
		return file.delete();
	}
	
	/*�̸� ���� �׽�Ʈ �غ��� ���� main*/
	/*
	public static void main(String[] args) {
		String filename = "C:\\fd\\asd\\sf\\ds\\����.����.��󰫴ٳ�.����.jpg"; 
		getExtend(filename);
	}
	*/
}
