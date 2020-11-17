/*MVC패턴이 적용된 JTable에서 사용된ㄴ TableModel은 명칭으로는 마치 모델인것 처럼 보이지만,
	그 역할로 본다면 Controller이다.
	
	JTable(view)	------- Controller(TableModel) ------- 	보여질 데이터(Model)
	
	*/

package day1106.db;

import javax.swing.table.AbstractTableModel;

public class MyModel extends AbstractTableModel{

	//데이터 준비
	String[][] flower = {
			{"장미", "50000", "Red", "Korea"},
			{"튤립", "70000", "Purple", "France"},
			{"안개꽃", "35000", "White", "Korea"}
	};
	String[][] car = {
			{"BMW", "7000", "White"},
			{"Benz", "95000", "silver"},
			{"Audi", "8000", "black"}
	};

	//행의 갯수를 반환하는 메서드
	public int getRowCount() {
		return car.length;
	}
	
	//열의 갯수를 반환하는 메서드
	public int getColumnCount() {
		return car[0].length;
	}

	//지정한 좌표의 값을 반환
	public Object getValueAt(int row, int col) {
		//System.out.println("row:"+row+", col:"+col);
		return car[row][col];
	}

}
