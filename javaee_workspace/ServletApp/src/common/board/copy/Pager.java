//Pageing 처리를 재사용성을 높이기 위해 클래스 정의

package common.board.copy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Pager {
	private int totalRecord;  //총 레코드 수
	private int pageSize = 10;  //페이지 당 보여질 레코드 수
	private int totalPage;  //총 페이지 수
	private int blockSize = 10;  //블럭당 보여질 페이지 수
	private int currentPage = 1;  //현재 페이지
	private int firstPage;  //현재 페이지 -현재 페이지와 가장 근접한 수;
	private int lastPage;  //firstPage를 이용해서 구함
	private int curPos;  //페이지 당 List 내에서의 시작 index
	private int num;  //페이지 당 시작 번호
	
	//선언된 변수 초기화
	public void init(HttpServletRequest request, List list) {
		totalRecord = list.size();
		totalPage = (int)Math.ceil((float)totalRecord/pageSize);
		//페이지를 선택한 경우엔, 그 선택된 페이지로 대체
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		firstPage = currentPage - (currentPage-1)%blockSize;
		lastPage = firstPage + (blockSize - 1);
		curPos = (currentPage - 1) * pageSize;
		num = totalRecord - curPos;
		
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getCurPos() {
		return curPos;
	}

	public void setCurPos(int curPos) {
		this.curPos = curPos;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
}
