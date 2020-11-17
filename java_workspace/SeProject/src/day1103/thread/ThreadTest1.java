/*�������? Thread.
	�ϳ��� ���μ��� ������ �񵿱������� ������ �� �ִ� �� �ϳ��� ���ν��� ������ ���Ѵ�.
	
Thread�� �����ֱ� (LifeCycle)
	new�� ���ؼ� ź���Ѵ�(��������� �����ڰ� ����. �����ڴ� �����Ű�� ���� �ڵ带 run()�� �ִ´�. �ѹ� ������� thread�� JVM�� ����).
	-> ź����Ų thread�� JVM�� �ñ��. == start()�޼���
	-> JVM�� thread�� Runnable ������ �ְ� thread���� �̰����� ������.(����. run�޼��� ���� �ڵ带 �����Ѵ�.)
	-> run()�޼����� �ݴ� �극�̽��� ������ ���� thread�� ������ �̸���.
	*/

package day1103.thread;

public class ThreadTest1 {

	public static void main(String[] args) {
		//�ð� ������ �����ϰ� ���۽��Ѻ���.
		TimeThread tt = new TimeThread();
		tt.start();  //runnable ���·� ����.
		
		//0.5�ʸ��� ���� ����ϴ� Thread�� �����ϵ�, ���� Ŭ���� ������ ��������.(�����͸�Ŭ����)
		Thread starThread = new Thread() {
			@Override
			public void run() {
				while(true) {
					System.out.println("��");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		starThread.start();  //Runnable ���·� ����
		
		
		//�����ڰ� ������ thread�� �̿��ؼ� ���ѷ��� ����.
		MyThread t1 = new MyThread();  //�н� ����.
//		t1.start();  //�������� ������ �ý��ۿ� �ðܾ� �Ѵ�.(JVM�� Runnalble ������ �ø� ��)
		//thread�� ������ run()�޼���� JVM�� ���� ȣ��ȴ�.
//		while(true) {
//			System.out.println("��");			
//		}
	}

}
