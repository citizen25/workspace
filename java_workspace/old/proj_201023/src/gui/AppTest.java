/* ���ݱ����� �ֿܼ����� �ڹٸ� �����������,
�ڹٵ� �׷��� API�� �����Ѵ�.
������ ����.. �ڹ��� ������� ��ư ����. */

package gui;
import java.awt.Frame;  // �������� ��ġ ����.
import java.awt.Button;  // ��ư�� ��ġ ����.

class AppTest{
    public static void main(String[] args){
        // �ڹٿ����� �������� ������ ���ִ� Ŭ������ Frame�� �����Ѵ�.
        Frame frame;

        frame = new Frame();   // ������ ����.
        frame.setSize(300, 400);   // ������ �����쿡 �ʺ�, ���� �ֱ�.
        frame.setVisible(true);     // �������� �Ӽ��� ���̰�.

        //��ư �����Ͽ�, �����ӿ� ���̱�.
        Button bt = new Button("�� �չ�ư");
        frame.add(bt);
    }
}
