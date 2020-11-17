/*JTable 더 자세히 알아보기
 	JTable은 MVC를 추구한다
 	JTable 자체 : View
 	JTable에 들어가는 데이터 : Model
 	TableModel : Controller
 	 */

package day1106.db;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JTableApp extends JFrame{
	JTable table;  //MVC에서 view이다
	JScrollPane scroll;
	MyModel model;
	
	public JTableApp() {
		table = new JTable(model = new MyModel());  //JTable과 Controller인 MyModel 연결
		scroll = new JScrollPane(table);
		add(scroll);
		
		setSize(400, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new JTableApp();
	}
	
}
