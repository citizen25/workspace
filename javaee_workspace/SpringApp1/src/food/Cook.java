//�丮�縦 �����Ѵ�

package food;

public class Cook {
	//���� �ڷ������� has a ���踦 ������� �� ��� ����?
	//  ���� �ڷ����� �����ǰų� ��ȭ�� �������, ���� Ŭ������ has a ������ �ִ� Ŭ���� ����
	//  �������� ��ȭ���ױ� ������ ������������ �������� -> DI��� �Ѵ�
	
	private Pen pen;  //��Ȯ�� �ڷ������� has a ���踦 ǥ������ ����
	
	//�ܺηκ��� �ʿ��� ��ü�� ���Թޱ� ���� setter �޼���
	public void setPen(Pen pen) {
		this.pen = pen;
	}
	
	/*
	public Cook() {
		//pen = new Frypen();  //new�� �ִ� ��, �������� ������ ������ �ذ�� �� ����
			//�ذ�å) new�� ��������
	}
	*/
	
	public void makeFood() {
		pen.boil();
		
	}
}
