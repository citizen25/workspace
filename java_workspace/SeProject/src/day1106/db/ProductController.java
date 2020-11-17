/*JTable은 디자인에 불과하므로, 이 클래스로부터 데이터에 대한 정보를 가져간다
	따라서 TableModel 인터페이스를 구현한 객체인 AbtractTableModel을 상속받자*/

package day1106.db;

import javax.swing.table.AbstractTableModel;

public class ProductController extends AbstractTableModel{

	String[][] data = {};
	String[] column = {"product_id", "subcategory_id", "product_name", "brand", "price", "filename"};
	
	public int getRowCount() {
		return data.length;
	}

	public int getColumnCount() {
		return column.length;
	}

	//컬럼의 제목 가져오기
	//JTable이 getColumnCount()의 갯수만큼 호출하면서 순서대로 0,1,2,3,4,5를 넘기면서 컬럼제목을 가져간다
	public String getColumnName(int col) {
		return column[col];
	}
	
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

}
