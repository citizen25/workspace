package com.swingmall.cart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.swingmall.main.Page;
import com.swingmall.main.ShopMain;

public class Cart extends Page{
	JPanel bt_container;  //��ư�� ������ �����̳�
	JButton bt_pay;  //�����ܰ�� ����
	JButton bt_del;  //��ٱ��� ����
	
	//��ٱ��� ������ �� �÷��� �����ӿ� ��ü�� ����
	HashMap<Integer, CartVO> cartList;
	JPanel p_content;  //Cart�� ���� ������ ����, �������� ���� �����̳� �غ��Ѵ�
	
	public Cart(ShopMain shopMain) {
		super(shopMain);
		
		cartList = new HashMap<Integer, CartVO>();
		
		//this.setBackground(Color.BLACK);
		bt_container = new JPanel();
		bt_pay = new JButton("�����ϱ�");
		bt_del = new JButton("��ٱ��� ����");
		
		//��Ÿ��
		bt_container.setPreferredSize(new Dimension(ShopMain.WIDTH, 100));
		bt_container.setBackground(Color.CYAN);
		
		//getCartList();
		
		bt_container.add(bt_pay);
		bt_container.add(bt_del);
		add(bt_container, BorderLayout.SOUTH);
	}
	
	//��ٱ��Ͽ� �ֱ�
	public void addCart(CartVO vo) {  //��ǰ 1���� ��ٱ��Ͽ� �߰��ϱ�
		cartList.put(vo.getProduct_id(), vo);  //key�� value�� ����
	}
	//��ٱ��� �����ϱ�
	public void removeCart(int product_id) {  //��ǰ 1���� ��ٱ��Ͽ��� �����ϱ�
		cartList.remove(product_id);
	}
	//��ٱ��� ����
	public void updateCart(CartVO vo) {
		//�ؽøʿ� ����ִ� ��ü �� �ش� ��ü�� ã�Ƴ���, vo ��ü
		CartVO obj = cartList.get(vo.getProduct_id());  //Ű�� �˻�
		obj = vo;  //���� �ؽø��� �������ִ� vo�� ã�Ƴ��� �ּ� ����
	}
	//��ٱ��� ����
	public void removeAll() {  //��� ��ǰ�� ��ٱ��Ͽ��� �����ϱ�
		
	}
	
	//��ٱ��� ��� �������� (����: ���� ������ ���� �����̹Ƿ� ���� �Ϸķ� �þ�߷��� �Ѵ�. �� �Ŀ� ����)
	public void getCartList() {
		Set<Integer> set = cartList.keySet();  //key���� set���� ��ȯ �޴´�. ��, ���� �ѹ��� �Ϸķ� �þ�߸��� ���� �ƴ϶�
			//set���� ���� key�� �����;� ��
		//System.out.println(cartList.keySet());

		Iterator<Integer> it = set.iterator();
		
		//add�ϱ� ����, ������ �پ��ִ� ��� ������Ʈ�� �����Ѵ�
		int count=0;
		if(p_content != null) {
			this.remove(p_content);
			this.revalidate();
			this.updateUI();
			this.repaint();
		}
		
		//�������� ���� ����
		p_content = new JPanel();
		p_content.setPreferredSize(new Dimension(ShopMain.WIDTH-350, 500));
		
		while(it.hasNext()) {  //��Ұ� �ִ� ����..
			int key = it.next();  //��Ҹ� ����
			System.out.println("key : "+key);
			CartVO vo = cartList.get(key);
			//�������� ǥ���ϴ� CartItem�� CartVO�� ������ ä������
			CartItem item = new CartItem(vo);
			item.bt_del.addActionListener((e)->{
				if(JOptionPane.showConfirmDialog(Cart.this, "��ٱ��Ͽ��� �����Ͻðھ��?") == JOptionPane.OK_OPTION) {
					removeCart(vo.getProduct_id());
					getCartList();
				}
			});
			
			item.bt_update.addActionListener((e)->{
				if(JOptionPane.showConfirmDialog(Cart.this, "��ٱ��ϸ� �����Ͻðھ��?") == JOptionPane.OK_OPTION) {
					int ea = Integer.parseInt(item.t_ea.getText());  //������ ���� ���ϱ�
					vo.setEa(ea);  //����� ������ vo�� �ݿ��� �Ŀ� ����
					updateCart(vo);
					getCartList();
				}
			});
			
			p_content.add(item);
			count++;
		}
		add(p_content);
		this.updateUI();
		
		System.out.println("count is "+count);
	}
}
