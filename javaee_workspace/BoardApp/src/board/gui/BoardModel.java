package board.gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import board.model.Notice;

public class BoardModel extends AbstractTableModel{
	
	String[] column = {"notice_id", "작성자", "제목", "등록일", "조회수"};
	//이차원 형태의 데이터 선언.. 
	ArrayList<Notice> list = new ArrayList<Notice>();
	
	public int getRowCount() {
		return list.size();
	}

	public int getColumnCount() {
		return column.length;
	}

	/*컬럼 제목 출력*/
	public String getColumnName(int col) {
		return column[col];
	}
	
	public Object getValueAt(int row, int col) {
		Notice notice = list.get(row);  //각 방에 있는 VO를 추출
		
		String obj = null;
		if(col == 0) {
			obj = Integer.toString(notice.getNotice_id());
		}else if(col == 1) {
			obj = notice.getAuthor();
		}else if(col == 2) {
			obj = notice.getTitle();
		}else if(col == 3) {
			obj = notice.getRegdate();
		}else if(col == 4) {
			obj = Integer.toString(notice.getHit());
		}
		return obj;
	}

}
