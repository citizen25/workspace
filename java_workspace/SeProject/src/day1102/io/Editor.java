/*문서 파일을 열고 저장이 가능한 텍스트 에디터 만들기
	한글이 포함된 문서파일을 대상으로 하므로, 당연히 문자기반 스트림을 사용해야 한다.*/

package day1102.io;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

// ctrl + shift + F : 자동들여쓰기

public class Editor extends JFrame implements ActionListener{

	JMenuBar bar;
	JMenu menu;
	JMenuItem item_new, item_open, item_save, item_saveas;
	JTextArea area;
	JFileChooser chooser;
	FileReader reader;
	FileWriter writer;
	File selectedFile;  //지금 열어놓고 있는 파일.
	
	BufferedReader buffr;
	BufferedWriter buffw;
	
	public Editor() {
		
		//생성
		bar = new JMenuBar();
		menu = new JMenu("파일");
		item_new = new JMenuItem("새 파일");
		item_open = new JMenuItem("열기");
		item_save = new JMenuItem("저장");
		item_saveas = new JMenuItem("다른 이름으로 저장");
		area = new JTextArea();
		chooser = new JFileChooser("C:/workspace/java_workspace/SeProject/res/data");
		//FileChooser는 생성한다고 바로 뜨는게 아니다. 메서드를 호출해야 뜨기 때문에, 생성 먼저 해놓는다.
		
		//스타일
		
		//조립
		setJMenuBar(bar);
		bar.add(menu);
		menu.add(item_new);
		menu.add(item_open);
		menu.add(item_save);
		menu.add(item_saveas);
		
		add(area);  //센터에 부착
		
		//아이템들과 리스너 연결
		item_new.addActionListener(this);
		item_open.addActionListener(this);
		item_save.addActionListener(this);
		item_saveas.addActionListener(this);
		
		setSize(800, 700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); //화면 정가운데
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == item_new) {
			
		}else if(obj == item_open) {
			//JOptionPane.showMessageDialog(this, "여시겠슴까?");
			open();
		}else if(obj == item_save) {
			save();
		}else if(obj == item_saveas) {
			
		}
	}
	
	//파일열기
	public void open() {
		//파일 탐색기 띄우기
		int result = chooser.showOpenDialog(this);
		System.out.println(result);  //0이 '열기', 1이 '취소'
		if(result == JFileChooser.APPROVE_OPTION) {  //'열기'버튼 눌렀다면..
			//선택한 파일에 스트림을 꽂아서 읽기 작업 시도.
			selectedFile = chooser.getSelectedFile();
			//유저가 chooser로부터 선택한 파일 정보를 File클래스로 반환 받는다.
			
			//윈도우에 제목으로 파일 이름을 출력
			this.setTitle(selectedFile.getAbsolutePath());
			try {
				reader = new FileReader(selectedFile);  //선택한 파일에 대한 스트림 생성.
				//생성된 stream으로부터 데이터를 읽어와서, area에 출력.
				buffr = new BufferedReader(reader);
				int data;
				String str = null;
				int count=0;
				while(true) {
					//data = reader.read();
					str = buffr.readLine();
					count++;
					System.out.println(count);
					//if(data == -1) {break;}
					if(str == null) {break;}  //String은 객체이므로, 데이터가 없을 때에는 null을 반환함.
					//기본데이터형 --> 객체자료형. wrapper.
					//area.append(Character.toString((char)data));  //String이 와야함.					
					area.append(str+"\n");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
		}
	}
	
	//실행 중인 프로그램에서 데이터를 파일에 출력해야 됨. (출력스트림 필요)
	public void save() {
		try {
			writer = new FileWriter(selectedFile);
			writer.write(area.getText());  //textarea의 String을 출력.
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new Editor();
	}

}
