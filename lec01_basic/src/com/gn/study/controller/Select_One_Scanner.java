package com.gn.study.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.gn.study.model.vo.Vo;

public class Select_One_Scanner {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// Driver 등록
			Class.forName("org.mariadb.jdbc.Driver");
			// Connection 객체 생성
			String url = "jdbc:mariadb://127.0.0.1:3306/jdbc_basic";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			// StateMent 객체 생성
			stmt = conn.createStatement();
			// 결과 전달받기(ResultSet)
			String sql = "SELECT t_no ,t_name ,t_date\r\n"
					+ " FROM test\r\n"
					+ " WHERE t_no =";
			System.out.println("조회하고자 하는 행의 번호를 입력하세요");
			System.out.print("번호 : ");
			String number = sc.nextLine();
			rs = stmt.executeQuery(sql+number);
			// 데이터 추출
			Vo v = new Vo();
			while(rs.next()) {
				v.setTestNo(rs.getInt("t_no"));
				v.setTestName(rs.getString("t_name"));
				v.setTestDate(rs.getTimestamp("t_date").toLocalDateTime());
			}
			System.out.println(v);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
