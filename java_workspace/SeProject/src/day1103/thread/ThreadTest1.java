/*쓰레드란? Thread.
	하나의 프로세스 내에서 비동기적으로 동작할 수 있는 또 하나의 세부실행 단위를 말한다.
	
Thread의 생명주기 (LifeCycle)
	new에 의해서 탄생한다(여기까지만 개발자가 관여. 개발자는 실행시키고 싶은 코드를 run()에 넣는다. 한번 만들어진 thread는 JVM이 제어).
	-> 탄생시킨 thread를 JVM에 맡긴다. == start()메서드
	-> JVM은 thread를 Runnable 영역에 넣고 thread들을 이것저것 때린다.(실행. run메서드 안의 코드를 실행한다.)
	-> run()메서드의 닫는 브레이스를 만나는 순간 thread는 죽음에 이른다.
	*/

package day1103.thread;

public class ThreadTest1 {

	public static void main(String[] args) {
		//시간 쓰레드 생성하고 동작시켜보자.
		TimeThread tt = new TimeThread();
		tt.start();  //runnable 상태로 진입.
		
		//0.5초마다 별을 출력하는 Thread를 구현하되, 현재 클래스 내에서 구현하자.(내부익명클래스)
		Thread starThread = new Thread() {
			@Override
			public void run() {
				while(true) {
					System.out.println("★");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		starThread.start();  //Runnable 상태로 진입
		
		
		//개발자가 정의한 thread를 이용해서 무한루프 수행.
		MyThread t1 = new MyThread();  //분신 생성.
//		t1.start();  //쓰레드의 수행은 시스템에 맡겨야 한다.(JVM의 Runnalble 영역에 올린 것)
		//thread가 보유한 run()메서드는 JVM에 의해 호출된다.
//		while(true) {
//			System.out.println("☆");			
//		}
	}

}
