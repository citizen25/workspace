/*초를 실시간 출력할 쓰레드*/
package day1103.thread;

import java.util.Calendar;

public class TimeThread extends Thread{

	@Override
	public void run() {
		while(true) {
			//현재 시간을 구해서 1초마다 갱신해서 출력.
			Calendar cal = Calendar.getInstance();  //추상클래스이므로 자체 메서드로 인스턴스 얻어오자. (Toolkit과 같으 방식)
			//년, 월, 일, 시, 분, 초 출력.
			System.out.println(cal.get(Calendar.YEAR)+"년 "
			+(cal.get(Calendar.MONTH)+1)+"월 "
			+cal.get(Calendar.DATE)+"일 "
			+cal.get(Calendar.HOUR)+"시 "
			+cal.get(Calendar.MINUTE)+"분 "
			+cal.get(Calendar.SECOND)+"초");			
			try {
				Thread.sleep(1000);  //1/1000초까지 표현 가능. 1초 동안 non-runnable 상태로 있다가 다시 복귀하라는 명령.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
