package day1103.thread;


/*�����ڴ� ���������� �����ϰ� ���� �ڵ尡 ���� ��, thread�� ��ӹ޾� run()�� �������ϸ� �ȴ�.
	��, run()�� �����ڰ� �ۼ��ϰ��� �ϴ� ������ �ۼ��ϸ� �ȴ�.*/
public class MyThread extends Thread{
	
	//JVM�� �Ʒ��� run() �޼��带 �������ָ�, �� ���� ������ running ���¶� �Ѵ�.
	@Override
	public void run() {
		while(true) {			
			System.out.println("�� ");
		}
	}

}
