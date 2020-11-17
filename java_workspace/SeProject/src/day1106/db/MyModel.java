/*MVC������ ����� JTable���� ���Ȥ� TableModel�� ��Ī���δ� ��ġ ���ΰ� ó�� ��������,
	�� ���ҷ� ���ٸ� Controller�̴�.
	
	JTable(view)	------- Controller(TableModel) ------- 	������ ������(Model)
	
	*/

package day1106.db;

import javax.swing.table.AbstractTableModel;

public class MyModel extends AbstractTableModel{

	//������ �غ�
	String[][] flower = {
			{"���", "50000", "Red", "Korea"},
			{"ƫ��", "70000", "Purple", "France"},
			{"�Ȱ���", "35000", "White", "Korea"}
	};
	String[][] car = {
			{"BMW", "7000", "White"},
			{"Benz", "95000", "silver"},
			{"Audi", "8000", "black"}
	};

	//���� ������ ��ȯ�ϴ� �޼���
	public int getRowCount() {
		return car.length;
	}
	
	//���� ������ ��ȯ�ϴ� �޼���
	public int getColumnCount() {
		return car[0].length;
	}

	//������ ��ǥ�� ���� ��ȯ
	public Object getValueAt(int row, int col) {
		//System.out.println("row:"+row+", col:"+col);
		return car[row][col];
	}

}
