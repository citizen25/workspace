/*java.lang에 있는 클래스 중, 클래스에 대한 정보를 가진 클래스가 지원된다.
	Class 클래스*/

package day1102.icon;

import java.lang.reflect.Method;
import java.net.URL;

public class ClassInfo {
	Class myClass;
	
	public void test() {
		
	}
	
	public ClassInfo() {
		//현재 사용 중인 ClassInfo라는 클래스에 대한 정보를 출력해보자.
		//프로그래밍적으로 현재 클래스에 대한 정보를 구해보자.
		myClass = this.getClass();  //현재 인스턴스를 변수에 담자.
		Method[] methods = myClass.getMethods();  //현재 인스턴스가 보유한 메서드들을 반환.
		
		for(int i=0; i<methods.length; i++) {
			System.out.println("현재 객체가 보유한 메서드는 " + methods[i]);
		}
		//사실은 메서드명을 조사하려고 그런게 아니다.
		//Class 클래스를 이요하면, 해당 클래스의 환경 정보 등에도 접근할 수 있다.
		//따라서, 우리는 class path를 조사해서 우리가 사용중인 res라는 패키지의 경로를 조사할 것임.
		//아래의 메서드를 이용하면, 클래스 패스를 기준으로 자원에 접근할 수 있다.
		//패키지에 넣은 자원이 클래스가 아닌 일반파일인 경우 .(dot)이 아닌 /(slash)로 접근해야 함.
		URL url = myClass.getClassLoader().getResource("res/icon_youtube.png");
		System.out.println(url);
		
	}
	
	public static void main(String[] args) {
		new ClassInfo();
	}
}
