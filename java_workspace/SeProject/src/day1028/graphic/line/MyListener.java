package day1028.graphic.line;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener implements ActionListener{
	XCanvas can;  //null.
	
	//������ ĵ���� �ּҸ� �Ѱܹ޾� ����ϱ� ���� �޼���.
	public MyListener(XCanvas can) {
		this.can = can;
	}
	//�ּҸ� �Ѱܹ޴´�? ������! �޼���!!!
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/*ĵ������ ���� �׸���, LineMaker Ŭ������ JTextField�� ���� �̿��Ͽ�..
		paint() �޼���� �����ڰ� ���� ȣ���� ���� ����, ȣ���ؼ��� �ȵȴ�.
			paint() �޼���� �׸��� �׷��� �غ� �Ǿ��� ��, �ý���. �� JVM�� ȣ���Ѵ�.
			���� �����ڰ� ���ϴ� Ÿ�ֿ̹� �׸��� �����ϰ� �Ϸ���, paint() �޼��带 ȣ���ϴ� ���� �ƴ϶�
			������ ���� �ý��ۿ� ��û�ؾ� �Ѵ�!
			�� �� ����ϴ� ���� repaint()�̴�. == �ٽ� �׷��ּ���.
			repaint()ȣ�� == update()ȭ������� -> paint()�׸��� */
		can.repaint();
	}
}
