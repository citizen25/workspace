/*���� ������Ʈ ��, ������������ ������ (ǥ)�� ǥ���ϱ⿡ ����ȸ�� JTable�� ����غ���.*/

package day1105.db;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableApp extends JFrame{
	JTable table;
	JScrollPane scroll;
	
	String[] column = {"��ȣ", "�̸�", "����ó", "�ּ�", "����"};
	String[][] data = {
		{"1", "Batman", "011", "����", "��"},
		{"2", "Superman", "017", "ũ����", "��"},
		{"3", "Wonder", "011", "�Ƹ���", "��"}
	};
	
	public TableApp() {
		table = new JTable(data, column);  //row3, col5
		scroll = new JScrollPane(table);
		setLayout(new FlowLayout());
		add(scroll);
		
		//���콺 �̺�Ʈ ����
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				//System.out.println("mouseReleased() called..");
				int col = table.getSelectedColumn();  //������ ������ column index
				int row = table.getSelectedRow();  //������ ������ row index
				//System.out.println("��ǥ("+col+" , "+row+")");
				String value = (String)table.getValueAt(row, col);
				System.out.println(value);
			}
		});
		
		setVisible(true);
		setSize(500, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new TableApp();
	}
}
