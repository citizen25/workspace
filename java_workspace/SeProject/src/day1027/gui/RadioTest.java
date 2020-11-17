/* 1) �۾� ũ�� ���� : ctrl + (-,+ Ű) 
 * 2) window > Preferences > General > Appearance > Colors and Fonts > Basic > Text Font ���� Verdanaü ���� 
 * 3) ����Ű ��� ���� : ctrl + shift + L 
 * 4) �ڵ� import : ctrl + shift + O
 * 5) �ڵ� ���� : ctrl + shift + F
 * 6) �ش� ��ü�� api���� �ٷΰ��� : �ش� Ŭ���� Ŀ�� �ø� �� shift + <F2>. ���ͳ� ������ ������
 * 7) ��� : sout �Է��� ctrl + space 
 * 8) �ڵ� �� �̵��ϱ� : Alt + ��,�Ʒ� ����Ű 
 * 9) �� ���� : �� ���� �� ctrl + Alt + ����Ű */

package day1027.gui;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.Frame;

public class RadioTest extends Frame{
	// �ڹٿ����� üũ�ڽ��� ������ �����.
	CheckboxGroup group;
	
	public RadioTest() {
		group = new CheckboxGroup();
		setLayout(new FlowLayout());
		this.add(new Checkbox("�", group, false));
		this.add(new Checkbox("����", group, false));
		this.add(new Checkbox("��ǻ��", group, false));
		this.add(new Checkbox("��ȭ", group, false));
		this.add(new Checkbox("�丮", group, false));
		this.add(new Checkbox("�ְ� ������", group, true));
		
		setSize(300, 400);
		setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("�̳� ����");
		new RadioTest();
	}

}
