//��� �������� ���������� �������� �Ӽ�, �޼������ �����ϱ����� �ֻ��� ������ Ŭ����
//���� Home, Product, Q&A, Mypage, Login ���� ����������
//�� Ŭ��������ӹ��� ���, �ڵ带 �ߓ��ؼ� �ۼ����� �ʾƵ� �ȴ�

package com.swingmall.main;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Page extends JPanel{
	ShopMain shopMain;
	public Page(ShopMain shopMain) {
		this.shopMain = shopMain;
		this.setPreferredSize(new Dimension(ShopMain.WIDTH, ShopMain.HEIGHT-100));
	}
}