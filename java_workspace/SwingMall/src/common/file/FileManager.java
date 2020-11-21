package common.file;

public class FileManager {
	//���ϸ� ���ϱ� : �Ű������� ������ ��θ� �Ѱܹ޾� ���ϸ� �����Ѵ�
	public static String getFilename(String path) {
		int lastIndex = path.lastIndexOf("/");  //�������� ��ġ�� /(������)�� �ε��� ���ϱ�
		return path.substring(lastIndex+1, path.length());
	}
	
	//Ȯ���� ���ϱ� : �Ű������� ���ϸ��� �Ѱܹ޾� Ȯ���ڸ� �����Ѵ�
	public static String getExtend(String filename) {
		String[] str = filename.split("\\.");  //.(��)�� �������� ���ڿ� �и�. �и� �Ŀ��� �迭�� ��ȯ��. Ư������ "."�� �տ� �������� �ΰ��� �ٿ��� �Ѵ�
		return str[1];  //�ι�° ĭ�� Ȯ������
	}
	
	//Ȯ���� ���ϱ� ������Ʈ : ������ ���� �������� �����;� ������ ����
	public static String getExtend2(String filename) {
		//������ ���� ��ġ ���ϱ�. lastIndexOf �޼��� �������
		
		int lastIndex = filename.lastIndexOf(".");
		
		return filename.substring(lastIndex+1, filename.length());
			//������ �� ���� ���ں��� �����;� �ϹǷ�, +1 �Ѵ�
	}
	/*
	public static void main(String[] args) {
		String filename = getFilename("http://3.bp.blogspot.com/-PTW4W1OlYIw/Wn7idkhYXTI/AAAAAAAAfIY/PAyn3Q1OwZQZxDrmshICJckNCI93AD6_QCK4BGAYYCw/s1600/ironman-3.jpg");
		
	}
	*/
	
}
