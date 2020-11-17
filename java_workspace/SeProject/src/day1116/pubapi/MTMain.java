package day1116.pubapi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MTMain extends JFrame{
	//���� ����
	JPanel p_west;
	JTextField t_name;
	JTextField t_op1;
	JTextField t_op2;
	JTextField t_op3;
	JButton bt;
	
	//���� ����
	JTable table;
	MountainModel model;
	JScrollPane scroll;
	Vector data = new Vector();  //2���� ���Ͱ� �� ����
		//Generic type�Ƿ� �˸´� �ڷ�����?
		//���� �����ͼ� ���̺� ����� ����. ���� ���� ���;� �Ѵ� -> ���� ǥ��� ���� �Ұ���
	Vector<String> columnName = new Vector<String>();  //�÷� ���� ������ ��� ����
		//JTable���� ������ ���� �� ���ݱ����� ������ �迭�� ��Դµ�, 
		//2���� �迭�� ������ ũ�⸦ ���ؾ��ϱ� ������ ������ ���� ����
		//ex) rs.last() �� rs.getRow()�� ������ �� �� ���ϰ�, �ٽ� Ŀ�� ���󺹱�. ����
		//����, CollectionFramework�� Vector�� �����ϴ� �����ڸ� �̿��Ѵ�	
		//2���� �迭�� �ƴ϶�, Vector�� �����ؼ� �����Ϳ� �÷����� ó���Ѵ�
		//���ʹ� �÷��� �����ӿ��̴� �迭ó�� ������ ũ�⸦ ������� �ʾƵ� �ȴ�. ����~
		
	
	public MTMain() {
		init();  //������ ä���
		
		//����
		p_west = new JPanel();
		t_name = new JTextField();
		t_op1 = new JTextField();
		t_op2= new JTextField();
		t_op3 = new JTextField();
		bt = new JButton("�˻��ϱ�");
				
		//���͸� ����� ���� TableModel ��ü�� ����� �ʿ䰡 ���� ���̴�
		table = new JTable(data, columnName);  //�Ű������� vector�� �ִ´�
		scroll = new JScrollPane(table);
		
		//��Ÿ�� ����
		p_west.setPreferredSize(new Dimension(200, 600));
		p_west.setBackground(Color.WHITE);
		t_name.setPreferredSize(new Dimension(190, 30));
		t_op1.setPreferredSize(new Dimension(190, 30));
		t_op2.setPreferredSize(new Dimension(190, 30));
		t_op3.setPreferredSize(new Dimension(190, 30));
		
		//����
		p_west.add(t_name);
		p_west.add(t_op1);
		p_west.add(t_op2);
		p_west.add(bt);
		
		add(p_west, BorderLayout.WEST);
		add(scroll);
		
		setSize(900, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	//���Ͱ��� �ʱ�ȭ����
	public void init() {
		//������ 1�� �־��
		//JTable�� ���͸� �Ű������� �ִ� ����� ��ü�������� ���� ���߹�Ŀ� ���� ����
		//����) ���;ȿ� ���͸� �־���ϹǷ�, ������ �迭 ��İ� �ٸ� �� ����
		//����, �Ʒ��� ���� ���� �ȿ� �� �ϳ��� ���͸� �߰��ؾ��Ѵ�
		Vector v = new Vector();
		v.add("1");
		v.add("�Ӹ���");
		v.add("������");
		v.add("3000");
		
		data.add(v);  //���� ���� ����. �ᱹ 2���� �迭�� ����� �״�� ����
			//�츮�� ���ߴ� ����� Mountain VO�� �ִ� �ſ�����, ���͸� �����Ѵ�. ���� ���
		//���;��� ���� ��� vs ���� ���� VO ���
			//vo������� ���°� ����.�׷��� �������� �� �����޿� ������ �� �ִ�
			//�׷��� jtable�� vo�� �������� �ʴ´�
			//���� �츮�� ���� ������ �Ѵ� -> MTMain���� ����
		
		//�÷����� ä���
		columnName.add("ID");  //�� ���̵�
		columnName.add("�� �̸�");  //�� �̸�
		columnName.add("������");  //�� ������
		columnName.add("����");  //�� ����
	}
	
	public static void main(String[] args) {
		new MTMain();
	}

}
