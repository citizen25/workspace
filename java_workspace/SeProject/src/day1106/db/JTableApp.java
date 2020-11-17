/*JTable �� �ڼ��� �˾ƺ���
 	JTable�� MVC�� �߱��Ѵ�
 	JTable ��ü : View
 	JTable�� ���� ������ : Model
 	TableModel : Controller
 	 */

package day1106.db;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JTableApp extends JFrame{
	JTable table;  //MVC���� view�̴�
	JScrollPane scroll;
	MyModel model;
	
	public JTableApp() {
		table = new JTable(model = new MyModel());  //JTable�� Controller�� MyModel ����
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
