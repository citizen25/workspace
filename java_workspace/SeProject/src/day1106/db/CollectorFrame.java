/*인터넷으로 제품 이미지 가져오기*/

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
	JButton bt_apply;  //이미지 그리기 반영하기
	
	BufferedImage buffr;  //url로 가져온 이미지 정보를 담을 객체
	ShoppingApp shoppingApp;
	File file;  //인터넷으로부터 수집한 파일
	
	public CollectorFrame(ShoppingApp shoppingApp){
		this.shoppingApp = shoppingApp;
		
		setLayout(new FlowLayout());
		
		t_url = new JTextField();
		bt = new JButton("수집실행");
		bt_apply = new JButton("반영하기");
		
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
				//ShoppingApp 클래스의 img변수의 값을 인터넷상 이미지로 교체하고
				shoppingApp.getTargetImage(file.getAbsolutePath());
				
				//ShoppingApp 클래스의 preview() 메서드 호출
				shoppingApp.preview();
				
			}
		});
		
		setLocationRelativeTo(shoppingApp);
		setVisible(true);
		setSize(600, 250);
		//System.exit(0);  //자식창이 부모창까지 죽여버린다
	}
	
	public void collect() {
		//가져올 데이터가 이미지인 경우엔 아래의 방법이 유용하다
		try {
			URL url = new URL(t_url.getText());
			buffr = ImageIO.read(url);
			
			//현재까지는 메모렝 존재하므로, 실제 파일로 저장해놓자
			//저장할 파일명은 우리가 지정하자. 규칙이 있어야 한다. 시 분 초 밀리초 로 하면 파일명의 유일성을 보장할 수 있을 것이다
			long time = System.currentTimeMillis();  //현재 시간을 반환해주는 메서드
			System.out.println(time);
			String filename = FileManager.getFilename("https://thumbnail9.coupangcdn.com/thumbnails/remote/230x230ex/image/vendor_inventory/b6d4/c135bd7d593950570c0b166d56ebd283bdadc60e06b2441336fa59745553.jpg");
			String extend = FileManager.getExtend(filename);
			
			//빈파일 생성
			file = new File("C:/workspace/java_workspace/SeProject/res/travel2/"+time+"."+extend);
			
			//빈파일에다가 이미지 데이터를 쓰자(출력)
			ImageIO.write(buffr, extend, file);
			JOptionPane.showMessageDialog(this, "가져오기 완료");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
