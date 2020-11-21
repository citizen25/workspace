package com.swingmall.product;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.swingmall.admin.product.ProductVO;
import com.swingmall.cart.Cart;
import com.swingmall.cart.CartVO;
import com.swingmall.main.Page;
import com.swingmall.main.ShopMain;

public class ProductDetail extends Page{
	public JPanel p_content;  //�󼼳����� ��� �� �г�
	public JPanel p_can;  //ū ��ǰ �̹��� �׷��� �г�
	JPanel p_option;  //�ɼ� ���� ����
	JLabel la_brand;  //�귣��
	JLabel la_product_name;  //��ǰ�� ��
	JLabel la_price;  //��ǰ ����
	
	Choice ch_color;  //���� �ɼ�
	Choice ch_size;  //������ �ɼ�
	
	JButton bt_buy;  //�����ϱ� ��ư
	JButton bt_cart;  //��ٱ��� ��ư
	
	private ProductVO vo;
	private Image img;

	//�������� ȣǮ �� ��ǰ 1���� ������ vo��, �׷��� �̹����� img�� ����
	public ProductDetail(ShopMain shopMain) {
		super(shopMain);
		
		p_content = new JPanel();
		p_can = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, p_can);
			}
		};
		p_option = new JPanel();
		la_brand = new JLabel();  //�귣��
		la_product_name = new JLabel();  //��ǰ��
		la_price = new JLabel();  //����
		
		ch_color = new Choice();
		ch_size = new Choice();
		
		//���� ä���
		ch_color.add("red");
		ch_color.add("black");
		ch_color.add("white");

		ch_size.add("S");
		ch_size.add("M");
		ch_size.add("L");
		
		bt_buy = new JButton("Buy");
		bt_cart = new JButton("Cart");
		
		//��Ÿ��
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
		
		//����
		p_content.setLayout(new GridLayout(1, 2));
		
		//p_content �����ϱ�
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
		
		//��ٱ��� ������ ����
		bt_cart.addActionListener((e)->{
			registCart();  //��ٱ��Ͽ� ��ǰ �߰��ϱ�
			
			//��ٱ��Ͽ� ������ ���ٰ� �˷��ְ�, ��ٱ��� �̵� ���θ� Ȯ���ؾ���
			int ans = JOptionPane.showConfirmDialog(ProductDetail.this, "��ٱ��Ͽ� ��ǰ�� �����ϴ�.\n��ٱ��Ϸ� �̵��Ͻðھ��?");
			if(ans == JOptionPane.OK_OPTION) {
				getShopMain().showPage(ShopMain.CART);
			}
			getShopMain().showPage(ShopMain.CART);
		});
	}
	
	//���������� ������ �� �����͸� ä���ִ� �޼��� (�����ڿ��� �ϸ� ������ ó���� Ÿ�̹����� ������ ����)
	public void init(ProductVO vo, Image img) {
		this.vo = vo;  //�⺭������ ���� �����ִ� ��ǰ vo�� ����
		la_brand.setText(vo.getBrand());  //�귣�� ä���ֱ�
		la_product_name.setText(vo.getProduct_name());
		la_price.setText(Integer.toString(vo.getPrice()));
		this.img = img;
		this.img = this.img.getScaledInstance(500, 550, Image.SCALE_SMOOTH);
	}
	
	//��ٱ��Ͽ� ��� (DB�� �������� �ʰ� ���� �޸� ������ ������ ����)
	public void registCart() {
		Cart cartPage = (Cart)getShopMain().getPage()[ShopMain.CART];  //��ٱ��� �������� ����
		CartVO cartVO = new CartVO();  //Empty vo ����
		
		cartVO.setProduct_id(vo.getProduct_id());  //���� �����ִ� ��ǰ�� �̿��Ͽ� CartVO�� ä���
		cartVO.setBrand(vo.getBrand());
		cartVO.setProduct_name(vo.getProduct_name());
		cartVO.setPrice(vo.getPrice());
		cartVO.setFilename(vo.getFilename());
		cartVO.setDetail(vo.getDetail());
		cartVO.setEa(1);  //��ٱ��Ͽ� ���� ��, �⺻�� 1���̴�
		cartVO.setColor(ch_color.getSelectedItem());  //������ ����
		cartVO.setSize(ch_size.getSelectedItem());  //������ ������
		
		cartPage.addCart(cartVO);  //��ٱ��Ͽ� ��ǰ 1�� �߰��ϱ�
		cartPage.getCartList();  //��ٱ��� ��� �����ϱ�
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
