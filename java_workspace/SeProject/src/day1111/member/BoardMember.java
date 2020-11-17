/*이 클래스는 현실의 '회원'을 표현한 클래스이다
	주의) 로직을 작성하기 위해 정의한 클래스가 아니라, 단지 멤버변수에 데이터를 담기 위한 목적으로 만든 더미한 클래스이다
	어플리케이션 설계 분야에서는 이러한 목적의 객체를 가리켜
		DTO : Data Transfer Object(메서드의 매개변수로 전달하기 위한 객체)
		VO : Value Object(값을 담기 위한 객체)
		주로 데이터베이스 연동 시, 하나의 레코드를 담기 위한 용도로 많이 사용된다*/

package day1111.member;

public class BoardMember {

	private int member_id;
	private String m_id;
	private String m_pass;
	private String m_name;
	private String regdate;
	
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_pass() {
		return m_pass;
	}
	public void setM_pass(String m_pass) {
		this.m_pass = m_pass;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
		
	
}
