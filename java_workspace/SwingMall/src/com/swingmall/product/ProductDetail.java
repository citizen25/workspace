package com.swingmall.product;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.swingmall.admin.product.ProductVO;
import com.swingmall.main.Page;
import com.swingmall.main.ShopMain;

public class ProductDetail extends Page{
	public JPanel p_content;  //상세내용을 담게 될 패널
	public JPanel p_can;  //큰 상품 이미지 그려질 패널
	JPanel p_option;  //옵션 선택 영역
	JLabel la_brand;  //브랜드
	JLabel la_product_name;  //상품명 라벨
	JLabel la_price;  //상품 가격
	
	Choice ch_color;  //색상 옵션
	Choice ch_size;  //사이즈 옵션
	
	JButton bt_buy;  //구매하기 버튼
	JButton bt_cart;  //장바구니 버튼
	
	private ProductVO vo;
	private Image img;

	//상세페이지 호풀 시 상품 1개의 정보는 vo에, 그려질 이미지는 img에 전달
	public ProductDetail(ShopMain shopMain) {
		super(shopMain);
		
		p_content = new JPanel();
		p_can = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, p_can);
			}
		};
		p_option = new JPanel();
		la_brand = new JLabel();  //브랜드
		la_product_name = new JLabel();  //상품명
		la_price = new JLabel();  //가격
		
		ch_color = new Choice();
		ch_size = new Choice();
		
		//색상 채우기
		ch_color.add("red");
		ch_color.add("black");
		ch_color.add("white");

		ch_size.add("S");
		ch_size.add("M");
		ch_size.add("L");
		
		bt_buy = new JButton("Buy");
		bt_cart = new JButton("Cart");
		
		//스타일
		p_content.setPreferredSize(new Dimension(ShopMain.WIDTH-100, ShopMain.HEIGHT-200));
		p_content.setBackground(Color.YELLOW);
		p_option.setBackground(Color.PINK);
		
		Dimension d = new Dimension((ShopMain.WIDTH-100)/3, 30);
		
		la_brand.setPreferredSize(d);
		la_product_name.setPreferredSize(d);
		la_price.setPreferredSize(d);
		
		bt_buy.setPreferredSize(new Dimension(200, 30));
		bt_cart.setPreferredSize(new Dimension(200, 30));
		
		ch_color.setPreferredSize(d);
		ch_size.setPreferredSize(d);
		
		//조립
		p_content.setLayout(new GridLayout(1, 2));
		
		//p_content 구성하기
		p_option.add(la_brand);
		p_option.add(la_product_name);
		p_option.add(la_price);
		p_option.add(ch_color);
		p_option.add(ch_size);
		p_option.add(bt_buy);
		p_option.add(bt_cart);
		
		p_content.add(p_can);
		p_content.add(p_option);
		
		add(p_content);
	}
	
	//상세페이지가 보여질 때 데이터를 채워넣는 메서드 (생성자에서 하면 디자인 처리에 타이밍적인 제한이 많다)
	public void init(ProductVO vo, Image img) {
		la_brand.setText(vo.getBrand());  //브랜드 채워넣기
		la_product_name.setText(vo.getProduct_name());
		la_price.setText(Integer.toString(vo.getPrice()));
		this.img = img;
		this.img = this.img.getScaledInstance(500, 550, Image.SCALE_SMOOTH);
	}
	
	public ProductVO getVo() {
		return vo;
	}

	public void setVo(ProductVO vo) {
		this.vo = vo;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
	
}
