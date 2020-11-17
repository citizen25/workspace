package event;
import java.awt.Frame;
import java.awt.Button;
import java.awt.TextField;
import java.awt.FlowLayout;
import java.awt.Choice;

class MyWin extends Frame{  //MyWin is a Frame

    Button bt;  //MyWin has a bt.
    TextField t;
    Choice ch;  //html������ select �ڽ��� ����.

    public MyWin(){
        bt = new Button("��ư Ŭ��!");
        t = new TextField(15);
        ch = new Choice();

        // ch�� ������ ä���
        ch.add("Java Programming");
        ch.add("JSP Programming");
        ch.add("Android Programming");
        ch.add("Spring Programming");
        ch.add("Mybatis Programming");

        this.setLayout(new FlowLayout());
        // ��ư�� ������(frame)�� ����
        this.add(bt);  //�������� ����Ʈ�� ButtonLayout�̹Ƿ�, ���� ���� �ʴ´ٸ� ���Ϳ����� ũ�� ���� ���̴�.
        this.add(t);
        this.add(ch);

        bt.addActionListener(new MyListener());  //��ư�� ������ ����.
        t.addKeyListener(new MyKey());  //�ؽ�Ʈ �ڽ��� ������ ����.
        this.addMouseListener(new MyMouse());
        ch.addItemListener(new MyItem());
        this.addWindowListener(new MyWindowListener());

        this.setSize(300, 400);
        this.setVisible(true);
    }
    public static void main(String[] args){
        new MyWin();
    }
}
