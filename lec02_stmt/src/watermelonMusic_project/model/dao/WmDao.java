package watermelonMusic_project.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import watermelonMusic_project.model.vo.WmSong;
import watermelonMusic_project.model.vo.WmUser;

public class WmDao {
	// 회원 가입
	public int insertMember(WmUser u) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,id,pw);
			stmt = conn.createStatement();
			String sql = "INSERT INTO wm_user(user_id ,user_pw ,user_name ,user_email ,user_phone) "
					+ " VALUES ('"+u.getUser_id()+"','"+u.getUser_pw()+"','"+u.getUser_name()
					+"','"+u.getUser_email()+"','"+u.getUser_phone()+"')";
			result = stmt.executeUpdate(sql);
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
		return result;
	}

	// 로그인
	public WmUser login(String userId, String userPw) {
		WmUser user = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,id,pw);
			stmt = conn.createStatement();
			String sql = "select * from wm_user where user_id = '"
					+ userId +"' and user_pw = '"+userPw+"'";
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				user = new WmUser();
				user.setUser_no(rs.getInt("user_no"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_pw(rs.getString("user_pw"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_email(rs.getString("user_email"));
				user.setUser_phone(rs.getString("user_phone"));
				user.setRegDate(rs.getTimestamp("regDate").toLocalDateTime());
			}
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
		return user;
	}
	
	// 음악 추가
	public int insertSong(String title, String artist) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,id,pw);
			stmt = conn.createStatement();
			String sql = "INSERT INTO wm_song(m_title ,m_artist) "
					+ " VALUES ('"+title+"','"+artist+"')";
			result = stmt.executeUpdate(sql);
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
		return result;
	}
	
	// 재생횟수 Top10 조회
	public List<WmSong> selectTop10Song() {
		List<WmSong> list = new ArrayList<WmSong>();
		// DB에 SQL문 요청
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,id,pw);
			stmt = conn.createStatement();
			String sql = "SELECT * FROM wm_song "
					+ "	ORDER BY m_repeat DESC "
					+ "	LIMIT 10";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				WmSong s = new WmSong();
				s.setM_no(rs.getInt("m_no"));
				s.setM_title(rs.getString("m_title"));
				s.setM_artist(rs.getString("m_artist"));
				s.setM_repeat(rs.getInt("m_repeat"));
				list.add(s);
			}
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
		return list;
	}
	
	
	// 전체 회원 조회
	public List<WmUser> searchUserAll() {
		List<WmUser> list = new ArrayList<WmUser>();
		// DB에 SQL문 요청
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,id,pw);
			stmt = conn.createStatement();
			String sql = "select * from wm_user";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				WmUser u = new WmUser();
				u.setUser_no(rs.getInt("user_no"));
				u.setUser_id(rs.getString("user_id"));
				u.setUser_pw(rs.getString("user_pw"));
				u.setUser_name(rs.getString("user_name"));
				u.setUser_email(rs.getString("user_email"));
				u.setUser_phone(rs.getString("user_phone"));
				u.setRegDate(rs.getTimestamp("regDate").toLocalDateTime());
				list.add(u);
			}
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
		return list;
	}
	
	// 회원 아이디로 조회
	public WmUser serachUserForId(String memberId) {
		WmUser user = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,id,pw);
			stmt = conn.createStatement();
			String sql = "select * from wm_user where user_id = '"
					+ memberId +"'";
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				user = new WmUser();
				user.setUser_no(rs.getInt("user_no"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_pw(rs.getString("user_pw"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_email(rs.getString("user_email"));
				user.setUser_phone(rs.getString("user_phone"));
				user.setRegDate(rs.getTimestamp("regDate").toLocalDateTime());
			}
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
		return user;
	}
	
	// 회원 강제 탈퇴
	public int deleteUserAdmin(String userId) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,id,pw);
			stmt = conn.createStatement();
			String sql = "delete from wm_user where user_id ='" +userId + "'";
			result = stmt.executeUpdate(sql);
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
		return result;
	}
	
	
	// 플레이 리스트 출력
	public List<WmSong> showPlayList() {
		List<WmSong> list = new ArrayList<WmSong>();
		// DB에 SQL문 요청
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,id,pw);
			stmt = conn.createStatement();
			String sql = "select * from wm_song";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				WmSong s = new WmSong();
				s.setM_no(rs.getInt("m_no"));
				s.setM_title(rs.getString("m_title"));
				s.setM_artist(rs.getString("m_artist"));
				s.setM_repeat(rs.getInt("m_repeat"));
				list.add(s);
			}
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
		return list;
	}
	
	// 재생할 음악의 번호 입력받기
	public WmSong showPlayList(int number) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		WmSong song = null;
		int result = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,id,pw);
			stmt = conn.createStatement();
			String sql = "update wm_song set m_repeat = m_repeat+1 "
					+"where m_no = " + number;
			result = stmt.executeUpdate(sql);
			
			stmt = conn.createStatement();
			String sql1 = "select * from wm_song where m_no = " + number;
			
			rs = stmt.executeQuery(sql1);
			if(rs.next()) {
				song = new WmSong();
				song.setM_no(rs.getInt("m_no"));
				song.setM_title(rs.getString("m_title"));
				song.setM_artist(rs.getString("m_artist"));
				song.setM_repeat(rs.getInt("m_repeat"));
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
		return song;
	}
	
	
	
	// 개인 정보 수정(사용자 이름 변경)
	public int updateUserName(WmUser user, String name) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,id,pw);
			stmt = conn.createStatement();
			String sql = "update wm_user set user_name = '"+name+"' "
					+"where user_id ='" +user.getUser_id() + "'";
			result = stmt.executeUpdate(sql);
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
		return result;
	}
	
	
	// 회원 탈퇴
	public int deleteUser(WmUser user) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,id,pw);
			stmt = conn.createStatement();
			String sql = "delete from wm_user where user_id ='" +user.getUser_id() + "'";
			result = stmt.executeUpdate(sql);
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
		return result;
	}
	
	
	
	
}
