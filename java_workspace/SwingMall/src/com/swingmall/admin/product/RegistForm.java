//상품등록 폼
package com.swingmall.admin.product;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.swingmall.admin.AdminMain;

public class RegistForm extends JPanel{
	Product product;  //상품 페이지
	JPanel p_container;  //그리드 적용 예정(7,2)
	
	JLabel[] la_title = new JLabel[7];
	String[] title = {"topcategory", "subcategory", "product_name", "brand", "price", "filename", "detail"};
	
	Choice ch_top;  //최상위 카테고리
	Choice ch_sub;  //하위 카테고리
	JTextField t_product_name;  //상품명
	JTextField t_brand;  //브랜드
	JTextField t_price;  //가격
	JTextField t_filename;  //이미지
	JTextArea t_detail;  //상세설명
	JScrollPane s1;  //상세 설명에 부착할 스크롤
	JButton bt_regist;
	JButton bt_list;
	
	public RegistForm(Product product) {
		this.product = product;
		
		//생성
		p_container = new JPanel();
		for(int i=0; i<title.length; i++) {
			la_title[i] = new JLabel(title[i]); 			
		}
		ch_top = new Choice();
		ch_sub = new Choice();
		t_product_name = new JTextField();
		t_brand = new JTextField();
		t_price = new JTextField();
		t_filename = new JTextField();
		t_detail = new JTextArea();
		s1 = new JScrollPane(t_detail);
		bt_regist = new JButton("regist");
		bt_list = new JButton("list");
		
		//최상위 카테고리 채우기(DB연동 X, 기존 데이터를 재사용하자)
		for(String name : product.topList) {
			ch_top.add(name);
		}
		
		//스타일
		Dimension d = new Dimension(320,25);
		
		p_container.setBackground(Color.WHITE);
		p_container.setPreferredSize(new Dimension(AdminMain.WIDTH-500, AdminMain.HEIGHT-400));
		for(int i=0;i<title.length;i++) {
			la_title[i].setPreferredSize(d);
		}
		ch_top.setPreferredSize(d);
		ch_sub.setPreferredSize(d);
		t_product_name.setPreferredSize(d);
		t_brand.setPreferredSize(d);
		t_price.setPreferredSize(d);
		t_filename.setPreferredSize(d);
		t_detail.setPreferredSize(new Dimension(320, 300));
		bt_regist.setPreferredSize(new Dimension(300, 40));
		bt_list.setPreferredSize(new Dimension(300, 40));
		
		//조립
		p_container.setLayout(new GridLayout(7, 2));
		p_container.add(la_title[0]);
		p_container.add(ch_top);
		p_container.add(la_title[1]);
		p_container.add(ch_sub);
		p_container.add(la_title[2]);					
		p_container.add(t_product_name);
		p_container.add(la_title[3]);					
		p_container.add(t_brand);
		p_container.add(la_title[4]);					
		p_container.add(t_price);
		p_container.add(la_title[5]);					
		p_container.add(t_filename);
		p_container.add(la_title[6]);					
		p_container.add(s1);

		this.add(p_container);  //현재 패널에 폼 컨테이너 부착
		this.add(bt_regist);  //현재 패널에 등록 버튼 부착
		this.add(bt_list);  //현재 패널에 목록 버튼 부착
	
		bt_regist.addActionListener((e)->{
			if(regist() == 0) {
				JOptionPane.showMessageDialog(RegistForm.this, "regist failed");
			}else {
				JOptionPane.showMessageDialog(RegistForm.this, "regist success");
				product.getProductList(null);  //목록을 갱신시키기 위한 메서드 호출
			}
		});
		
		//목록으로 돌아가기
		bt_list.addActionListener((e)->{
			product.addRemoveContent(this, product.p_center);			
		});
		
		ch_top.addItemListener((e)->{
			getSubCategory(ch_top.getSelectedIndex());
		});
	}
	
	//유저가 선택한 최상위 카테고리에서 선택한 항목의 하위 카테고리 가져오기
	public void getSubCategory(int index) {
		ArrayList<String> list = product.subList.get(index);
		ch_sub.removeAll();  //채워넣기 전에 기존 아이템들 삭제
		for(String item : list) {
			ch_sub.add(item);
		}
	}
	
	//유저가 선택한 아이템으로부터 subcategory의 pk를 가져오기
	public int getSubId(String name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int subcategory_id = 0;
		String sql = "select * from subcategory where name=?";
		try {
			pstmt = product.getAdminMain().getCon().prepareStatement(sql);  //쿼리 준비
			pstmt.setNString(1, name);  //매개변수로 전달될 아이템을 바인트 변수에 대입 
			rs = pstmt.executeQuery();
			if(rs.next()) {
				subcategory_id = rs.getInt("subcategory_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			product.getAdminMain().getDbManager().close(pstmt, rs);
		}
		
		return subcategory_id;
	}
	
	public int regist() {
		PreparedStatement pstmt = null;
		int result = 0;  //DML수행이 성공했는지 여부를 알 수 있는 변수
		String sql = "insert into product(product_id, subcategory_id, product_name";
		sql += ", brand, price, filename, detail) values(seq_product.nextval, ?, ?, ?, ?, ?, ?)";

		try {
			pstmt = product.getAdminMain().getCon().prepareStatement(sql);
			
			pstmt.setInt(1, getSubId(ch_sub.getSelectedItem()));  //선택한 아이템의 pk를 구하여 바인드 변수에 대입
			pstmt.setString(2, t_product_name.getText());  //상품명
			pstmt.setString(3, t_brand.getText());  //브랜드
			pstmt.setInt(4, Integer.parseInt(t_price.getText()));  //가격
			pstmt.setString(5, t_filename.getText());  //이미지 경로(url)
			pstmt.setString(6, t_detail.getText());  //상세정보
			
			result = pstmt.executeUpdate();  //DML
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			product.getAdminMain().getDbManager().close(pstmt);
		}
		return result;
	}	
}
