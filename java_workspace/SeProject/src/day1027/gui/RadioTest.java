/* 1) 글씨 크기 조절 : ctrl + (-,+ 키) 
 * 2) window > Preferences > General > Appearance > Colors and Fonts > Basic > Text Font 에서 Verdana체 선택 
 * 3) 단축키 모두 보기 : ctrl + shift + L 
 * 4) 자동 import : ctrl + shift + O
 * 5) 자동 정렬 : ctrl + shift + F
 * 6) 해당 객체의 api문서 바로가기 : 해당 클래스 커서 올린 후 shift + <F2>. 인터넷 연결이 전제됨
 * 7) 출력 : sout 입력후 ctrl + space 
 * 8) 코드 블럭 이동하기 : Alt + 위,아래 방향키 
 * 9) 줄 복사 : 블럭 지정 후 ctrl + Alt + 방향키 */

package day1027.gui;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.Frame;

public class RadioTest extends Frame{
	// 자바에서는 체크박스를 라디오로 사용함.
	CheckboxGroup group;
	
	public RadioTest() {
		group = new CheckboxGroup();
		setLayout(new FlowLayout());
		this.add(new Checkbox("운동", group, false));
		this.add(new Checkbox("독서", group, false));
		this.add(new Checkbox("컴퓨터", group, false));
		this.add(new Checkbox("영화", group, false));
		this.add(new Checkbox("요리", group, false));
		this.add(new Checkbox("애견 돌보기", group, true));
		
		setSize(300, 400);
		setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("겁나 빠름");
		new RadioTest();
	}

}
