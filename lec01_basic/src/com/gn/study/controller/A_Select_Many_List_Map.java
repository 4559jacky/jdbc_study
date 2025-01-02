package com.gn.study.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A_Select_Many_List_Map {
	public static void main(String[] args) {
		
		// 7. close를 위해 객체 밖으로 빼기
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// 1. DriverManager
			Class.forName("org.mariadb.jdbc.Driver");
			// 2. Connection
			// DriverManager에 정보를 담아서 연결(통로)
			// DB 연결 정보 : url, 계정명, 비밀번호
			conn = DriverManager.getConnection(
							"jdbc:mariadb://127.0.0.1:3306/jdbc_basic"
							,"scott", "tiger");
			// 3. Statement
			// 지금부터 일한디
			stmt = conn.createStatement();
			// 4. 쿼리 실행 결과 ResultSet에 담기
			rs = stmt.executeQuery("select t_no,t_name,t_date from test");
			// 5. 데이터 추출
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			while(rs.next()) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("t_no", rs.getInt("t_no"));
				map.put("t_name", rs.getString("t_name"));
				map.put("t_date", rs.getTimestamp("t_date"));
				list.add(map);
//				System.out.println(rs.getInt("t_no"));
//				System.out.println(rs.getString("t_name"));
//				System.out.println(rs.getTimestamp("t_date"));
			}
			// 8. 결과 확인
			if(list.isEmpty()) {
				System.out.println("조회된 결과가 없습니다.");
			} else {
				for(Map<String,Object> m : list) {
					System.out.println(m);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
