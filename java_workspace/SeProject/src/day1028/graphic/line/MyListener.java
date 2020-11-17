package day1028.graphic.line;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener implements ActionListener{
	XCanvas can;  //null.
	
	//기존의 캔버스 주소를 넘겨받아 사용하기 위한 메서드.
	public MyListener(XCanvas can) {
		this.can = can;
	}
	//주소를 넘겨받는다? 무조건! 메서드!!!
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/*캔버스에 선을 그리되, LineMaker 클래스의 JTextField의 값을 이용하여..
		paint() 메서드는 개발자가 직접 호출할 수도 없고, 호출해서도 안된다.
			paint() 메서드는 그림이 그려질 준비가 되었을 때, 시스템. 즉 JVM이 호출한다.
			따라서 개발자가 원하는 타이밍에 그림을 갱신하게 하려면, paint() 메서드를 호출하는 것이 아니라
			갱신할 것을 시스템에 요청해야 한다!
			이 때 사용하는 것이 repaint()이다. == 다시 그려주세요.
			repaint()호출 == update()화면지우기 -> paint()그리기 */
		can.repaint();
	}
}
