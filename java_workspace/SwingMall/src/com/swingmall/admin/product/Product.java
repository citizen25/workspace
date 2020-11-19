package com.swingmall.admin.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import com.swingmall.admin.AdminMain;
import com.swingmall.admin.Page;

public class Product extends Page{
	JPanel p_west;  //tree가 올 영역
	JPanel p_center;
	JTree tree;
	JTable table;
	JScrollPane s1, s2;
	JButton bt_regist;
	
	ArrayList<String> topList;  //최상위 카테고리 이름을 담게 될 리스트. top, down, accessary, shoes
	ArrayList<ArrayList> subList = new ArrayList<ArrayList>();  //모든 하위 카테고리
	
	ProductModel model;
	RegistForm registForm;
	
	public Product(AdminMain adminMain){
		super(adminMain);
		
		//--카테고리 가져오기--
		getTopList();  //상위 카테고리 가져오기. 멤버변수인 topList에 최상위 카테고리가 채워진다
		for(String name : topList) {
			getSubList(name);			
		}
		
		//--노드 만들기--
		//'상품목록'이라는 제목의 최상위 노드 생성하기
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Product List");
		for(int i=0; i<topList.size(); i++) {
			top.add(getCreatedNode(topList.get(i), subList.get(i)));	
		}
		
		//생성
		p_west = new JPanel();
		p_center = new JPanel();
		tree = new JTree(top);  //매개변수로 노드를 넣어야 한다
		table = new JTable();
		s1 = new JScrollPane(tree);
		s2 = new JScrollPane(table);
		bt_regist = new JButton("Regist");
		registForm = new RegistForm(this);
		
		//스타일
		bt_regist.setPreferredSize(new Dimension(150, 20));
		s1.setPreferredSize(new Dimension(200, AdminMain.HEIGHT-100));
		p_west.setBackground(Color.WHITE);
		s2.setPreferredSize(new Dimension(AdminMain.WIDTH-300, AdminMain.HEIGHT-200));
		
		//조립
		setLayout(new BorderLayout());
		
		p_west.add(s1);  //서쪽 패널에 트리 스크롤 부착
		p_center.add(s2);  //센터 패널에 테이블 스크롤 부착
		p_center.add(bt_regist);  //센터 패널에 버튼 부착
		
		add(p_west, BorderLayout.WEST);
		add(p_center);
		
		getProductList(null);  //모든 상품 가져오기
		
		//tree는 이벤트가 별도로 지원된다
		tree.addTreeSelectionListener((e)->{
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
			//System.out.println(selectedNode);
			if(selectedNode.toString().equals("Product List")) {
				getProductList(null);
			}else {				
				getProductList(selectedNode.toString());
			} 
		});
		
		bt_regist.addActionListener((e)->{
			addRemoveContent(p_center, registForm);
		});
	}
	//상위 카테고리 가져오기
	public void getTopList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM topcategory";
		
		try {
			pstmt = getAdminMain().getCon().prepareStatement(sql);  //쿼리 준비
			rs = pstmt.executeQuery();  //쿼리 수행
			//배열은 유연하지 못하므로, ArrayList에 담는다
			topList = new ArrayList();
			while(rs.next()) {
				topList.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getAdminMain().getDbManager().close(pstmt, rs);
		}
		
	}
	
	//하위 카테고리 가져오기
	public void getSubList(String name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from subcategory where topcategory_id=(";
		sql += " select topcategory_id from topcategory where name='" + name + "')";
		
		try {
			pstmt = getAdminMain().getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			ArrayList list = new ArrayList();  //상위 카테고리에 등록된 하위 카테고리
			while(rs.next()) {
				list.add(rs.getString("name"));				
			}
			//모두 담겨지면, 이차원 리스트에 추가해놓자
			subList.add(list);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getAdminMain().getDbManager().close(pstmt, rs);
		}
	}
	
	//트리 노드 생성하기
	public DefaultMutableTreeNode getCreatedNode(String parentName, ArrayList childName) {
		//부모 노드 생성하기
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentName);
		
		//넘겨받은 매개변수인 ArrayList만큼 반복하여 부모 노드에 자식 노드 부착
		for(int i=0; i<childName.size(); i++) {
			parent.add(new DefaultMutableTreeNode(childName.get(i)));
		}
		
		return parent;
	}
	
	//상품 가져오기
	public void getProductList(String name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		if(name == null) {  //매개변수 name이 안 넘어오면 모든 상품 가져오기
			sql = "select * from product";
		}else {  //name값이 넘어오면 조건 쿼리 수행
			sql = "select * from product where subcategory_id=(";
			sql += "select subcategory_id from subcategory where name='" + name + "')";
		}
		try {
			pstmt = getAdminMain().getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//메타 정보를 이용하여 ProductModel의 column ArrayList를 채우자
			ResultSetMetaData meta = rs.getMetaData();
			ArrayList<String> columnNames = new ArrayList<String>();
			
			for(int i=1; i<=meta.getColumnCount(); i++) {
				String colName = meta.getColumnName(i);  //컬럼명 추출
				columnNames.add(colName);
			}
			
			//rs의 레코드를 ProductModel의 record ArrayList에 채우자
			ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
			while(rs.next()) {
				ProductVO vo = new ProductVO();  //비어있는 vo를 생성해서 rs의 값들을 채워넣기 위해
				
				vo.setProduct_id(rs.getInt("product_id"));
				vo.setSubcategory_id(rs.getInt("subcategory_id"));
				vo.setProduct_name(rs.getString("product_name"));
				vo.setBrand(rs.getString("brand"));
				vo.setPrice(rs.getInt("price"));
				vo.setFilename(rs.getString("filename"));
				vo.setDetail(rs.getString("detail"));
				productList.add(vo);  //방금 생성하고 하나의 레코드가 채워진 vo를 ArrayList에 추가하자
			}
			
			model = new ProductModel();
			model.column = columnNames;  //컬럼 정보 대입
			model.record = productList;  //레코드 정보 대입
			table.setModel(model);  //테이블에 방금 생성한 모델 적용
			table.updateUI();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getAdminMain().getDbManager().close(pstmt, rs);
		}
	}
	
	//보여질 컨텐트와 가려질 컨텐트를 제어하는 메서드
	public void addRemoveContent(Component removeObj, Component addObj) {
		this.remove(removeObj);  //제거될 자
		this.add(addObj);  //부착될 자
		((JPanel)addObj).updateUI();
	}
	
}
