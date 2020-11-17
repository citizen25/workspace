package day1111.board;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BoardList extends JPanel{
	JTable table;
	JScrollPane scroll;
	JButton bt;
	BoardApp boardApp;
	BoardModel boardModel;
	Connection con;
	
	public BoardList(BoardApp boardApp) {
		this.boardApp = boardApp;
		con = boardApp.getCon();
		
		table = new JTable(boardModel = new BoardModel());
		scroll = new JScrollPane(table);
		bt = new JButton("글쓰기");
		
		getList();
		
		setLayout(new BorderLayout());
		add(scroll);
		add(bt, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(780, 500));
		setVisible(true);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				//상세보기가 보유한 getDetail() 메서드 호출하기
				BoardDetail boardDetail = (BoardDetail)boardApp.getPages(BoardApp.BOARD_DETAIL);
				String board_id = (String)table.getValueAt(table.getSelectedRow(), 0);  //board_id
				boardDetail.getDetail(Integer.parseInt(board_id));  //상세보기
				boardDetail.updateHit(Integer.parseInt(board_id));  //조회수 증가 메서드 호출
				
				boardApp.setPage(BoardApp.BOARD_DETAIL);  //상세보기
			}
		});
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//글쓰기 폼의 작성자에 아이디 넣어두기
				BoardWrite page = (BoardWrite)boardApp.getPages(BoardApp.BOARD_WRITE);
				page.t_writer.setText(boardApp.getBoardMember().getM_id());
				
				boardApp.setPage(BoardApp.BOARD_WRITE);  //글쓰기 폼 보여주기
			}
		});
	}
	
	//오라클 연동하기
	//rs에 담겨진 데이터를 TableModel이 보유한 이차원 배열 data에 대입
	public void getList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM board ORDER BY board_id DESC";
		
		try {
			//스크롤이 가능하고(next()능력에 자유자재 이동능력 추가), 읽기전용인 rs를 만들기 위한 옵션
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			int total = rs.getRow();  //총 레코드 수 반환
			
			//rs에 들어있는 데이터를 이차원 배열로 옮겨심어보자
			//먼저 이차원 배열을 준비해놓아야 한다
			String[][] records = new String[total][5];
			
			rs.beforeFirst();
			int index = 0;
			while(rs.next()) {
				String[] arr = new String[5];
				arr[0] = rs.getString("board_id");
				arr[1] = rs.getString("title");
				arr[2] = rs.getString("writer");
				arr[3] = rs.getString("regdate");
				arr[4] = rs.getString("hit");
				
				records[index++] = arr;  //일차원 배열을 이차원 배열의 방에 담음
			}
			
			//이차원 배열이 모두 완성되었으므로, TableModel이 보유한 data 이차원 배열에 대입하면 된다
			boardModel.data = records;
			
			table.updateUI();  //JTable UI 갱신
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
