package com.gn.study.model.service;

import static com.gn.study.common.JDBCTemplate.close;
import static com.gn.study.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.gn.study.model.dao.Dao;
import com.gn.study.model.vo.Car;

// DB에 접속 -> Connection 객체 생성
public class Service {
	private Dao dao = new Dao();
	
	public List<Car> selectCarAll() {
		Connection conn = getConnection();
		List<Car> list = dao.selectCarAll(conn);
		close(conn);
		return list;
	}
	
	// 번호
	public List<Car> selectCarByNo(int modelNo) {
		Connection conn = getConnection();
		List<Car> car = dao.selectCarByNo(modelNo, conn);
		close(conn);
		return car;
	}
	// 모델
	public List<Car> selectCarByModel(String modelName) {
		Connection conn = getConnection();
		List<Car> car = dao.selectCarByModel(modelName, conn);
		close(conn);
		return car;
	}
	// 가격
	public List<Car> selectCarByPrice(int modelPrice) {
		Connection conn = getConnection();
		List<Car> car = dao.selectCarByPrice(modelPrice, conn);
		close(conn);
		return car;
	}
	// 출시일
	public List<Car> selectCarByDate(String modelDate) {
		Connection conn = getConnection();
		List<Car> car = dao.selectCarByDate(modelDate, conn);
		close(conn);
		return car;
	}
	
	
	public int insertCarOne(Car car) {
		Connection conn = getConnection();
		int result = dao.insertCarOne(car, conn);
		close(conn);
		return result;
//		Connection conn = null;
//		int result = 0;
//		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//			String url = "jdbc:mariadb://127.0.0.1:3306/jdbc_basic";
//			String user = "scott";
//			String pw = "tiger";
//			conn = DriverManager.getConnection(url,user,pw);
//			result = dao.insertCarOne(car, conn);
//		} catch(Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(conn != null) conn.close();
//			} catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
	}
	
	
}
