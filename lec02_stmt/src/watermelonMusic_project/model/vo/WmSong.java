package watermelonMusic_project.model.vo;

public class WmSong {
	private int m_no;
	private String m_title;
	private String m_artist;
	private int m_repeat;
	
	public WmSong() {}
	public WmSong(String m_title, String m_artist) {
		this.m_title = m_title;
		this.m_artist = m_artist;
	}
	
	public WmSong(String m_title, String m_artist, int m_repeat) {
		this.m_title = m_title;
		this.m_artist = m_artist;
		this.m_repeat = m_repeat;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getM_title() {
		return m_title;
	}
	public void setM_title(String m_title) {
		this.m_title = m_title;
	}
	public String getM_artist() {
		return m_artist;
	}
	public void setM_artist(String m_artist) {
		this.m_artist = m_artist;
	}
	public int getM_repeat() {
		return m_repeat;
	}
	public void setM_repeat(int m_repeat) {
		this.m_repeat = m_repeat;
	}
	@Override
	public String toString() {
		return "노래차트 [음번 : " + m_no +
				", 곡 제목 : " + m_title +
				", 아티스트 : " + m_artist +
				", 재생횟수 : " + m_repeat + "]";
	}
	
	public String toStringdingdong() {
		return " [제목 : " + m_title +
				"], [아티스트 : " + m_artist +
				"], [재생횟수 : " + m_repeat + "]";
	}
	
	
	
}
