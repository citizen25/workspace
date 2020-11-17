package day1116.pubapi;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class MountainModel extends AbstractTableModel{

	//2차원 배열이 아니라, Vector를 선언해서 데이터와 컬럼명을 처리한다
	//벡터는 컬렉션 프레임웍이니 배열처럼 생성시 크기를 명시하지 않아도 된다. 유연~
	
	Vector<Mountain> data = new Vector<Mountain>();
		//Generic type의로 알맞는 자료형은?
		//산을 가져와서 테이블에 출력할 예정. 따라서 산이 들어와야 한다
	
	//컬럼 제목 정보를 담는 벡터
	Vector<String> columnName = new Vector<String>();
	
	//컬럼 제목을 구성할 벡터 요소는 생성자에서 채운다
	public MountainModel() {
		//컬럼 구성
		columnName.add("ID");  //산 아이디
		columnName.add("산 이름");  //산 이름
		columnName.add("소재지");  //산 소재지
		columnName.add("높이");  //산 높이
		
		//데이터 거짓으로 넣어보기(테스트용)
		Mountain mt = new Mountain();
		mt.setMntnid(1);
		mt.setMntnnm("설악산");
		mt.setMntninfopoflc("강원도");
		mt.setMntninfohght(1000);
	}
	
	//레코드 수(행의 수)는 벡터의 길이에서 가져오면 됨
	public int getRowCount() {
		return data.size();
	}

	//컬럼 수는 벡터의 길이에서 가져오면 된다
	public int getColumnCount() {
		return columnName.size();
	}

	//컬럼 제목을 출력하기 위한 메서드
	public String getColumnName(int col) {
		return columnName.get(col);  //배열이 아니므로 get(index)로 가져와야 한다
	}	
	
	public Object getValueAt(int row, int col) {
		//2차원 배열이나 벡터는 모두 배열구조이므로 지금까지는 [row][col] 형태로 데이터를 추출하였다
		//하지만, 이제 벡터에 들어있는 데이터(VO)는 [row]에 대한 접근은 가능하지만
		//[col]에 대한 접근은 불가하다
		//해결책) 조건문을 사용한다
		//즉, col이 0일때는 산의 아이디, 1일때 이름, 2일 때.... 등등 조건을 달아야 한다
		//System.out.println("row:"+row+"  col:"+col);  //호출시 컬럼값 확인하기
		
		String obj = null;  //각 조건에 따라 반환될 데이터
		Mountain mt = data.get(row);  //row번째에서 산을 하나 끄집어 낸다
		if(col == 0) {  //산의 아이디
			obj = Integer.toString(mt.getMntnid());
		}else if(col == 1) {  //산의 이름
			obj = mt.getMntnnm();
		}else if(col == 2) {  //산의 위치
			obj = mt.getMntninfopoflc();
		}else if(col == 3) {  //산의 높이
			obj = Integer.toString(mt.getMntninfohght());
		}
		//이 메서드의 반환형은 오브젝트형. 따라서 객체형(String, Integer등)으로 반환해야 한다
		//근데 JTable에 들어가는 모든 데이터는 String 취급할 수 있으므로, String으로 반환한다
		
		return obj;
	}

}
