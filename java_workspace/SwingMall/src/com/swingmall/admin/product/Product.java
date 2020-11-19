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
	JPanel p_west;  //tree�� �� ����
	JPanel p_center;
	JTree tree;
	JTable table;
	JScrollPane s1, s2;
	JButton bt_regist;
	
	ArrayList<String> topList;  //�ֻ��� ī�װ� �̸��� ��� �� ����Ʈ. top, down, accessary, shoes
	ArrayList<ArrayList> subList = new ArrayList<ArrayList>();  //��� ���� ī�װ�
	
	ProductModel model;
	RegistForm registForm;
	
	public Product(AdminMain adminMain){
		super(adminMain);
		
		//--ī�װ� ��������--
		getTopList();  //���� ī�װ� ��������. ��������� topList�� �ֻ��� ī�װ��� ä������
		for(String name : topList) {
			getSubList(name);			
		}
		
		//--��� �����--
		//'��ǰ���'�̶�� ������ �ֻ��� ��� �����ϱ�
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Product List");
		for(int i=0; i<topList.size(); i++) {
			top.add(getCreatedNode(topList.get(i), subList.get(i)));	
		}
		
		//����
		p_west = new JPanel();
		p_center = new JPanel();
		tree = new JTree(top);  //�Ű������� ��带 �־�� �Ѵ�
		table = new JTable();
		s1 = new JScrollPane(tree);
		s2 = new JScrollPane(table);
		bt_regist = new JButton("Regist");
		registForm = new RegistForm(this);
		
		//��Ÿ��
		bt_regist.setPreferredSize(new Dimension(150, 20));
		s1.setPreferredSize(new Dimension(200, AdminMain.HEIGHT-100));
		p_west.setBackground(Color.WHITE);
		s2.setPreferredSize(new Dimension(AdminMain.WIDTH-300, AdminMain.HEIGHT-200));
		
		//����
		setLayout(new BorderLayout());
		
		p_west.add(s1);  //���� �гο� Ʈ�� ��ũ�� ����
		p_center.add(s2);  //���� �гο� ���̺� ��ũ�� ����
		p_center.add(bt_regist);  //���� �гο� ��ư ����
		
		add(p_west, BorderLayout.WEST);
		add(p_center);
		
		getProductList(null);  //��� ��ǰ ��������
		
		//tree�� �̺�Ʈ�� ������ �����ȴ�
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
	//���� ī�װ� ��������
	public void getTopList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM topcategory";
		
		try {
			pstmt = getAdminMain().getCon().prepareStatement(sql);  //���� �غ�
			rs = pstmt.executeQuery();  //���� ����
			//�迭�� �������� ���ϹǷ�, ArrayList�� ��´�
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
	
	//���� ī�װ� ��������
	public void getSubList(String name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from subcategory where topcategory_id=(";
		sql += " select topcategory_id from topcategory where name='" + name + "')";
		
		try {
			pstmt = getAdminMain().getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			ArrayList list = new ArrayList();  //���� ī�װ��� ��ϵ� ���� ī�װ�
			while(rs.next()) {
				list.add(rs.getString("name"));				
			}
			//��� �������, ������ ����Ʈ�� �߰��س���
			subList.add(list);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getAdminMain().getDbManager().close(pstmt, rs);
		}
	}
	
	//Ʈ�� ��� �����ϱ�
	public DefaultMutableTreeNode getCreatedNode(String parentName, ArrayList childName) {
		//�θ� ��� �����ϱ�
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentName);
		
		//�Ѱܹ��� �Ű������� ArrayList��ŭ �ݺ��Ͽ� �θ� ��忡 �ڽ� ��� ����
		for(int i=0; i<childName.size(); i++) {
			parent.add(new DefaultMutableTreeNode(childName.get(i)));
		}
		
		return parent;
	}
	
	//��ǰ ��������
	public void getProductList(String name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		if(name == null) {  //�Ű����� name�� �� �Ѿ���� ��� ��ǰ ��������
			sql = "select * from product";
		}else {  //name���� �Ѿ���� ���� ���� ����
			sql = "select * from product where subcategory_id=(";
			sql += "select subcategory_id from subcategory where name='" + name + "')";
		}
		try {
			pstmt = getAdminMain().getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//��Ÿ ������ �̿��Ͽ� ProductModel�� column ArrayList�� ä����
			ResultSetMetaData meta = rs.getMetaData();
			ArrayList<String> columnNames = new ArrayList<String>();
			
			for(int i=1; i<=meta.getColumnCount(); i++) {
				String colName = meta.getColumnName(i);  //�÷��� ����
				columnNames.add(colName);
			}
			
			//rs�� ���ڵ带 ProductModel�� record ArrayList�� ä����
			ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
			while(rs.next()) {
				ProductVO vo = new ProductVO();  //����ִ� vo�� �����ؼ� rs�� ������ ä���ֱ� ����
				
				vo.setProduct_id(rs.getInt("product_id"));
				vo.setSubcategory_id(rs.getInt("subcategory_id"));
				vo.setProduct_name(rs.getString("product_name"));
				vo.setBrand(rs.getString("brand"));
				vo.setPrice(rs.getInt("price"));
				vo.setFilename(rs.getString("filename"));
				vo.setDetail(rs.getString("detail"));
				productList.add(vo);  //��� �����ϰ� �ϳ��� ���ڵ尡 ä���� vo�� ArrayList�� �߰�����
			}
			
			model = new ProductModel();
			model.column = columnNames;  //�÷� ���� ����
			model.record = productList;  //���ڵ� ���� ����
			table.setModel(model);  //���̺� ��� ������ �� ����
			table.updateUI();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getAdminMain().getDbManager().close(pstmt, rs);
		}
	}
	
	//������ ����Ʈ�� ������ ����Ʈ�� �����ϴ� �޼���
	public void addRemoveContent(Component removeObj, Component addObj) {
		this.remove(removeObj);  //���ŵ� ��
		this.add(addObj);  //������ ��
		((JPanel)addObj).updateUI();
	}
	
}
