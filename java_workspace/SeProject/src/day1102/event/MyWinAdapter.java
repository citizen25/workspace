/*�����ʴ� �������̽��̱� ������, �����ʸ� �����ϴ� �ڴ� �ݵ�� 
	�߻� �޼��带 �������ؾ��Ѵ�.
	������, �����ʰ� ������ �߻�޼����� ���� �ʹ� ���� ���,
	��������� �ʴ� ����ִ� �޼��带 �츮�� ������ Ŭ���� ���� �δ� ���� ȿ�������� ���ϴ�.
	����, sun�翡���� �������� �޼��尡 3�� �̻��� ���, ������ �����ʸ� ������ ����Ͷ�� ��ü�� �������ش�.
	
	ex) WindowListener�� ������ Ŭ���� : WindowAdapter
		MouseListener�� ������ Ŭ���� : MouseApdater
		KeyListener�� ������ Ŭ���� : KayAdatper*/

package day1102.event;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//.java�� ����� ���� : �����ϱ� ����.
public class MyWinAdapter extends WindowAdapter{
	
	public void windowClosing(WindowEvent e) {
		System.out.println("WindowClosing");
		System.exit(0);  //���μ��� ����
	}

}
