/*쇼핑몰 상품관리 프로그램 제작*/

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

	//전체 레이아웃
	JPanel p_west;  //전체 중 서쪽
	JPanel p_center;  //전체 중 가운데
	JPanel p_east;  //전체 중 동쪽
	
	//등록폼 관련(왼쪽)
	Choice ch_top;
	Choice ch_sub;
	JTextField t_name;
	JTextField t_brand;
	JTextField t_price;
	JButton bt_find;  //이미지 찾아보기 버튼
	JButton bt_collect;  //이미지 수집하기(원격지의 이미지를 나의 하드디스크로)
	JPanel can;  //이미지 미리보기 그려질 곳
	JButton bt_regist;
	CollectorFrame collectorFrame;

	//센터 영역 - 검색 관련
	JPanel c_north;  //가운데 중 북쪽
	JPanel c_center;  //가운데 중 센터
	Choice ch_category;  //검색 카테고리
	JTextField t_keyword;  //검색어
	JButton bt_search;  //검색 버튼
	JTable table;
	JScrollPane scroll;
	
	//동쪽 영역 - 상세보기
	Choice ch_top2;
	Choice ch_sub2;
	JTextField t_name2;
	JTextField t_brand2;
	JTextField t_price2;
	JButton bt_find2;  //이미지 찾아보기 버튼
	JPanel can2;  //이미지 미리보기 그려질 곳
	JButton bt_edit;  //수정 버튼
	JButton bt_del;  //삭제 버튼
	
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "user1104";
	String password = "user1104";
	
	Connection con;  //접속 성공이 되면, 그 정보를 가질 인터페이스
	HashMap<String, Integer> map = new HashMap<String, Integer>();  //컬렉션 프레임웍 중, key-value의 쌍으로 객체를 관리해주는 객체
	HashMap<String, Integer> map2 = new HashMap<String, Integer>();
	
	JFileChooser chooser = new JFileChooser("C:/workspace/java_workspace/SeProject/res/travel2");
	Toolkit kit = Toolkit.getDefaultToolkit();
	File file;
	Image img;
	ProductController productController;
	
	public ShoppingApp() {
		//서쪽 영역 생성
		p_west = new JPanel();
		ch_top = new Choice();
		ch_sub = new Choice();
		t_name = new JTextField();
		t_brand = new JTextField();
		t_price = new JTextField();
		bt_find = new JButton("이미지 찾기");
		bt_collect= new JButton("인터넷 수집");
		can = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, can);
			}
		};
		bt_regist = new JButton("등록");
		
		ch_top.add("choose category");
		
		//서쪽 조립
		p_west.add(ch_top);
		p_west.add(ch_sub);
		p_west.add(t_name);
		p_west.add(t_brand);
		p_west.add(t_price);
		p_west.add(bt_find);
		p_west.add(bt_collect);
		p_west.add(can);
		p_west.add(bt_regist);
		
		//서쪽 스타일 적용
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
		
		//프레임에 서쪽 영역 붙이기
		add(p_west, BorderLayout.WEST);
		
		
		//가운데 영역 생성
		p_center = new JPanel();
		c_north = new JPanel();
		c_center = new JPanel();
		ch_category = new Choice();
		t_keyword = new JTextField();
		bt_search = new JButton("검색");
		table = new JTable(productController = new ProductController());
		scroll = new JScrollPane(table);
		
		ch_category.add("product_name");
		ch_category.add("brand");
		
		//스타일 적용
		//c_north.setBackground(Color.PINK);
		ch_category.setPreferredSize(new Dimension(130, 30));
		t_keyword.setPreferredSize(new Dimension(400, 30));
		bt_search.setPreferredSize(new Dimension(120, 30));
		
		//가운데 - 검색 영역 조립
		c_north.add(ch_category);
		c_north.add(t_keyword);
		c_north.add(bt_search);
		
		//가운데 - 테이블 영역 조립
		c_center.setLayout(new BorderLayout());
		c_center.add(scroll);
		
		//가운데 영역의 전체 패널에 두 패널 부착
		p_center.setLayout(new BorderLayout());
		p_center.add(c_north, BorderLayout.NORTH);
		p_center.add(c_center, BorderLayout.CENTER);
		
		//가운데 전체 패널을 프레임에 부착
		add(p_center);
		
		
		//동쪽 영역 생성
		p_east = new JPanel();
		ch_top2 = new Choice();
		ch_sub2 = new Choice();
		t_name2 = new JTextField();
		t_brand2 = new JTextField();
		t_price2 = new JTextField();
		bt_find2 = new JButton("이미지 찾기");
		can2 = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, can2);
			}
		};
		bt_edit = new JButton("수정");
		bt_del = new JButton("삭제");
		
		//동쪽 조립
		p_east.add(ch_top2);
		p_east.add(ch_sub2);
		p_east.add(t_name2);
		p_east.add(t_brand2);
		p_east.add(t_price2);
		p_east.add(bt_find2);
		p_east.add(can2);
		p_east.add(bt_edit);
		p_east.add(bt_del);
		
		//동쪽 스타일 적용
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
		
		//프레임에 동쪽 영역 붙이기
		add(p_east, BorderLayout.EAST);
		
		connect();
		getTopList();
		getProductList();
		
		//윈도우 창 닫으면, 오라클과의 접속 끊고 프로세스 종료
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//접속해제 + 프로세스 종료
				disconnect();
				System.exit(0);
			}
		});
		
		//ch_top에 아이템 리스너 연결
		ch_top.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//해시맵으로부터 key값을 이용하여, value를 추출한다
				//System.out.println("itemStateChanged() called..");
				//System.out.println(ch_top.getSelectedItem());
				//System.out.println("선택한 아이템의 index는 "+ch_top.getSelectedItem());
				//System.out.println("맵으로부터 추출된 value는 "+topcategory_id);
				if(ch_top.getSelectedIndex() > 0) {  //제일 위의 아이템은 제외시켜야 한다
					int topcategory_id = map.get(ch_top.getSelectedItem());
					
					getSubList(topcategory_id);
				}
			}
		});
		
		//파일 찾기 버튼과 리스너 연결
		bt_find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findImage();  //쇼핑몰에 사용할 상품 이미지 선택
				preview();
			}
		});
		
		//등록 버튼과 리스너 연결
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist();
				getProductList();
				table.updateUI();  //UI 갱신
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
				table.updateUI();  //테이블 갱신
			}
		});
		
		//테이블에 마우스 리스너 연결
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				int row = table.getSelectedRow();  //선택한 행
				int col = table.getSelectedColumn();  //선택한 열
				
				//String value = (String)table.getValueAt(row, col);
				//System.out.println(value);
				
				//선택한 제품의 알맞는 카테고리 선택되어있도록
				setCategory(row);
				setSubcategory(row);
				getDetail(row);  //상세보기 출력
				
				String filename = (String)table.getValueAt(row, 5);
				getTargetImage("C:/workspace/java_workspace/SeProject/res/travel2/"+filename);  //이미지 그리기
				
				can2.repaint();
				
			}
		});
		
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);  //db 연동시엔 하면 안됨
		
	}
	
	//오라클 접속
	public void connect() {
		//드라이버 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);  //접속
			if(con == null) {
				JOptionPane.showMessageDialog(this, "접속하지 못했습니다");
				System.exit(0);  //프로그램 종료
			}else {
				//윈도우 창에 유저명으로 접속했다는 메세지
				this.setTitle(user+"로 접속중");
			}
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, "드라이버를 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//상위 카테고리 가져오기
	public void getTopList() {
		String sql = "SELECT * FROM topcategory";
		
		//쿼리문을 수행하는 JDBC 객체는? PreparedStatement
		//결과 집합을 담는 JDBC 객체는? ResultSet (select문 수행 후 그 결과를 담는 객체)
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);  //쿼리문 준비
			rs = pstmt.executeQuery();  //쿼리 실행
			
			while(rs.next()) {  //커서 1칸 전진
				ch_top.add(rs.getString("name"));  //사용자들이 보게될 아이템
				ch_top2.add(rs.getString("name"));
				
				map.put(rs.getString("name"), rs.getInt("topcategory_id"));  //해시맵에 key-value 쌍으로 정보 넣기
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

	//하위 카테고리 가져오기
	public void getSubList(int topcategory_id) {
		String sql = "SELECT * FROM subcategory WHERE topcategory_id="+topcategory_id;					
		//System.out.println(sql);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);  //쿼리수행 객체 생성. 쿼리문 준비
			rs = pstmt.executeQuery();
			
			//채우기 전에 모두 지우기 (초기화)
			ch_sub.removeAll();
			ch_sub2.removeAll();
			
			ch_sub.add("choose category");
			ch_sub2.add("choose category");
			//서브카테고리 채우기
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
	
	//상품 이미지 선택
	public void findImage() {
		if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			//파일 정보를 구한다
			file = chooser.getSelectedFile();
			//System.out.println("당신이 지금 선택한 파일의 정보 : "+file.getAbsolutePath());
			
			getTargetImage(file.getAbsolutePath());
		}
	}
	
	//그려질 이미지 구하기
	public void getTargetImage(String path) {
		img = kit.getImage(path);  //멤버변수 img값을 구한다
		img = ImageUtil.getCustomSize(img, 135, 115);
	}
	
	//미리보기 구현
	public void preview() {
		//paint로 그림 처리
		can.repaint();
	}
	
	//등록 구현하기
	public void regist() {
		//물음표 값 결정짓기 
		int subcategory_id=map2.get(ch_sub.getSelectedItem()); //??
		String product_name = t_name.getText();
		String brand = t_brand.getText();
		int price = Integer.parseInt(t_price.getText());
		String filename = file.getName();//풀경로말고 이미지명만..
		
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
			//바인드 변수 지정후에 쿼리 수행해야 한다!!
			pstmt.setInt(1, subcategory_id);
			pstmt.setString(2, product_name);
			pstmt.setString(3, brand);
			pstmt.setInt(4, price);
			pstmt.setString(5, filename);
			
			//아래의 메서드의 반환값? 이 쿼리문에 의해 영향받는 레코드 수를 반환 , 따라서  insert 경우엔 성공이라면 언제나 1
			//update, delete는 실패일 경우 0. 성공이면 1이상임
			int result = pstmt.executeUpdate(); //DML(insert, update, delete 의 경우)
			
			if(result ==0) {
				JOptionPane.showMessageDialog(this, "등록실패ㅜㅜ");  
			}else {
				JOptionPane.showMessageDialog(this, "등록성공^^");
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
	
	//product 테이블의 레코드 가져오기
	public void getProductList() {
		String sql = "SELECT * FROM product";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//PreparedStatement 생성시 인수 2개를 넘겨, 전후방으로 커서를 자유롭게 이동가능하도록 할 수 있다 
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);  //쿼리 준비
			rs = pstmt.executeQuery();  //select문 수행 후 결과표를 rs에 대입

			//rs의 메서드 중 .getRow()는 현재 커서의 위치 즉, 레코드 어디를 가리키고 있는지를 알 수 있다
			rs.last();  //커서를 제일 마지막으로 보내기
			int currentRow = rs.getRow();
			
			//System.out.println("현재 커서가 가리키는 레코드 번호는 "+currentRow);
			//System.out.println("마지막에 도달한 커서의 rowNum은 "+currentRow);
			
			//rs의 표 데이터를 ProductController가 보유한 data 이차원 배열에 대입
			String [][] data = new String[currentRow][productController.column.length];
			
			//이차원 배열에 데이터를 담으로면, 커서를 다시 원상복귀시켜야 한다
			rs.beforeFirst();  //첫번째 레코드 모다도 이전으로 되돌림 (위치 초기화)
			
			int index=0;
			while(rs.next()) {
				String[] record = new String[productController.column.length];
				
				record[0] = rs.getString("product_id");
				record[1] = rs.getString("subcategory_id");
				record[2] = rs.getString("product_name");
				record[3] = rs.getString("brand");
				record[4] = rs.getString("price");
				record[5] = rs.getString("filename");
				
				//채워진 1차원 배열을 data 2차원 배열에 순서대로 담자
				data[index++] = record;
			}
			
			//완성된 2차원 배열을 productController가 보유한 data 배열 주소로 대입시키자
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
	
	//검색 결과 출력하기
	public void getSearchResult(String category, String keyword) {
		String sql = "SELECT * FROM product WHERE "+category+" LIKE '%"+keyword+"%'";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//PreparedStatement 생성시 인수 2개를 넘겨, 전후방으로 커서를 자유롭게 이동가능하도록 할 수 있다 
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);  //쿼리 준비
			rs = pstmt.executeQuery();  //select문 수행 후 결과표를 rs에 대입

			//rs의 메서드 중 .getRow()는 현재 커서의 위치 즉, 레코드 어디를 가리키고 있는지를 알 수 있다
			rs.last();  //커서를 제일 마지막으로 보내기
			int currentRow = rs.getRow();
			
			//System.out.println("현재 커서가 가리키는 레코드 번호는 "+currentRow);
			System.out.println("마지막에 도달한 커서의 rowNum은 "+currentRow);
			
			//rs의 표 데이터를 ProductController가 보유한 data 이차원 배열에 대입
			String [][] data = new String[currentRow][productController.column.length];
			
			//이차원 배열에 데이터를 담으로면, 커서를 다시 원상복귀시켜야 한다
			rs.beforeFirst();  //첫번째 레코드 모다도 이전으로 되돌림 (위치 초기화)
			
			int index=0;
			while(rs.next()) {
				String[] record = new String[productController.column.length];
				
				record[0] = rs.getString("product_id");
				record[1] = rs.getString("subcategory_id");
				record[2] = rs.getString("product_name");
				record[3] = rs.getString("brand");
				record[4] = rs.getString("price");
				record[5] = rs.getString("filename");
				
				//채워진 1차원 배열을 data 2차원 배열에 순서대로 담자
				data[index++] = record;
			}
			
			//완성된 2차원 배열을 productController가 보유한 data 배열 주소로 대입시키자
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
	
	//제품 상세보기
	public void getDetail(int row) {
		t_name2.setText((String)table.getValueAt(row, 2));  //상품명
		t_brand2.setText((String)table.getValueAt(row, 3));  //브랜드
		t_price2.setText((String)table.getValueAt(row, 4));  //상품가격
		
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
			pstmt = con.prepareStatement(sql);  //쿼리문 준비
			rs = pstmt.executeQuery();  //쿼리문 실행
			if(rs.next()) {
				//select()메서드는 선택될 아이템을 지정할 수 있다
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
	
	
	//접속 해제
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
