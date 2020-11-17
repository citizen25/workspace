package day201108;

import javax.swing.table.AbstractTableModel;

public class ProductController extends AbstractTableModel{

	String[][] data = {};

	String[] column = {"product_id", "subcategory_id", "product_name", "brand", "price", "filename"};

	ShoppingApp shoppingApp;


	
	public void getShoppingApp(ShoppingApp shoppingApp){
		shoppingApp = this.shoppingApp;
		System.out.println(shoppingApp);
	}

	public int getRowCount(){
		return data.length;
	}

	public int getColumnCount(){
		return data[0].length;
	}

	public String getColumnName(int col){
		return column[col];
	}

	public Object getValueAt(int row, int col){
		return data[row][col];
	}

}
