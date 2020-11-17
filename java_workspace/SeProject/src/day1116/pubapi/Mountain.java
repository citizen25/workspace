
//산 1개를 담게 될 클래스 정의

package day1116.pubapi;

public class Mountain {
	//멤버변수로는 오픈 데이터 포털의 산 정보에 잇는 모든 데이터를 다 넣기엔 너무 많다
	//원하는 것을 골라 담는다
	//적어도 산의 이름은 있어야 하니까, 산 이름에 해당하는 xml태그가 뭔지 조사하자 -> mntnnm
	
	private int mntnid;  //산의 고유 코드
	private String mntnnm;  //산 이름 담을 변수
	private String mntninfopoflc;  //산정보 소재지(소재지)
	private int mntninfohght;  //산 높이

	//은닉화 한다 (getter/setter)
	public int getMntnid() {
		return mntnid;
	}
	public void setMntnid(int mntnid) {
		this.mntnid = mntnid;
	}
	public String getMntnnm() {
		return mntnnm;
	}
	public void setMntnnm(String mntnnm) {
		this.mntnnm = mntnnm;
	}
	public String getMntninfopoflc() {
		return mntninfopoflc;
	}
	public void setMntninfopoflc(String mntninfopoflc) {
		this.mntninfopoflc = mntninfopoflc;
	}
	public int getMntninfohght() {
		return mntninfohght;
	}
	public void setMntninfohght(int mntninfohght) {
		this.mntninfohght = mntninfohght;
	}
}
