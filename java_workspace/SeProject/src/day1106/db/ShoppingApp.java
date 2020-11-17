/*���θ� ��ǰ���� ���α׷� ����*/

package day1106.db;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import common.image.ImageUtil;

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
	JButton bt_collect;  //�̹��� �����ϱ�(�������� �̹����� ���� �ϵ��ũ��)
	JPanel can;  //�̹��� �̸����� �׷��� ��
	JButton bt_regist;
	CollectorFrame collectorFrame;

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
	
	Connection con;  //���� ������ �Ǹ�, �� ������ ���� �������̽�
	HashMap<String, Integer> map = new HashMap<String, Integer>();  //�÷��� �����ӿ� ��, key-value�� ������ ��ü�� �������ִ� ��ü
	HashMap<String, Integer> map2 = new HashMap<String, Integer>();
	
	JFileChooser chooser = new JFileChooser("C:/workspace/java_workspace/SeProject/res/travel2");
	Toolkit kit = Toolkit.getDefaultToolkit();
	File file;
	Image img;
	ProductController productController;
	
	public ShoppingApp() {
		//���� ���� ����
		p_west = new JPanel();
		ch_top = new Choice();
		ch_sub = new Choice();
		t_name = new JTextField();
		t_brand = new JTextField();
		t_price = new JTextField();
		bt_find = new JButton("�̹��� ã��");
		bt_collect= new JButton("���ͳ� ����");
		can = new JPanel() {
			public void paint(Graphics g) {
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
		p_west.add(bt_collect);
		p_west.add(can);
		p_west.add(bt_regist);
		
		//���� ��Ÿ�� ����
		ch_top.setPreferredSize(new Dimension(135, 30));
		ch_sub.setPreferredSize(new Dimension(135, 30));
		t_name.setPreferredSize(new Dimension(135, 30));
		t_brand.setPreferredSize(new Dimension(135, 30));
		t_price.setPreferredSize(new Dimension(135, 30));
		bt_find.setPreferredSize(new Dimension(135, 30));
		bt_collect.setPreferredSize(new Dimension(135, 30));
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
		
		ch_category.add("product_name");
		ch_category.add("brand");
		
		//��Ÿ�� ����
		//c_north.setBackground(Color.PINK);
		ch_category.setPreferredSize(new Dimension(130, 30));
		t_keyword.setPreferredSize(new Dimension(400, 30));
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
		can2 = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, can2);
			}
		};
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
		
		//������ â ������, ����Ŭ���� ���� ���� ���μ��� ����
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//�������� + ���μ��� ����
				disconnect();
				System.exit(0);
			}
		});
		
		//ch_top�� ������ ������ ����
		ch_top.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//�ؽø����κ��� key���� �̿��Ͽ�, value�� �����Ѵ�
				//System.out.println("itemStateChanged() called..");
				//System.out.println(ch_top.getSelectedItem());
				//System.out.println("������ �������� index�� "+ch_top.getSelectedItem());
				//System.out.println("�����κ��� ����� value�� "+topcategory_id);
				if(ch_top.getSelectedIndex() > 0) {  //���� ���� �������� ���ܽ��Ѿ� �Ѵ�
					int topcategory_id = map.get(ch_top.getSelectedItem());
					
					getSubList(topcategory_id);
				}
			}
		});
		
		//���� ã�� ��ư�� ������ ����
		bt_find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findImage();  //���θ��� ����� ��ǰ �̹��� ����
				preview();
			}
		});
		
		//��� ��ư�� ������ ����
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist();
				getProductList();
				table.updateUI();  //UI ����
			}
		});
		
		bt_collect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				collectorFrame = new CollectorFrame(ShoppingApp.this);
			}
		});
		
		bt_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String category = ch_category.getSelectedItem();
				String keyword = t_keyword.getText();
				
				getSearchResult(category, keyword);
				table.updateUI();  //���̺� ����
			}
		});
		
		//���̺� ���콺 ������ ����
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				int row = table.getSelectedRow();  //������ ��
				int col = table.getSelectedColumn();  //������ ��
				
				//String value = (String)table.getValueAt(row, col);
				//System.out.println(value);
				
				//������ ��ǰ�� �˸´� ī�װ� ���õǾ��ֵ���
				setCategory(row);
				setSubcategory(row);
				getDetail(row);  //�󼼺��� ���
				
				String filename = (String)table.getValueAt(row, 5);
				getTargetImage("C:/workspace/java_workspace/SeProject/res/travel2/"+filename);  //�̹��� �׸���
				
				can2.repaint();
				
			}
		});
		
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);  //db �����ÿ� �ϸ� �ȵ�
		
	}
	
	//����Ŭ ����
	public void connect() {
		//����̹� �ε�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);  //����
			if(con == null) {
				JOptionPane.showMessageDialog(this, "�������� ���߽��ϴ�");
				System.exit(0);  //���α׷� ����
			}else {
				//������ â�� ���������� �����ߴٴ� �޼���
				this.setTitle(user+"�� ������");
			}
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, "����̹��� ã�� �� �����ϴ�.");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//���� ī�װ� ��������
	public void getTopList() {
		String sql = "SELECT * FROM topcategory";
		
		//�������� �����ϴ� JDBC ��ü��? PreparedStatement
		//��� ������ ��� JDBC ��ü��? ResultSet (select�� ���� �� �� ����� ��� ��ü)
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);  //������ �غ�
			rs = pstmt.executeQuery();  //���� ����
			
			while(rs.next()) {  //Ŀ�� 1ĭ ����
				ch_top.add(rs.getString("name"));  //����ڵ��� ���Ե� ������
				ch_top2.add(rs.getString("name"));
				
				map.put(rs.getString("name"), rs.getInt("topcategory_id"));  //�ؽøʿ� key-value ������ ���� �ֱ�
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	//���� ī�װ� ��������
	public void getSubList(int topcategory_id) {
		String sql = "SELECT * FROM subcategory WHERE topcategory_id="+topcategory_id;					
		//System.out.println(sql);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);  //�������� ��ü ����. ������ �غ�
			rs = pstmt.executeQuery();
			
			//ä��� ���� ��� ����� (�ʱ�ȭ)
			ch_sub.removeAll();
			ch_sub2.removeAll();
			
			ch_sub.add("choose category");
			ch_sub2.add("choose category");
			//����ī�װ� ä���
			while(rs.next()) {
				ch_sub.add(rs.getString("name"));
				ch_sub2.add(rs.getString("name"));
				
				map2.put(rs.getString("name"), rs.getInt("subcategory_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//��ǰ �̹��� ����
	public void findImage() {
		if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			//���� ������ ���Ѵ�
			file = chooser.getSelectedFile();
			//System.out.println("����� ���� ������ ������ ���� : "+file.getAbsolutePath());
			
			getTargetImage(file.getAbsolutePath());
		}
	}
	
	//�׷��� �̹��� ���ϱ�
	public void getTargetImage(String path) {
		img = kit.getImage(path);  //������� img���� ���Ѵ�
		img = ImageUtil.getCustomSize(img, 135, 115);
	}
	
	//�̸����� ����
	public void preview() {
		//paint�� �׸� ó��
		can.repaint();
	}
	
	//��� �����ϱ�
	public void regist() {
		//����ǥ �� �������� 
		int subcategory_id=map2.get(ch_sub.getSelectedItem()); //??
		String product_name = t_name.getText();
		String brand = t_brand.getText();
		int price = Integer.parseInt(t_price.getText());
		String filename = file.getName();//Ǯ��θ��� �̹�����..
		
		System.out.println("subcategory_id: "+ subcategory_id );
		System.out.println("product_name: "+ product_name);
		System.out.println("brand: "+ brand );
		System.out.println("price: "+ price );
		System.out.println("filename: "+ filename);
		
		String sql="insert into product(product_id, subcategory_id, product_name, brand,price,filename)";
		sql+=" values(seq_product.nextval, ?,?,?,?,?)";
		
		PreparedStatement pstmt=null;
		
		try {
			pstmt = con.prepareStatement(sql);
			//���ε� ���� �����Ŀ� ���� �����ؾ� �Ѵ�!!
			pstmt.setInt(1, subcategory_id);
			pstmt.setString(2, product_name);
			pstmt.setString(3, brand);
			pstmt.setInt(4, price);
			pstmt.setString(5, filename);
			
			//�Ʒ��� �޼����� ��ȯ��? �� �������� ���� ����޴� ���ڵ� ���� ��ȯ , ����  insert ��쿣 �����̶�� ������ 1
			//update, delete�� ������ ��� 0. �����̸� 1�̻���
			int result = pstmt.executeUpdate(); //DML(insert, update, delete �� ���)
			
			if(result ==0) {
				JOptionPane.showMessageDialog(this, "��Ͻ��Ф̤�");  
			}else {
				JOptionPane.showMessageDialog(this, "��ϼ���^^");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//product ���̺��� ���ڵ� ��������
	public void getProductList() {
		String sql = "SELECT * FROM product";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//PreparedStatement ������ �μ� 2���� �Ѱ�, ���Ĺ����� Ŀ���� �����Ӱ� �̵������ϵ��� �� �� �ִ� 
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);  //���� �غ�
			rs = pstmt.executeQuery();  //select�� ���� �� ���ǥ�� rs�� ����

			//rs�� �޼��� �� .getRow()�� ���� Ŀ���� ��ġ ��, ���ڵ� ��� ����Ű�� �ִ����� �� �� �ִ�
			rs.last();  //Ŀ���� ���� ���������� ������
			int currentRow = rs.getRow();
			
			//System.out.println("���� Ŀ���� ����Ű�� ���ڵ� ��ȣ�� "+currentRow);
			//System.out.println("�������� ������ Ŀ���� rowNum�� "+currentRow);
			
			//rs�� ǥ �����͸� ProductController�� ������ data ������ �迭�� ����
			String [][] data = new String[currentRow][productController.column.length];
			
			//������ �迭�� �����͸� �����θ�, Ŀ���� �ٽ� ���󺹱ͽ��Ѿ� �Ѵ�
			rs.beforeFirst();  //ù��° ���ڵ� ��ٵ� �������� �ǵ��� (��ġ �ʱ�ȭ)
			
			int index=0;
			while(rs.next()) {
				String[] record = new String[productController.column.length];
				
				record[0] = rs.getString("product_id");
				record[1] = rs.getString("subcategory_id");
				record[2] = rs.getString("product_name");
				record[3] = rs.getString("brand");
				record[4] = rs.getString("price");
				record[5] = rs.getString("filename");
				
				//ä���� 1���� �迭�� data 2���� �迭�� ������� ����
				data[index++] = record;
			}
			
			//�ϼ��� 2���� �迭�� productController�� ������ data �迭 �ּҷ� ���Խ�Ű��
			productController.data = data;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//�˻� ��� ����ϱ�
	public void getSearchResult(String category, String keyword) {
		String sql = "SELECT * FROM product WHERE "+category+" LIKE '%"+keyword+"%'";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//PreparedStatement ������ �μ� 2���� �Ѱ�, ���Ĺ����� Ŀ���� �����Ӱ� �̵������ϵ��� �� �� �ִ� 
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);  //���� �غ�
			rs = pstmt.executeQuery();  //select�� ���� �� ���ǥ�� rs�� ����

			//rs�� �޼��� �� .getRow()�� ���� Ŀ���� ��ġ ��, ���ڵ� ��� ����Ű�� �ִ����� �� �� �ִ�
			rs.last();  //Ŀ���� ���� ���������� ������
			int currentRow = rs.getRow();
			
			//System.out.println("���� Ŀ���� ����Ű�� ���ڵ� ��ȣ�� "+currentRow);
			System.out.println("�������� ������ Ŀ���� rowNum�� "+currentRow);
			
			//rs�� ǥ �����͸� ProductController�� ������ data ������ �迭�� ����
			String [][] data = new String[currentRow][productController.column.length];
			
			//������ �迭�� �����͸� �����θ�, Ŀ���� �ٽ� ���󺹱ͽ��Ѿ� �Ѵ�
			rs.beforeFirst();  //ù��° ���ڵ� ��ٵ� �������� �ǵ��� (��ġ �ʱ�ȭ)
			
			int index=0;
			while(rs.next()) {
				String[] record = new String[productController.column.length];
				
				record[0] = rs.getString("product_id");
				record[1] = rs.getString("subcategory_id");
				record[2] = rs.getString("product_name");
				record[3] = rs.getString("brand");
				record[4] = rs.getString("price");
				record[5] = rs.getString("filename");
				
				//ä���� 1���� �迭�� data 2���� �迭�� ������� ����
				data[index++] = record;
			}
			
			//�ϼ��� 2���� �迭�� productController�� ������ data �迭 �ּҷ� ���Խ�Ű��
			productController.data = data;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//��ǰ �󼼺���
	public void getDetail(int row) {
		t_name2.setText((String)table.getValueAt(row, 2));  //��ǰ��
		t_brand2.setText((String)table.getValueAt(row, 3));  //�귣��
		t_price2.setText((String)table.getValueAt(row, 4));  //��ǰ����
		
	}
	
	public void setCategory(int row) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String subcategory_id = (String)table.getValueAt(row, 1);
		String sql = "SELECT * FROM topcategory WHERE topcategory_id=(";
		sql += "SELECT topcategory_id FROM subcategory WHERE subcategory_id="+subcategory_id;
		sql += ")";
		//System.out.println(sql);
		
		try {
			pstmt = con.prepareStatement(sql);  //������ �غ�
			rs = pstmt.executeQuery();  //������ ����
			if(rs.next()) {
				//select()�޼���� ���õ� �������� ������ �� �ִ�
				ch_top2.select(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
	}
	
	public void setSubcategory(int row) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String subcategory_id = (String)table.getValueAt(row, 1);
		
		String sql = "SELECT * FROM subcategory WHERE subcategory_id = "+subcategory_id;

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ch_sub2.select(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
	}
	
	
	//���� ����
	public void disconnect() {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new ShoppingApp();
	}
}
