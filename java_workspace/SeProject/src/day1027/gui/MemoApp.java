package day1027.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// GUI. ��, �������� ��ü���� ���� �Ϲ� Ŭ����.
public class MemoApp extends JFrame{
	JMenuBar bar;  //�޴����� �÷����� ����.
	JMenu m_file, m_edit, m_style, m_view, m_help;
	// ��ü �ڷ����� �ڷ����̹Ƿ�, �迭 ����.
	JMenuItem[] items;  //m_file�޴��� ���� �޴��� 8���� �������� �߰��غ���.
	String[] item_title = {"���� �����", "�� â", "����", "����", "�ٸ� �̸����� ����", "������ ����", "�μ�", "������"};
	JTextArea area;
	JScrollPane scroll;  //area�� �ٿ��� ��ũ��.
	
	public MemoApp() {
		bar = new JMenuBar();  //�޴��� ����.
		
		// �޴��� ����.
		m_file = new JMenu("����");
		m_edit = new JMenu("����");
		m_style = new JMenu("����");
		m_view = new JMenu("����");
		m_help = new JMenu("����");

		/* ������ ����.
		����) �޴� �������� �����Ȱ� �ƴ϶�, �������� �� �ڸ� 8ĭ�� Ȯ���� ������.
		js�ʹ� �޸�, �ڹٿ����� �迭�� �ڷ����� �̹� �����Ǹ�, �ش� �ڷ����� ���� �� ����.*/
		items = new JMenuItem[item_title.length];  //[][][][][][][][]		
		
		for(int i=0; i<items.length; i++) {
			items[i] = new JMenuItem(item_title[i]);  //������ ����.
			// 5��°�� �����ϸ� ���м� �߰�.
			if(i==5 || i==7) {
				m_file.addSeparator();  //���м� �߱�.
			}
			m_file.add(items[i]);  //���� �޴��� ������ ����.
		}

		area = new JTextArea();
		scroll = new JScrollPane(area);  //��ũ�� �ް� ���� ������Ʈ�� ������ �Ű������� �ִ´�.
		
		// �ٿ� �޴��� ����.
		bar.add(m_file);
		bar.add(m_edit);
		bar.add(m_style);
		bar.add(m_view);
		bar.add(m_help);
		// �ٴ� ���� Ư������ �ֱ� ������ ��ġ �����ڿ� ������� ������ �������� ��� ������ �ٿ���.
		this.setJMenuBar(bar);  //JFrame�� �� ����.
		
		/* �����ӿ� scroll ���� (���� ���⿡ area�� �����ؾ��� ��ó�� ��������, 
		scroll�� area�� �����ϰ� �����Ƿ� scroll�� �ٿ��� �Ѵ�. */
		add(scroll);
		
		// bar ��Ÿ�� ����.
		bar.setBackground(Color.BLACK);
		m_file.setForeground(Color.WHITE);
		m_edit.setForeground(Color.WHITE);
		m_style.setForeground(Color.WHITE);
		m_view.setForeground(Color.WHITE);
		m_help.setForeground(Color.WHITE);

		// �޴��� ũ�� ����
		m_file.setPreferredSize(new Dimension(100, 45));
		m_edit.setPreferredSize(new Dimension(100, 45));
		m_style.setPreferredSize(new Dimension(100, 45));
		m_view.setPreferredSize(new Dimension(100, 45));
		m_help.setPreferredSize(new Dimension(100, 45));
		
		// area ��Ÿ�� ����.
		area.setBackground(Color.YELLOW);  //area ���� �ٲٱ�. 
		area.setFont(new Font("����ü", Font.BOLD|Font.ITALIC, 20));
		area.setForeground(Color.RED);
		
		setSize(700, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new MemoApp();
	}
	
}
