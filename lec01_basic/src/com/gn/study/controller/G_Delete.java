package com.gn.study.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class G_Delete {
	public static void main(String[] args) {
		// 1. 작업용 객체 선언
		Connection conn = null;
		Statement stmt = null;
		
		// 2. try~catch~finally
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/jdbc_basic";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,id,pw);
			
			stmt = conn.createStatement();
			
			String sql = "delete from test "
					+"where t_no = 2";
			int result = stmt.executeUpdate(sql);
			if(result>0) {
				System.out.println("정상적으로 삭제되었습니다.");
			} else {
				System.out.println("삭제 중 오류가 발생하였습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
