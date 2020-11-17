package event;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

// 윈도우창을 대상으로 발생할 수 있는 
public class MyWindowListener implements WindowListener{
    // 현재 창을 활성화 시킬 때 (커서가 현재 창에 올라와 사용중일 때)
    public void	windowActivated(WindowEvent e){
        System.out.println("windowActived called");
    }
    // 창이 닫히면
    public void	windowClosed(WindowEvent e){
        System.out.println("windowClosed called");
    }
    // 닫기 버튼을 누를 때 (창이 닫히지는 않음)
    public void	windowClosing(WindowEvent e){
        System.out.println("windowClosing called");
    }
    public void	windowDeactivated(WindowEvent e){
        System.out.println("windowDeactivated called");
    }
    // 아이콘화의 반대
    public void	windowDeiconified(WindowEvent e){
        System.out.println("windowDeiconfied called");
    }
    // 최소화 버튼을 눌러 아이콘화 시킬 때
    public void	windowIconified(WindowEvent e){
        System.out.println("windowIconfied called");
    }
    // 창이 뜰 때
    public void	windowOpened(WindowEvent e){
        System.out.println("windowOpened called");
    }
}
