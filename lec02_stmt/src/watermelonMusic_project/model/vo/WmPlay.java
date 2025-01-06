package watermelonMusic_project.model.vo;

public class WmPlay {
	private int play_no;
	private int user_no;
	private int m_no;
	
	public WmPlay() {}
	
	public WmPlay(int user_no, int m_no) {
		this.user_no = user_no;
		this.m_no = m_no;
	}

	public int getPlay_no() {
		return play_no;
	}

	public void setPlay_no(int play_no) {
		this.play_no = play_no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public int getM_no() {
		return m_no;
	}

	public void setM_no(int m_no) {
		this.m_no = m_no;
	}

	@Override
	public String toString() {
		return "플레이리스트 [재생순번 : " + play_no +
				", 사용자 번호 : " + user_no +
				", 음번 : " + m_no + "]";
	}
	
	
	
}
