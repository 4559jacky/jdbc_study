package watermelonMusic_project.model.vo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WmUser {
	private int user_no;
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_email;
	private String user_phone;
	private LocalDateTime regDate;
	
	public WmUser() {}
	
	public WmUser(String user_id, String user_pw, String user_name, String user_email, String user_phone) {
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_phone = user_phone;
	}

	public WmUser(int user_no, String user_id, String user_pw, String user_name, String user_email, String user_phone,
			LocalDateTime regDate) {
		this.user_no = user_no;
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_phone = user_phone;
		this.regDate = regDate;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return "회원 [회원번호 : " + user_no +
				", 아이디 : " + user_id +
				", 패스워드 : " + user_pw +
				", 이름 : " + user_name +
				", 이메일 : " + user_email +
				", 연락처 : " + user_phone +
				", 회원가입 일자 : " + dtf.format(regDate) + "]";
	}
	
	
	
	
	
	
	
	
}
