package com.gn.study.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gn.study.model.vo.ProjectVo;

public class ProjectDao {
	
	public int deleteProjectOne(int projectNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/company_project";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			String sql = "delete from project where project_id = ? ";
			
			// 자동차 생성 시점에 sql을 넣어준다.
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  projectNo);
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int updateProjectOne(int projectNo, String projectName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/company_project";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			String sql = "Update project set project_name = ? where project_id = ? ";
			
			// 자동차 생성 시점에 sql을 넣어준다.
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  projectName);
			pstmt.setInt(2,  projectNo);
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public int insertProjectOne(String projectName, String managerName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
			
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/company_project";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			String sql = "INSERT INTO project(project_name ,project_manager) "
					+ "VALUES (? ,(SELECT emp_id "
					+ "				FROM employee "
					+ "				WHERE emp_name = ?))";
			
			// 자동차 생성 시점에 sql을 넣어준다.
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  projectName);
			pstmt.setString(2,  managerName);
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public List<ProjectVo> selectProjectAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProjectVo> list = new ArrayList<ProjectVo>();
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/company_project";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			String sql = "SELECT p.project_id "
					+ "		,p.project_name "
					+ "		,p.project_manager "
					+ "		,p.reg_date "
					+ "		,p.mod_date "
					+ "		,IFNULL(concat(e.emp_name,'(',p.project_manager,')'), '미정') as 'emp_name'"
					+ "FROM project p "
					+ "left JOIN employee e "
					+ "ON p.project_manager = e.emp_id";;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProjectVo vo = new ProjectVo();
				vo.setProjectId(rs.getInt("project_id"));
				vo.setProjectName(rs.getString("project_name"));
				vo.setProjectManager(rs.getInt("project_manager"));
				vo.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
				vo.setModDate(rs.getTimestamp("mod_date").toLocalDateTime());
				vo.setManagerName(rs.getString("emp_name"));
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;	
	}
	
	// 담당자 이름으로 검색
	public List<ProjectVo> searchByProjectManagerName(String managerName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProjectVo> list = new ArrayList<ProjectVo>();
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/company_project";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			String sql = "SELECT p.project_id "
					+ "		,p.project_name "
					+ "		,p.project_manager "
					+ "		,p.reg_date "
					+ "		,p.mod_date "
					+ "		,IFNULL(concat(e.emp_name,'(',p.project_manager,')'), '미정') as 'emp_name'"
					+ "FROM project p "
					+ "left JOIN employee e "
					+ "ON p.project_manager = e.emp_id "
					+ "where e.emp_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  managerName);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProjectVo vo = new ProjectVo();
				vo.setProjectId(rs.getInt("project_id"));
				vo.setProjectName(rs.getString("project_name"));
				vo.setProjectManager(rs.getInt("project_manager"));
				vo.setManagerName(rs.getString("emp_name"));
				vo.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
				vo.setModDate(rs.getTimestamp("mod_date").toLocalDateTime());
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;	
	}
	
	// 프로젝트 이름으로 검색
	public List<ProjectVo> selectProjectAllByName(String projectName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProjectVo> list = new ArrayList<ProjectVo>();
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/company_project";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			String sql = "SELECT p.project_id "
					+ "		,p.project_name "
					+ "		,p.project_manager "
					+ "		,p.reg_date "
					+ "		,p.mod_date "
					+ "		,IFNULL(concat(e.emp_name,'(',p.project_manager,')'), '미정') as 'emp_name'"
					+ "FROM project p "
					+ "left JOIN employee e "
					+ "ON p.project_manager = e.emp_id "
					+ "where p.project_name like concat('%',?,'%')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  projectName);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProjectVo vo = new ProjectVo();
				vo.setProjectId(rs.getInt("project_id"));
				vo.setProjectName(rs.getString("project_name"));
				vo.setProjectManager(rs.getInt("project_manager"));
				vo.setManagerName(rs.getString("emp_name"));
				vo.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
				vo.setModDate(rs.getTimestamp("mod_date").toLocalDateTime());
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;	
	}
	
	
}
