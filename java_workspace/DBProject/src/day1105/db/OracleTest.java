/*�ڹٿ��� �����ͺ��̽� �����ϴ� ����� ������ 
	JDBC(Java DataBase Connectivity)��� �ϸ�
	java.sql ��Ű������ api�� �����ȴ�.

	[�����ͺ��̽� ���� ������ ����]
	1) DB ������ �˸´� ����̹��� �ε� (oracle, mysql, mssql.. ���� �ٸ� jar �ʿ�)
	2) ����
	3) ���ϴ� ���� ����
	4) ���� ����(Ư�� stream �� DB�� ��� �� �ݵ�� ��������)*/
package day1105.db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class OracleTest{
	public static void main(String[] args){
		//�޼����� ���������� �ݵ�� �ʱ�ȭ�ؾ� �� (�������ó�� �ڵ����� ������ �ʴ´�)
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			Class.forName("oracle.jdbc.OracleDriver");  //����ϰ����ϴ� ����̹� Ŭ������ ��� (Ŭ���� �ε�)
			//���� ������� OS�÷����� Ŭ���� �н��� ��ϵǾ� �־�� �Ѵ�.	
			System.out.println("����̹� �ε� ����");

			//���ӽ� �ʿ��� ���� ���� : DB������ url, id, password
			String url = "jdbc:oracle:thin:@localhost:1521:XE";  //������ ��Ģ
			String user = "user1104";
			String password = "user1104";

			//���� �õ� ��, ������ �����ϸ�, Connenction�̶�� ���ͺ��̽��� �ν��Ͻ��� ��ȯ����
			//Connection���� ������ ���� ������ ����ִ�
			//���� ���� ���δ� Connection ��ü�� null���η� �Ǵ��Ѵ�
			//���� �õ��� Connection �ν��Ͻ��� �ֱ�
			con = DriverManager.getConnection(url, user, password);
			
			if(con == null){
				System.out.println("���� ����");				
			}else{
				System.out.println("���� ����");
				//������ ���������Ƿ�, �������� ������ �� �ִ�.
				//�������� �����ϴ� ��ü�� PreparedSatatement �������̽��̴�.
				String sql = "insert into member(member_id, name, age, phone)";
				sql += " values(seq_member.nextval, 'adams', 38, '010')";  //�����ݷ��� ����. �ܼ��� �ƴ϶�

				//������ �����Ǿ��, �������� ������ �� �����Ƿ�, Connection ��ü�κ��� �ν��Ͻ��� ���� �Ѵ�
				pstmt = con.prepareStatement(sql);  //���� ������ �غ�

				//���� ���� �� �������θ� �Ǵ��ϱ� ���ؼ��� executeUpdate() �޼����� ��ȯ���� �̿��Ѵ�
				//��ȯ���� int���̸�, ������ ���࿡ ���� �ݿ��� ���ڵ��� ���� ��ȯ�Ѵ�
				//���� insert�� ��� ��ȯ���� 1. update, delete�� �� ����  ����޾Ҵ��� �� ���� ����. ���ǵ��� �ٸ�
				//��ġ�� insert, update, delete � ���̶� ��ȯ���� 0�̶�� ���з� �����ϸ� �ȴ�.
				int result = pstmt.executeUpdate();  //DML(insert, update, delete)���� ����ÿ� �� �޼��带 ���
				if(result != 1){
					System.out.println("�Է� ����");
				}else{
					System.out.println("�Է� ����");
				}

			}

		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("������ ����̹��� ã�� �� �����ϴ�.");
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("������ ����̹��� ã�� �� �����ϴ�.");
		}finally{
			//db������ ���Ǿ��� ��� ��ü�� �ݾƾ� �Ѵ�(Connection, PreparedStatement)
			//�ݴ� ����(���ʰ� ���� �ݴ´�) : Connection(�빮), PreparedStatement(�湮)
			if(pstmt != null){  //������ ���� �ݴ´�
				try{
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
					System.out.println("close ����");
				}
			}
			if(con!= null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
					System.out.println("close ����");
				}
			}

		}
	}
}
