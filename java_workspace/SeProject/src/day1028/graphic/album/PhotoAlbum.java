package day1028.graphic.album;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PhotoAlbum extends JFrame implements ActionListener{
	AlbumPanel p;  //paint�޼��带 �������Ϸ���, Ŭ������ �������ؾ��Ѵ�.
	
	JPanel p_south;  //��ư 2���� ���� �г�. 
	JButton bt_prev, bt_next;  //����, ���� ��ư
	
	public PhotoAlbum() {
		//����.
		p = new AlbumPanel();
		p_south = new JPanel();
		bt_prev = new JButton("���� ����");
		bt_next = new JButton("���� ����");
		
		//����
		add(p);  //�߾ӿ� �ٹ� �г� �ֱ�.
		p_south.add(bt_prev);
		p_south.add(bt_next);
		add(p_south, BorderLayout.SOUTH);  //���ʿ� ��ư�г� �ֱ�.
		
		bt_prev.addActionListener(this);//���� ��ư�� ������ ����
		bt_next.addActionListener(this);//���� ��ư�� ������ ����
		
		
		setSize(500, 450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new PhotoAlbum();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();  //�̺�Ʈ�� ����Ų �̺�Ʈ �ҽ� ���ؿ���.
		//�̺�Ʈ �ҽ���? �̺�Ʈ�� ����Ų ������Ʈ�� �ǹ�.
		if(obj == bt_prev){//���� ��ư�̸� AlbumPanel.n ����
			p.n--;
		}else if(obj == bt_next){//���� ��ư�̸� AlbumPanel.n ����
			p.n++;
		}
		
		//ȭ�� ���� (���� AlbumPanel��  paint() ȣ�� �Ұ�) -> �����ϵ��� ��û.
		p.repaint();  //�ٽ� �׸���. update() -> paint().
	}
	
}
