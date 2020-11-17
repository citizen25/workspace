/*엔터프라이즈급의 개발분야(java, .net 분야)에서는 규모가 워낙 크다보니,
	유지보수성을 높이기 위한 각종 개발 방법들이 존재한다
	특히, 데이터베이스 관련 업무에서는 Table마다 1:1 대응하는 자바의 클래스를 VO로 정의해놓는것은 당연하다*/

package day1111.board;

public class Board {
	private int board_id;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
}
