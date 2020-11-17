/*JTable에게 정보를 제공하는 객체인 TableModel을 재정의하자*/

package day1111.board;

import javax.swing.table.AbstractTableModel;

public class BoardModel extends AbstractTableModel{
	//JTable을 위한 메서드 재정의
	//아래의 메서드들은 JTable이 호출해간다
	
	String[] column = {"board_id", "title", "writer", "regdate", "hit"};
	String[][] data = {};  //비어있는 2차원 배열 선언
	
	public int getRowCount() {
		return data.length;
	}
	
	public int getColumnCount() {
		return column.length;
	}
	
	//컬럼 제목을 출력하기 위해서는, 이미 메서드를 오버라이드하면 된다
	public String getColumnName(int col) {
		return column[col];
	}
	
	//해당 좌쵸의 데이터
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
}
