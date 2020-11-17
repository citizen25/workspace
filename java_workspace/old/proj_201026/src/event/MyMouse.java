// 마우스 이벤트 감지하기

package event;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MyMouse implements MouseListener{

    // 마우스 클릭 시, "마우스 클릭했어" 메세지 띄우기.
    public void mouseClicked(MouseEvent e){
        System.out.println("마우스 클릭!");
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
}
