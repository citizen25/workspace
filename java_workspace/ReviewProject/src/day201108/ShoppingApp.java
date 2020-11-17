package day201108;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ShoppingApp extends JFrame{
	//��ü ���̾ƿ�
	JPanel p_west;  //��ü �� ����
	JPanel p_center;  //��ü �� ���
	JPanel p_east;  //��ü �� ����
	
	//����� ����(����)
	Choice ch_top;
	Choice ch_sub;
	JTextField t_name;
	JTextField t_brand;
	JTextField t_price;
	JButton bt_find;  //�̹��� ã�ƺ��� ��ư
	JPanel can;  //�̹��� �̸����� �׷��� ��
	JButton bt_regist;

	//���� ���� - �˻� ����
	JPanel c_north;  //��� �� ����
	JPanel c_center;  //��� �� ����
	Choice ch_category;  //�˻� ī�װ�
	JTextField t_keyword;  //�˻���
	JButton bt_search;  //�˻� ��ư
	JTable table;
	JScrollPane scroll;
	
	//���� ���� - �󼼺���
	Choice ch_top2;
	Choice ch_sub2;
	JTextField t_name2;
	JTextField t_brand2;
	JTextField t_price2;
	JButton bt_find2;  //�̹��� ã�ƺ��� ��ư
	JPanel can2;  //�̹��� �̸����� �׷��� ��
	JButton bt_edit;  //���� ��ư
	JButton bt_del;  //���� ��ư

	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "user1104";
	String password = "user1104";

	Connection con;
	HashMap<String, Integer> map = new HashMap<String, Integer>();
	HashMap<String, Integer> map2 = new HashMap<String, Integer>();

	JFileChooser chooser = new JFileChooser("C:/workspace");
	Toolkit kit = Toolkit.getDefaultToolkit();
	File file;
	Image img;
	ProductController productController;

	public ShoppingApp(){
		//���� ���� ����
		p_west = new JPanel();
		ch_top = new Choice();
		ch_sub = new Choice();
		t_name = new JTextField();
		t_brand = new JTextField();
		t_price = new JTextField();
		bt_find = new JButton("�̹��� ã��");
		can = new JPanel(){
			public void paint(Graphics g){
				g.drawImage(img, 0, 0, can);
			}
		};
		bt_regist = new JButton("���");
		
		ch_top.add("choose category");
		
		//���� ����
		p_west.add(ch_top);
		p_west.add(ch_sub);
		p_west.add(t_name);
		p_west.add(t_brand);
		p_west.add(t_price);
		p_west.add(bt_find);
		p_west.add(can);
		p_west.add(bt_regist);
		
		//���� ��Ÿ�� ����
		ch_top.setPreferredSize(new Dimension(135, 30));
		ch_sub.setPreferredSize(new Dimension(135, 30));
		t_name.setPreferredSize(new Dimension(135, 30));
		t_brand.setPreferredSize(new Dimension(135, 30));
		t_price.setPreferredSize(new Dimension(135, 30));
		bt_find.setPreferredSize(new Dimension(135, 30));
		can.setPreferredSize(new Dimension(135, 115));
		bt_regist.setPreferredSize(new Dimension(135, 30));
		
		p_west.setPreferredSize(new Dimension(150, 600));
		//p_west.setBackground(Color.YELLOW);
		
		//�����ӿ� ���� ���� ���̱�
		add(p_west, BorderLayout.WEST);
		
		
		//��� ���� ����
		p_center = new JPanel();
		c_north = new JPanel();
		c_center = new JPanel();
		ch_category = new Choice();
		t_keyword = new JTextField();
		bt_search = new JButton("�˻�");
		table = new JTable(productController = new ProductController());
		scroll = new JScrollPane(table);
		
		//��Ÿ�� ����
		//c_north.setBackground(Color.PINK);
		ch_category.setPreferredSize(new Dimension(130, 30));
		t_keyword.setPreferredSize(new Dimension(500, 30));
		bt_search.setPreferredSize(new Dimension(120, 30));
		
		//��� - �˻� ���� ����
		c_north.add(ch_category);
		c_north.add(t_keyword);
		c_north.add(bt_search);
		
		//��� - ���̺� ���� ����
		c_center.setLayout(new BorderLayout());
		c_center.add(scroll);
		
		//��� ������ ��ü �гο� �� �г� ����
		p_center.setLayout(new BorderLayout());
		p_center.add(c_north, BorderLayout.NORTH);
		p_center.add(c_center, BorderLayout.CENTER);
		
		//��� ��ü �г��� �����ӿ� ����
		add(p_center);
		
		
		//���� ���� ����
		p_east = new JPanel();
		ch_top2 = new Choice();
		ch_sub2 = new Choice();
		t_name2 = new JTextField();
		t_brand2 = new JTextField();
		t_price2 = new JTextField();
		bt_find2 = new JButton("�̹��� ã��");
		can2 = new JPanel();
		bt_edit = new JButton("����");
		bt_del = new JButton("����");
		
		//���� ����
		p_east.add(ch_top2);
		p_east.add(ch_sub2);
		p_east.add(t_name2);
		p_east.add(t_brand2);
		p_east.add(t_price2);
		p_east.add(bt_find2);
		p_east.add(can2);
		p_east.add(bt_edit);
		p_east.add(bt_del);
		
		//���� ��Ÿ�� ����
		ch_top2.setPreferredSize(new Dimension(135, 30));
		ch_sub2.setPreferredSize(new Dimension(135, 30));
		t_name2.setPreferredSize(new Dimension(135, 30));
		t_brand2.setPreferredSize(new Dimension(135, 30));
		t_price2.setPreferredSize(new Dimension(135, 30));
		bt_find2.setPreferredSize(new Dimension(135, 30));
		can2.setPreferredSize(new Dimension(135, 115));
		bt_edit.setPreferredSize(new Dimension(135, 30));
		bt_del.setPreferredSize(new Dimension(135, 30));
		
		p_east.setPreferredSize(new Dimension(150, 600));
		//p_east.setBackground(Color.YELLOW);
		
		//�����ӿ� ���� ���� ���̱�
		add(p_east, BorderLayout.EAST);

		connect();
		getTopList();
		getProductList();

		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				disconnect();
				System.exit(0);
			}
		});

		ch_top.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if(ch_top.getSelectedIndex() > 0){
					int topcategory_id = map.get(ch_top.getSelectedItem());

					getSubList(topcategory_id);
				}
			}
		});

		bt_find.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				findImage();
				can.repaint();
			}
		});

		bt_regist.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				regist();
				getProductList();
			}
		});

		setSize(1000, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void connect(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
			if(con == null){
				JOptionPane.showMessageDialog(this, "�������� ���߽��ϴ�");
				System.exit(0);
			}else{
				this.setTitle(user+"�� ������");
			}
		}catch(ClassNotFoundException e){
			JOptionPane.showMessageDialog(this, "����̹��� ã�� �� �����ϴ�.");
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}

	}
	

	public void getTopList(){
		String sql = "SELECT * FROM topcategory";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()){
				ch_top.add(rs.getString("name"));
				map.put(rs.getString("name"), rs.getInt("topcategory_id"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}

	public void getSubList(int topcategory_id){
		String sql = "SELECT * FROM subcategory WHERE topcategory_id=" + topcategory_id;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			ch_sub.removeAll();

			ch_sub.add("choose category");
			while(rs.next()){
				ch_sub.add(rs.getString("name"));
				map2.put(rs.getString("name"), rs.getInt("subcategory_id"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}

	public void findImage(){
		if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
			file = chooser.getSelectedFile();
			img = kit.getImage(file.getAbsolutePath());
			img = img.getScaledInstance(135, 115, Image.SCALE_SMOOTH);
		}
	}

	public void preview(){
		can.repaint();
	}

	public void regist(){
		int subcategory_id = map2.get(ch_sub.getSelectedItem());
		String product_name = t_name.getText();
		String brand = t_brand.getText();
		int price = Integer.parseInt(t_price.getText());
		String filename = file.getName();

		String sql = "INSERT INTO product(product_id, subcategory_id, product_name, brand, price, filename)";
		sql += " VALUES(seq_product.nextval, ?, ?, ?, ?, ?)";

		PreparedStatement pstmt = null;

		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, subcategory_id);
			pstmt.setString(2, product_name);
			pstmt.setString(3, brand);
			pstmt.setInt(4, price);
			pstmt.setString(5, filename);

			int result = pstmt.executeUpdate();

			if(result == 0){
				JOptionPane.showMessageDialog(this, "regist failed");
			}else{
				JOptionPane.showMessageDialog(this, "regist success");
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}

	public void getProductList(){

		String sql = "SELECT * FROM product";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();

			rs.last();
			int count = rs.getRow();
			rs.beforeFirst();

			//System.out.println(count);

			String[][] data = new String[count][6];

			rs.next();

			String[] record = new String[6];

			record[0] = rs.getString("product_id");
			record[1] = rs.getString("subcategory_id");
			record[2] = rs.getString("product_id");
			record[3] = rs.getString("brand");
			record[4] = rs.getString("price");
			record[5] = rs.getString("filename");

			for(int i=0; i<count; i++){
				data[i] = record;
			}

			productController.getShoppingApp(this);

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}

	public void disconnect(){
		if(con != null){
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args){
		new ShoppingApp();
	}

}
