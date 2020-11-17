package day1116.pubapi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MTMain extends JFrame{
	//서쪽 영역
	JPanel p_west;
	JTextField t_name;
	JTextField t_op1;
	JTextField t_op2;
	JTextField t_op3;
	JButton bt;
	
	//센터 영역
	JTable table;
	MountainModel model;
	JScrollPane scroll;
	Vector data = new Vector();  //2차원 벡터가 될 예정
		//Generic type의로 알맞는 자료형은?
		//산을 가져와서 테이블에 출력할 예정. 따라서 산이 들어와야 한다 -> 벡터 표기로 인해 불가능
	Vector<String> columnName = new Vector<String>();  //컬럼 제목 정보를 담는 벡터
		//JTable에서 데이터 연동 시 지금까지는 이차원 배열만 써왔는데, 
		//2차원 배열은 생성시 크기를 정해야하기 때문에 불편한 점이 많다
		//ex) rs.last() 후 rs.getRow()로 데이터 총 수 구하고, 다시 커서 원상복귀. 난리
		//따라서, CollectionFramework중 Vector를 지원하는 생성자를 이용한다	
		//2차원 배열이 아니라, Vector를 선언해서 데이터와 컬럼명을 처리한다
		//벡터는 컬렉션 프레임웍이니 배열처럼 생성시 크기를 명시하지 않아도 된다. 유연~
		
	
	public MTMain() {
		init();  //데이터 채우기
		
		//생성
		p_west = new JPanel();
		t_name = new JTextField();
		t_op1 = new JTextField();
		t_op2= new JTextField();
		t_op3 = new JTextField();
		bt = new JButton("검색하기");
				
		//벡터를 사용할 때는 TableModel 자체를 사용할 필요가 없을 때이다
		table = new JTable(data, columnName);  //매개변수로 vector를 넣는다
		scroll = new JScrollPane(table);
		
		//스타일 적용
		p_west.setPreferredSize(new Dimension(200, 600));
		p_west.setBackground(Color.WHITE);
		t_name.setPreferredSize(new Dimension(190, 30));
		t_op1.setPreferredSize(new Dimension(190, 30));
		t_op2.setPreferredSize(new Dimension(190, 30));
		t_op3.setPreferredSize(new Dimension(190, 30));
		
		//부착
		p_west.add(t_name);
		p_west.add(t_op1);
		p_west.add(t_op2);
		p_west.add(bt);
		
		add(p_west, BorderLayout.WEST);
		add(scroll);
		
		setSize(900, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	//벡터값을 초기화하자
	public void init() {
		//데이터 1건 넣어보기
		//JTable에 벡터를 매개변수로 넣는 방식은 객체지향적인 요즘 개발방식에 맞지 않음
		//이유) 벡터안에 벡터를 넣어야하므로, 이차원 배열 방식과 다를 바 없음
		//따라서, 아래와 같이 벡터 안에 또 하나의 벡터를 추가해야한다
		Vector v = new Vector();
		v.add("1");
		v.add("속리산");
		v.add("강원도");
		v.add("3000");
		
		data.add(v);  //벡터 안의 벡터. 결국 2차원 배열의 모습을 그대로 유지
			//우리가 원했던 모습은 Mountain VO를 넣는 거였지만, 벡터를 강제한다. 옛날 방식
		//벡터안의 벡터 방식 vs 벡터 안의 VO 방식
			//vo방식으로 가는게 낫다.그래야 웹개발할 때 대형급에 적응할 수 있다
			//그러나 jtable은 vo를 지원하지 않는다
			//따라서 우리가 직접 만들어야 한다 -> MTMain에서 구현
		
		//컬럼정보 채우고
		columnName.add("ID");  //산 아이디
		columnName.add("산 이름");  //산 이름
		columnName.add("소재지");  //산 소재지
		columnName.add("높이");  //산 높이
	}
	
	public static void main(String[] args) {
		new MTMain();
	}

}
