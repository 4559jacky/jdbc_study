package com.gn.study.model.dao;

import static com.gn.study.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.gn.study.model.vo.Car;

public class Dao {
	
	public int deleteCarOne(int carNo, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "delete from car where car_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, carNo);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 수정
	public int editCarOne(int carNo, String carModel, int carPrice, String carDate, Connection conn) {
		Statement stmt = null;
		int result = 0;
		try {
			String sql = "";
			if(carModel!=null) {
				if(carPrice!=0) {
					if(carDate!=null) {
						sql = "update car set car_model = '"+carModel
									+"', car_price = "+carPrice
									+", car_date = str_to_date('"+carDate+"', '%Y-%m-%d')"
									+" where car_no = "+carNo;
					} else {
						sql = "update car set car_model = '"+carModel
								+"', car_price = "+carPrice
								+" where car_no = "+carNo;
					}
				} else {
					if(carDate!=null) {
						sql = "update car set car_model = '"+carModel
								+"', car_date = str_to_date('"+carDate+"', '%Y-%m-%d')"
								+" where car_no = "+carNo;
					} else {
						sql = "update car set car_model = '"+carModel
								+"' where car_no = "+carNo;
					}
				}
			} else if(carModel==null) {
				if(carPrice!=0) {
					if(carDate!=null) {
						sql = "update car set car_price = "+carPrice
								+", car_date = str_to_date('"+carDate+"', '%Y-%m-%d')"
								+" where car_no = "+carNo;
					} else {
						sql = "update car set car_price = "+carPrice
								+" where car_no = "+carNo;
					}
				} else {
					if(carDate!=null) {
						sql = "update car set car_date = str_to_date('"+carDate+"', '%Y-%m-%d')"
								+" where car_no = "+carNo;
					} else {
						sql = "";
					}
				}
			}
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) stmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 강사님버전 단일조회
	public List<Car> searchCarList(Connection conn, int option , Object obj) {
		Statement stmt = null;
		ResultSet rs = null;
		List<Car> list = new ArrayList<Car>();
		try {
			String sql = "select * from car where ";
			switch(option) {
				case 1:
					sql = sql + "car_no = " + (Integer)obj; break;
				case 2: 
					sql = sql + "car_model = '" + (String)obj +"'"; break;
				case 3: 
					sql = sql + "car_price = " + (Integer)obj; break;
				case 4: 
					sql = sql + "car_date = '" + (String)obj +"'"; break;				
			}
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while(rs.next()) {
				Car car = new Car();
				car.setCarNo(rs.getInt("car_no"));
				car.setCarModel(rs.getString("car_model"));
				car.setCarPrice(rs.getInt("car_price"));
				if(rs.getDate("car_date") != null) {
					car.setCarDate(sdf.format(rs.getDate("car_date")));
				} else {
					car.setCarDate("null");
				}
				list.add(car);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			try {
				if(stmt != null) stmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	
	public List<Car> selectCarAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Car> list = new ArrayList<Car>();
		try {
			String sql = "select * from car";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while(rs.next()) {
//				Car car = new Car(rs.getInt("car_no"),
//						rs.getString("car_model"),
//						rs.getInt("car_price"),
//						sdf.format(rs.getDate("car_date")));
				// ㄴ 이렇게 하면 NullPoint 오류가 발생한다.
				Car car = new Car();
				car.setCarNo(rs.getInt("car_no"));
				car.setCarModel(rs.getString("car_model"));
				car.setCarPrice(rs.getInt("car_price"));
				if(rs.getDate("car_date") != null) {
					car.setCarDate(sdf.format(rs.getDate("car_date")));
				} else {
					car.setCarDate("null");
				}
				list.add(car);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	// 번호
	public List<Car> selectCarByNo(int modelNo, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Car> list = new ArrayList<Car>();
		try {
			String sql = "select * from car where car_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, modelNo);
			rs = pstmt.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while(rs.next()) {
				Car car = new Car();
				car.setCarNo(rs.getInt("car_no"));
				car.setCarModel(rs.getString("car_model"));
				car.setCarPrice(rs.getInt("car_price"));
				if(rs.getDate("car_date") != null) {
					car.setCarDate(sdf.format(rs.getDate("car_date")));
				} else {
					car.setCarDate("null");
				}
				list.add(car);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	// 모델
	public List<Car> selectCarByModel(String modelName, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Car> list = new ArrayList<Car>();
		try {
			String sql = "select * from car where car_model = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, modelName);
			rs = pstmt.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while(rs.next()) {
				Car car = new Car();
				car.setCarNo(rs.getInt("car_no"));
				car.setCarModel(rs.getString("car_model"));
				car.setCarPrice(rs.getInt("car_price"));
				if(rs.getDate("car_date") != null) {
					car.setCarDate(sdf.format(rs.getDate("car_date")));
				} else {
					car.setCarDate("null");
				}
				list.add(car);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	// 가격
	public List<Car> selectCarByPrice(int modelPrice, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Car> list = new ArrayList<Car>();
		try {
			String sql = "select * from car where car_price = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, modelPrice);
			rs = pstmt.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while(rs.next()) {
				Car car = new Car();
				car.setCarNo(rs.getInt("car_no"));
				car.setCarModel(rs.getString("car_model"));
				car.setCarPrice(rs.getInt("car_price"));
				if(rs.getDate("car_date") != null) {
					car.setCarDate(sdf.format(rs.getDate("car_date")));
				} else {
					car.setCarDate("null");
				}
				list.add(car);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	// 출시일
	public List<Car> selectCarByDate(String modelDate, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Car> list = new ArrayList<Car>();
		try {
			String sql = "select * from car where car_date = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, modelDate);
			rs = pstmt.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while(rs.next()) {
				Car car = new Car();
				car.setCarNo(rs.getInt("car_no"));
				car.setCarModel(rs.getString("car_model"));
				car.setCarPrice(rs.getInt("car_price"));
				if(rs.getDate("car_date") != null) {
					car.setCarDate(sdf.format(rs.getDate("car_date")));
				} else {
					car.setCarDate("null");
				}
				list.add(car);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int insertCarOne(Car car, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "INSERT INTO car(car_model, car_price, car_date) VALUES (?,?,STR_TO_DATE(?, '%Y-%m-%d'))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, car.getCarModel());
			pstmt.setInt(2, car.getCarPrice());
			pstmt.setString(3, car.getCarDate());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
//			try {
//				if(pstmt != null) pstmt.close();
//			} catch(Exception e) {
//				e.printStackTrace();
//			}
		}
		return result;
	}
}
