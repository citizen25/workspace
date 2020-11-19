//모든 페이지가 공통적으로 가져야할 속성, 메서드등을 정의하기위한 최상위 페이지 클래스
//따라서 Home, Product, Q&A, Mypage, Login 등의 페이지들이
//이 클래스를상속받을 경우, 코드를 중볷해서 작성하지 않아도 된다

package com.swingmall.main;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Page extends JPanel{
	private ShopMain shopMain;
	public Page(ShopMain shopMain) {
		this.shopMain = shopMain;
		this.setPreferredSize(new Dimension(ShopMain.WIDTH, ShopMain.HEIGHT-100));
	}
	
	public ShopMain getShopMain() {
		return shopMain;
	}
}
