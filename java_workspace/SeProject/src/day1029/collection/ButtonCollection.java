package day1029.collection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonCollection extends JFrame implements ActionListener{

	JPanel p_north, p_center;
	JButton bt_create, bt_bg;
	/*�迭�� ���� ū Ư¡ : �迭�� ���� �� �ݵ�� ũ�⸦ ����ؾ� �Ѵ�.
		�迭�� ũ�⸦ �����ϱ� ������,ũ�⸦ �� �� ���� ���α׷������� ��뿡 ������ �ִ�.*/
	//JButton[] btn = new JButton[10000];  
	ArrayList<JButton> btn = new ArrayList<JButton>();  //ũ�Ⱑ 0�̴�.
	
	public ButtonCollection() {
		p_north = new JPanel();
		p_center = new JPanel();
		bt_create = new JButton("��ư ����");
		bt_bg = new JButton("���� ����");
		
		p_north.add(bt_create);
		p_north.add(bt_bg);
		
		//�����ӿ� ����.
		add(p_north, BorderLayout.NORTH);
		add(p_center, BorderLayout.CENTER);
		
		//��ư�� ������ ����.
		bt_create.addActionListener(this);
		bt_bg.addActionListener(this);
		
		setSize(500, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();  //�̺�Ʈ�� ����Ų ������Ʈ�� ��ȯ
		if(obj == bt_create) {
			create();
		}else if(obj.equals(bt_bg)) {
			setBg();
		}
	}
	
	public void create() {
		//��ư �����Ͽ�, p_center�� ����.
		JButton bt = new CustomButton();
		p_center.add(bt);
		
		btn.add(bt);  //����Ʈ�� �߰��ϱ�. js�� push()�޼���� ����.
		System.out.println("������� ������ ����Ʈ�� ���� " + btn.size());
		bt.setText("��ư" + Integer.toString(btn.size()));
		
		//draw���� ���� repaint(), ������ ���� updateUI()�̴�.
		p_center.updateUI();
	}
	
	public void setBg() {
		/*btn ����Ʈ�� ����ִ� ��� ��Ҹ� ������� ���� �ٲٱ�.
			ArrayList�� �����ִ� �����̹Ƿ�, for���� ����� �� �ִ�.*/
		for(int i=0; i<btn.size(); i++) {
			JButton bt = btn.get(i);
			bt.setBackground(Color.YELLOW);
		}
	}
	
	public static void main(String[] args) {
		new ButtonCollection();
	}


}
