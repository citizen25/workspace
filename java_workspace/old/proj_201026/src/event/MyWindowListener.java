package event;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

// ������â�� ������� �߻��� �� �ִ� 
public class MyWindowListener implements WindowListener{
    // ���� â�� Ȱ��ȭ ��ų �� (Ŀ���� ���� â�� �ö�� ������� ��)
    public void	windowActivated(WindowEvent e){
        System.out.println("windowActived called");
    }
    // â�� ������
    public void	windowClosed(WindowEvent e){
        System.out.println("windowClosed called");
    }
    // �ݱ� ��ư�� ���� �� (â�� �������� ����)
    public void	windowClosing(WindowEvent e){
        System.out.println("windowClosing called");
    }
    public void	windowDeactivated(WindowEvent e){
        System.out.println("windowDeactivated called");
    }
    // ������ȭ�� �ݴ�
    public void	windowDeiconified(WindowEvent e){
        System.out.println("windowDeiconfied called");
    }
    // �ּ�ȭ ��ư�� ���� ������ȭ ��ų ��
    public void	windowIconified(WindowEvent e){
        System.out.println("windowIconfied called");
    }
    // â�� �� ��
    public void	windowOpened(WindowEvent e){
        System.out.println("windowOpened called");
    }
}
