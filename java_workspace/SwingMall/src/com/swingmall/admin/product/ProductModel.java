package com.swingmall.admin.product;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ProductModel extends AbstractTableModel{
	//생성하지 않으멸 getRowCount() 메서드에서 NullPointerException이 발생
	ArrayList<ProductVO> record = new ArrayList<ProductVO>();  //레코드를 담게 될 리스트
	
	//컬럼 정보를 위한 ArrayList 선언
	ArrayList<String> column = new ArrayList<String>();
	
	public int getRowCount() {
		return record.size();
	}

	public int getColumnCount() {
		return column.size();
	}

	public String getColumnName(int col) {
		return column.get(col);
	}
	
	public Object getValueAt(int row, int col) {
		ProductVO vo = record.get(row);

		String obj = null;
		if(col == 0) {
			obj = Integer.toString(vo.getProduct_id());
		}else if(col == 1){
			obj = Integer.toString(vo.getSubcategory_id());
		}else if(col == 2){
			obj = vo.getProduct_name();
		}else if(col == 3){
			obj = vo.getBrand();
		}else if(col == 4){
			obj = Integer.toString(vo.getPrice());
		}else if(col == 5){
			obj = vo.getFilename();
		}else if(col == 6){
			obj = vo.getDetail();
		}
		return obj;
	}

}
