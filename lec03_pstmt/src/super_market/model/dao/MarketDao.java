package super_market.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import super_market.model.vo.Buy;
import super_market.model.vo.Product;
import super_market.model.vo.User;

public class MarketDao {
	
	// 제품 등록
	public int insertProduct(String prodName, int prodPrice, int prodAmount) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,id,pw);
			String sql = "INSERT INTO sm_product(prod_name ,prod_price ,prod_amount) "
					+ " VALUES (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, prodName);
			pstmt.setInt(2, prodPrice);
			pstmt.setInt(3, prodAmount);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 제품 입고
	public int addProduct(int prodNo, int prodAmount) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println(prodNo+", "+prodAmount);
		int result = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,id,pw);
			String sql = "Update sm_product set prod_amount = prod_amount + ? where prod_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prodAmount);
			pstmt.setInt(2, prodNo);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 판매 현황
	public List<Buy> salesStatus() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Buy> list = new ArrayList<Buy>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,id,pw);
			String sql = "SELECT u.user_nickname "
					+ "		,p.prod_name "
					+ "		,b.buy_amount "
					+ "FROM sm_buy b "
					+ "JOIN sm_user u "
					+ "ON u.user_no = b.user_no "
					+ "JOIN sm_product p "
					+ "ON p.prod_no = b.prod_no;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Buy b = new Buy();
				b.setUser_nickname(rs.getString("u.user_nickname"));
				b.setProd_name(rs.getString("p.prod_name"));
				b.setBuy_amount(rs.getInt("b.buy_amount"));
				list.add(b);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	// 전체 상품 정보 출력
	public List<Product> searchProductAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<Product>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,id,pw);
			String sql = "SELECT * from sm_product";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Product p = new Product();
				p.setProd_no(rs.getInt("prod_no"));
				p.setProd_name(rs.getString("prod_name"));
				p.setProd_price(rs.getInt("prod_price"));
				p.setProd_amount(rs.getInt("prod_amount"));
				list.add(p);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	// 제품 구매
	public int buyProduct(User user, int prodNo, int prodAmount) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		int result1 = 0;
		int result2= 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,id,pw);
			
			// 트랜잭션 시작
			conn.setAutoCommit(false);
			
			// 상품 수량 업데이트
			String sql1 = "update sm_product set prod_amount = prod_amount - ? "
					+ "where prod_no = ? ";
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setInt(1, prodAmount);
			pstmt1.setInt(2, prodNo);
			result1 = pstmt1.executeUpdate();
			
			if (result1 == 0) {
			    throw new Exception("재고가 부족합니다.");
			}
			
			// 구매 기록 삽입
			String sql2 = "insert into sm_buy(user_no, prod_no, buy_amount) values (?,?,?)";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, user.getUser_no());
			pstmt2.setInt(2, prodNo);
			pstmt2.setInt(3, prodAmount);
			result2 = pstmt2.executeUpdate();
			
			// 트랜잭션 커밋
			conn.commit();
		}catch(Exception e) {
			e.printStackTrace();
			if(conn != null)
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		} finally {
			try {
				if (pstmt2 != null) pstmt2.close();
		        if (pstmt1 != null) pstmt1.close();
		        if (conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		// 트랜잭션 결과 반환
		return (result1 >= 1) && (result2 >= 1) ? 1 : 0;
	}
	
	
	
	// 회원 가입
	public int insertMember(User u) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,id,pw);
			String sql = "INSERT INTO sm_user(user_id ,user_pw ,user_nickname) "
					+ " VALUES (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getUser_id());
			pstmt.setString(2, u.getUser_pw());
			pstmt.setString(3, u.getUser_nickname());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 로그인
	public User login(String userId, String userPw) {
		User user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,id,pw);
			String sql = "select * from sm_user where user_id = ? and user_pw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  userId);
			pstmt.setString(2,  userPw);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setUser_no(rs.getInt("user_no"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_pw(rs.getString("user_pw"));
				user.setUser_nickname(rs.getString("user_nickname"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	// 전체 회원 조회
	public List<User> searchUserAll() {
		List<User> list = new ArrayList<User>();
		// DB에 SQL문 요청
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,id,pw);
			String sql = "select * from sm_user";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User u = new User();
				u.setUser_no(rs.getInt("user_no"));
				u.setUser_id(rs.getString("user_id"));
				u.setUser_pw(rs.getString("user_pw"));
				u.setUser_nickname(rs.getString("user_nickname"));
				u.setReg_date(rs.getTimestamp("reg_date").toLocalDateTime());
				u.setMod_date(rs.getTimestamp("mod_date").toLocalDateTime());
				list.add(u);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
