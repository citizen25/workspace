package day1111.board;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BoardWrite extends JPanel{
	JTextField t_title;
	JTextField t_writer;
	JTextArea content;
	JScrollPane scroll;
	
	JButton bt_regist;
	JButton bt_list;
	BoardApp boardApp;
	Connection con;
	
	public BoardWrite(BoardApp boardApp) {
		this.boardApp = boardApp;
		con = boardApp.getCon();
		
		t_title = new JTextField();
		t_writer = new JTextField();
		content = new JTextArea();
		scroll = new JScrollPane(content);
		bt_regist = new JButton("글등록");
		bt_list = new JButton("목록보기");

		//스타일
		t_title.setPreferredSize(new Dimension(780, 35));
		t_writer.setPreferredSize(new Dimension(780, 35));
		scroll.setPreferredSize(new Dimension(780, 300));
		
		//조립
		add(t_title);
		add(t_writer);
		add(scroll);
		add(bt_regist);
		add(bt_list);
		
		setPreferredSize(new Dimension(780, 500));
		setVisible(true);
		
		bt_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boardApp.setPage(BoardApp.BOARD_LIST);
			}
		});
		
		bt_regist.addActionListener((e)->{
			regist();
			BoardList boardList = (BoardList)boardApp.getPages(BoardApp.BOARD_LIST);
			boardList.getList();  //목록보기 메서드 호출
		});
	}
	
	//글 등록하기
	public void regist() {
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO board(board_id, title, writer, content)";
		sql += " VALUES(seq_board.nextval, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);  //sql 준비
			//바인드 변수 지정
			pstmt.setString(1, t_title.getText());
			pstmt.setString(2, t_writer.getText());
			pstmt.setString(3, content.getText());
			
			int result = pstmt.executeUpdate();  //쿼리실행
			if(result == 0) {
				JOptionPane.showMessageDialog(this, "등록 실패");
			}else {
				JOptionPane.showMessageDialog(this, "등록 성공");
				boardApp.setPage(BoardApp.BOARD_LIST);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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







