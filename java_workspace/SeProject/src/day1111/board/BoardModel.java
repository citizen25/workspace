/*JTable���� ������ �����ϴ� ��ü�� TableModel�� ����������*/

package day1111.board;

import javax.swing.table.AbstractTableModel;

public class BoardModel extends AbstractTableModel{
	//JTable�� ���� �޼��� ������
	//�Ʒ��� �޼������ JTable�� ȣ���ذ���
	
	String[] column = {"board_id", "title", "writer", "regdate", "hit"};
	String[][] data = {};  //����ִ� 2���� �迭 ����
	
	public int getRowCount() {
		return data.length;
	}
	
	public int getColumnCount() {
		return column.length;
	}
	
	//�÷� ������ ����ϱ� ���ؼ���, �̹� �޼��带 �������̵��ϸ� �ȴ�
	public String getColumnName(int col) {
		return column[col];
	}
	
	//�ش� ������ ������
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
}
