package watermelonMusic_project.controller;

import java.util.List;

import watermelonMusic_project.model.dao.WmDao;
import watermelonMusic_project.model.vo.WmSong;
import watermelonMusic_project.model.vo.WmUser;

public class WmController {
	
	WmDao wd = new WmDao();
	
	// 회원가입
	public int insertMember(String id, String pw, String name,
			 				String email, String phone) {
		WmUser u = new WmUser(id, pw, name, email, phone);
		int result = wd.insertMember(u);
		return result;
	}
	
	// 로그인
	public WmUser login(String id, String pw) {
		WmUser user = wd.login(id, pw);
		return user;
	}
	
	// 음악 추가
	public int insertSong(String title, String artist) {
		int result = wd.insertSong(title, artist);
		return result;
	}
	
	// 재생횟수순 탑10 노래
	public List<WmSong> selectTop10Song() {
		List<WmSong> list = wd.selectTop10Song();
		return list;
	}
	
	
	// 전체 회원 조회
	public List<WmUser> searchUserAll() {
		List<WmUser> list = wd.searchUserAll();
		return list;
	}
	
	// 회원 아이디로 조회
	public WmUser serachUserForId(String memberId) {
		return wd.serachUserForId(memberId);
	}
	
	// 회원 강제 탈퇴
	public int deleteUserAdmin(String userId) {
		return wd.deleteUserAdmin(userId);
	}
	
	
	// 플레이 리스트 출력
	public List<WmSong> showPlayList() {
		List<WmSong> list = wd.showPlayList();
		return list;
	}
	
	
	// 재생할 음악의 번호 입력받기
	public WmSong playSongByNo(int number) {
		WmSong list = wd.showPlayList(number);
		return list;
	}
	
	
	// 개인 정보 수정(사용자 이름 변경)
	public int updateUserName(WmUser user, String name) {
		int result = wd.updateUserName(user, name);
		return result;
	}
	
	// 회원탈퇴
	public int deleteUser(WmUser user) {
		int result = wd.deleteUser(user);
		return result;
	}
	
}
