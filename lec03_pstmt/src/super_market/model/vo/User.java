package super_market.model.vo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User {
	private int user_no;
	private String user_id;
	private String user_pw;
	private String user_nickname;
	private LocalDateTime reg_date;
	private LocalDateTime mod_date;
	
	public User() {
		
	}
	
	public User(String user_id, String user_pw, String user_nickname) {
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_nickname = user_nickname;

	}

	public User(int user_no, String user_id, String user_pw, String user_nickname, LocalDateTime reg_date,
			LocalDateTime mod_date) {
		this.user_no = user_no;
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_nickname = user_nickname;
		this.reg_date = reg_date;
		this.mod_date = mod_date;
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

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public LocalDateTime getReg_date() {
		return reg_date;
	}

	public void setReg_date(LocalDateTime reg_date) {
		this.reg_date = reg_date;
	}

	public LocalDateTime getMod_date() {
		return mod_date;
	}

	public void setMod_date(LocalDateTime mod_date) {
		this.mod_date = mod_date;
	}

	@Override
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd E요일");
		return "회원정보 [번호 : " + user_no +
				", 아이디 : " + user_id +
				", 패스워드 : " + user_pw +
				", 닉네임 : " + user_nickname +
				", 회원가입 일자 : " + dtf.format(reg_date) +
				", 회원정보 수정 일자 : " + dtf.format(mod_date) + "]";
	}
	

	
}
