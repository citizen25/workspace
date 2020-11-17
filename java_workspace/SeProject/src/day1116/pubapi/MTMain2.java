package day1116.pubapi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class MTMain2 extends JFrame{
	
	//서쪽 영역
	JPanel p_west;
	JTextField t_name;
	JTextField t_op1;
	JTextField t_op2;
	JTextField t_op3;
	JButton bt;
	
	//센터 영역
	JTable table;
		//JTable에서 지원하는 Vector방식은 2차원 배열보다는 유연하지만,
		//여전히 2차원 배열의 구조를 유지하므로, TableModel을 이용한 벡터 및 VO를 종합해서 개발한다
	MountainModel model;
	JScrollPane scroll;
	
	//멤버변수로 파서 보유하기
	String apiKey = "Zy%2FGcloPgi2ip5cvJFl4l1f6umtALKjXZOf52uWjv2f4WohIT7RSa31OiAnr1hNLsAZveGqnoRqW6E3eqSinSQ%3D%3D";
	SAXParserFactory factory;
	SAXParser saxParser;
	Thread loadThread;  //네트워크상에서 xml을 불러올 때 사용할 thread. 
		//버튼을 누를 때마다 인스턴스 생성하여 네트워크 업무를 시키자 == 분신 생성
	InputStream is;  //xml데이터를 담고 있는 스트림
	MountainHandler mountainHandler;
	
	//파싱이 끝나면 닫아주기 위해 멤버변수로 선언
	BufferedReader rd;
	HttpURLConnection conn;
	
	public MTMain2() {
		//생성
		p_west = new JPanel();
		t_name = new JTextField();
		t_op1 = new JTextField();
		t_op2= new JTextField();
		t_op3 = new JTextField();
		bt = new JButton("검색하기");
				
		//TableModel을 이용한 개발 방식으로 간다
		table = new JTable(model = new MountainModel());
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
		
		//버튼과 리스너 연결
		bt.addActionListener((e)->{
			//네트워크를 타고 데이터를 가져올 때 메인 thread에서 가져오는 것은 좋지 않다
			//thread를 내부익명으로 처리
			loadThread = new Thread() {
				public void run() {  //버튼 누를 때 이 쓰레드 호출
					loadXML();
				}
			};
			//loadXML();
			loadThread.start();  //xml로딩 쓰레드 호출
		});
		
		setSize(900, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	//아직 파싱하는 작업이라기보다는 URL에서 xml을 가져오는 단계이므로 메서드명은 loadXML로 한다.
	public void loadXML() {
		//여기에 공공데이터 포털에서 공개한 소스코드를 붙여넣는다. 키값은 멤버변수에 선언
		//아래의 코드를 모두 Exception으로 감싼다(예외처리)
		try {
			StringBuilder urlBuilder = new StringBuilder("http://openapi.forest.go.kr/openapi/service/trailInfoService/getforeststoryservice"); /*URL*/
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + apiKey); /*Service Key*/
			urlBuilder.append("&" + URLEncoder.encode("mntnNm","UTF-8") + "=" + URLEncoder.encode(t_name.getText(), "UTF-8")); /**/
			urlBuilder.append("&" + URLEncoder.encode("mntnHght","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /**/
			urlBuilder.append("&" + URLEncoder.encode("mntnAdd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /**/
			urlBuilder.append("&" + URLEncoder.encode("mntnInfoAraCd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /**/
			urlBuilder.append("&" + URLEncoder.encode("mntnInfoSsnCd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /**/
			urlBuilder.append("&" + URLEncoder.encode("mntnInfoThmCd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /**/
			URL url = new URL(urlBuilder.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			//System.out.println("Response code: " + conn.getResponseCode());
			
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			    rd = new BufferedReader(new InputStreamReader(is = conn.getInputStream()));
			} else {
			    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			
			//아래의 코드는 화면에 출력하기 위한 코드이므로, 테스트가 끝아면 제거해야 한다
			//이유) read를 여기서 해버리면 이후의 라인에서는 스트림의 끝에 도달하게 되므로..
			//파실 하기도 전에 소모시켜버리지 말자
			/*
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
			    sb.append(line);
			}
			*/

			//제대로 불러와지는지, sb를 콘솔에 출력
			//System.out.println(sb.toString());
			
			parseData();  //이 시점부터 파싱 시작
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//주의) 지금 파싱할 데이터는 
	public void parseData() {
		factory = SAXParserFactory.newInstance();  //팩토리 객체 생성(이 객체가 있어야 parser를 생성)
		try {
			saxParser = factory.newSAXParser();  //파서 생성
			saxParser.parse(is, mountainHandler = new MountainHandler());  //파싱 시작
			
			//파싱이 끝아면 TableModel의 벡터객체를 파싱한 결과로 얻은 벡터로 대체해버리면 된다
			model.data = mountainHandler.mtList;
			table.updateUI();  //테이블 갱신
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(rd != null) {
				try {
					rd.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
			if(conn != null) {
				conn.disconnect();				
			}

		}
		
	}
	
	public static void main(String[] args) {
		new MTMain2();
	}

}
