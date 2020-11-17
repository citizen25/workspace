/*���ͳ����� ��ǰ �̹��� ��������*/

package day1106.db;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import common.file.FileManager;

public class CollectorFrame  extends JFrame{
	JTextField t_url;
	JButton bt;
	JButton bt_apply;  //�̹��� �׸��� �ݿ��ϱ�
	
	BufferedImage buffr;  //url�� ������ �̹��� ������ ���� ��ü
	ShoppingApp shoppingApp;
	File file;  //���ͳ����κ��� ������ ����
	
	public CollectorFrame(ShoppingApp shoppingApp){
		this.shoppingApp = shoppingApp;
		
		setLayout(new FlowLayout());
		
		t_url = new JTextField();
		bt = new JButton("��������");
		bt_apply = new JButton("�ݿ��ϱ�");
		
		t_url.setPreferredSize(new Dimension(580, 40));
		
		add(t_url);
		add(bt);
		add(bt_apply);
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				collect();
			}
		});
		
		bt_apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ShoppingApp Ŭ������ img������ ���� ���ͳݻ� �̹����� ��ü�ϰ�
				shoppingApp.getTargetImage(file.getAbsolutePath());
				
				//ShoppingApp Ŭ������ preview() �޼��� ȣ��
				shoppingApp.preview();
				
			}
		});
		
		setLocationRelativeTo(shoppingApp);
		setVisible(true);
		setSize(600, 250);
		//System.exit(0);  //�ڽ�â�� �θ�â���� �׿�������
	}
	
	public void collect() {
		//������ �����Ͱ� �̹����� ��쿣 �Ʒ��� ����� �����ϴ�
		try {
			URL url = new URL(t_url.getText());
			buffr = ImageIO.read(url);
			
			//��������� �޸�� �����ϹǷ�, ���� ���Ϸ� �����س���
			//������ ���ϸ��� �츮�� ��������. ��Ģ�� �־�� �Ѵ�. �� �� �� �и��� �� �ϸ� ���ϸ��� ���ϼ��� ������ �� ���� ���̴�
			long time = System.currentTimeMillis();  //���� �ð��� ��ȯ���ִ� �޼���
			System.out.println(time);
			String filename = FileManager.getFilename("https://thumbnail9.coupangcdn.com/thumbnails/remote/230x230ex/image/vendor_inventory/b6d4/c135bd7d593950570c0b166d56ebd283bdadc60e06b2441336fa59745553.jpg");
			String extend = FileManager.getExtend(filename);
			
			//������ ����
			file = new File("C:/workspace/java_workspace/SeProject/res/travel2/"+time+"."+extend);
			
			//�����Ͽ��ٰ� �̹��� �����͸� ����(���)
			ImageIO.write(buffr, extend, file);
			JOptionPane.showMessageDialog(this, "�������� �Ϸ�");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
