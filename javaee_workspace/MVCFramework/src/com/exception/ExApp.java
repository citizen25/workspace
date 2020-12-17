package com.exception;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExApp {

	//throws : 현재 메서드에서 해당 예외를 처리하지 않고, 이 메서드를 호출한 자에게 떠넘기는 것 
	public void insert() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		pstmt = con.prepareStatement("insert~~");
		pstmt.executeUpdate();
		
		/*
		try {
			pstmt = con.prepareStatement("insert~~");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}
	
	public static void main(String[] args) throws SQLException {
		ExApp app = new ExApp();
		app.insert();
	}

}
