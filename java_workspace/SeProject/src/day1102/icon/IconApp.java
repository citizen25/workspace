/*�ڹ��� ������Ʈ�� �̹����� �־�� (�̹��� ������)*/

package day1102.icon;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class IconApp extends JFrame{
	JButton bt;
	ImageIcon icon;  //==ImageIcon is icon
	
	public IconApp() {
		//os�� �������� ��θ� ������� ����, Ŭ�����н��� �������� �� ��η� �ڿ��� �̿��غ���!
		//icon = new ImageIcon("C:/workspace/java_workspace/SeProject/res/data/icon_youtube.png");
		
		URL url = this.getClass().getClassLoader().getResource("res/icon_youtube.png");
		
		icon = new ImageIcon(url);
		bt = new JButton(icon);
		
		//Image Ŭ������ �̹����� ũ�⸦ ������ �� �ִ� ����� �ִ�. == .getScaledInstance()
		Image img = icon.getImage();
		img = img.getScaledInstance(150, 120, Image.SCALE_SMOOTH);
		icon.setImage(img);  //�����ܿ� ����� �̹����� ���� ����.
		bt.setPreferredSize(new Dimension(150, 120));
		
		setLayout(new FlowLayout());
		add(bt);
		
		setVisible(true);
		setSize(300, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new IconApp();
	}

}
