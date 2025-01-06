package watermelonMusic_project.view;

import java.util.List;
import java.util.Scanner;

import watermelonMusic_project.controller.WmController;
import watermelonMusic_project.model.vo.WmSong;
import watermelonMusic_project.model.vo.WmUser;

public class WmMenu {
	private Scanner sc = new Scanner(System.in);
	private WmController mc = new WmController();
	public void mainMenu() {
		System.out.println("수박노래에 오신걸 환영합니다!");
		// 사용자에게 정보 입력받아서
		// Controller에게 전달
		while(true) {
			System.out.println("=== 회원 관리 프로그램 ===");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("3. 회원 아이디 찾기");
			System.out.println("4. 회원 비밀번호 찾기");
			System.out.println("0. 프로그램 종료");
			
			System.out.print("OPTION : ");
			int menu = sc.nextInt();
			sc.nextLine();
			switch(menu) {
				case 1 : createUser(); break;
				case 2 : login(); break;
//				case 3 : selectMemberId(); break;
//				case 4 : selectMemberName(); break;
				case 0 : System.out.println("잘가용~ 안농~~"); return;
				default : System.out.println("잘못된 번혼디용ㅠㅠ");
			}
		}
	}
	
	// 회원 가입
	public void createUser() {
		System.out.println("=== 회원가입 화면 ===");
		while(true) {
			System.out.print("아이디 : ");
			String id = sc.next();
			System.out.print("비밀번호 : ");
			String pw = sc.next();
			System.out.print("이름 : ");
			String name = sc.next();
			System.out.print("이메일 : ");
			String email = sc.next();
			System.out.print("전화번호(-빼고 입력) : ");
			String phone = sc.next();
			List<WmUser> list = mc.searchUserAll();
			int cnt = 0;
			for(WmUser u : list) {
				if(u.getUser_id().equals(id)) {
					cnt++;
				}
			}
			if(cnt > 0) {
				System.out.println("회원가입 실패 \n"+id+"는 이미 사용중인 아이디입니다. 다른 아이디를 입력해주세요.");
			} else {
				int result = mc.insertMember(id, pw, name, email, phone);
				if(result > 0) {
					System.out.println("회원가입에 성공하였습니다.");
					break;
			}
			}
		}
	}
	
	// 로그인 하기
	public void login() {
		System.out.println("=== 로그인 화면 ===");
		System.out.print("아이디 : ");
		String id = sc.next();
		System.out.print("비밀번호 : ");
		String pw = sc.next();
		
		WmUser user = mc.login(id, pw);
		if(user != null) {
			if(user.getUser_id().equals("admin")) {
				adminMenu(user);
			} else {
				userMenu(user);
			}
		} else {
			System.out.println("아이디 또는 비밀번호를 확인해주세요.");
		}
	}
	
	// 관리자 메뉴
	public void adminMenu(WmUser user) {
		System.out.println(user.getUser_name()+"님, 환영합니다!");
		while(true) {
			System.out.println("=== 관리자 메뉴 ===");
			System.out.println("1. 음악 추가");
//			System.out.println("2. 음악 인기 순위 조회");
			System.out.println("3. 회원 전체 조회");
			System.out.println("4. 회원 아이디 조회");
			System.out.println("5. 회원 강제 탈퇴");
			System.out.println("0. 로그아웃");
			
			System.out.print("OPTION : ");
			int menu = sc.nextInt();
			sc.nextLine();
			switch(menu) {
				case 1 : insertSong(); break;
				case 2 : break;
				case 3 : searchUserAll(); break;
				case 4 : serachUserForId(); break;
				case 5 : deleteUserAdmin(); break;
				case 0 : System.out.println("잘가용~ 안농~~"); return;
				default : System.out.println("잘못된 번혼디용ㅠㅠ");
			}
		}
	}
	
	// 음악 추가
	public void insertSong() {
		System.out.println("=== 음악 추가 ===");
		System.out.print("음악 제목 : ");
		String title = sc.next();
		System.out.print("아티스트 : ");
		String artist = sc.next();
		int result = mc.insertSong(title, artist);
		if(result > 0) {
			System.out.println("음악이 등록되었습니다.");
		} else {
			System.out.println("음악등록에 실패하였습니다.");
		}
	}
	
	
	// 전체 회원 조회
	public void searchUserAll() {
		System.out.println("=== 회원 전체 조회 ===");
		List<WmUser> list = mc.searchUserAll();
		// (1) 만약에 list가 비어있다면 -> 조회된 결과가 없습니다.
		// (2) 목록 출력
		if(list.isEmpty()) {
			System.out.println("조회된 결과가 없습니다.");
		} else {
			for(WmUser u : list) {
				System.out.println(u);
			}
		}
	}
	
	// 회원 아이디로 조회
	public void serachUserForId() {
		System.out.println("=== 회원 아이디로 조회 ===");
		System.out.print("검색할 아이디 : ");
		String id = sc.next();
		WmUser user = mc.serachUserForId(id);
		if(user != null) {
			System.out.println(user);
		} else {
			System.out.println("조회된 결과가 없습니다.");
		}
	}
	
	
	// 회원 강제 탈퇴
	public void deleteUserAdmin() {
		System.out.println("=== 회원 강제 탈퇴 ===");
		System.out.print("탈퇴시킬 회원의 아이디를 입력하세요 : ");
		String userId = sc.next();
		int result = mc.deleteUserAdmin(userId);
		if(result > 0) {
			System.out.println("Id : "+userId+" 회원을 탈퇴시켰습니다.");
			
		} else {
			System.out.println("회원탈퇴에 실패하였습니다.");
		}
	}
	
	
	
	// 사용자 메뉴
	public void userMenu(WmUser user) {
		System.out.println(user.getUser_name()+"님, 환영합니다!");
		while(true) {
			System.out.println("=== 사용자 메뉴 ===");
			System.out.println("1. 플레이 리스트 출력");
			System.out.println("2. 음악 재생");
			System.out.println("3. 회원 정보 수정");
			System.out.println("4. 회원 탈퇴");
			System.out.println("0. 로그아웃");
			
			System.out.print("OPTION : ");
			int menu = sc.nextInt();
			sc.nextLine();
			switch(menu) {
				case 1 : showPlayList(); break;
				case 2 : playSongByNo(); break;
				case 3 : updateUserName(user); break;
				case 4 : deleteUser(user); break;
				case 0 : System.out.println("잘가용~ 안농~~"); return;
				default : System.out.println("잘못된 번혼디용ㅠㅠ");
			}
		}
	}
	
	// 플레이 리스트 출력
	public void showPlayList() {
		System.out.println("=== 전체 플레이 리스트 ===");
		List<WmSong> list = mc.showPlayList();
		// (1) 만약에 list가 비어있다면 -> 조회된 결과가 없습니다.
		// (2) 목록 출력
		if(list.isEmpty()) {
			System.out.println("조회된 결과가 없습니다.");
		} else {
			for(WmSong s : list) {
				System.out.println(s);
			}
		}
	}
	
	// 재생할 음악의 번호 입력받기
	public void playSongByNo() {
		System.out.println("=== 음악 재생하기 ===");
		System.out.print("재생할 음악의 번호를 입력하세요 : ");
		int number = sc.nextInt();
		WmSong song = mc.playSongByNo(number);
		if(song != null) {
			System.out.println(song.getM_artist()+"가 부른 "+ song.getM_title()+"가 재생되었습니다.\n재생횟수 : "+song.getM_repeat());
		} else {
			System.out.println("없는 음번입니다.");
		}
	}
	
	
	
	// 개인 정보 수정(사용자 이름 변경)
	public void updateUserName(WmUser user) {
		sc.nextLine();
		System.out.println("=== 개인 정보 수정 ===");
		while(true) {
			System.out.print("비밀번호를 입력하세요 : ");
			String pass = sc.next();
			if(user.getUser_pw().equals(pass)) {
				System.out.print("변경할 이름을 입력하세요 : ");
				String name = sc.next();
				int result = mc.updateUserName(user, name);
				if(result > 0) {
					System.out.println("회원정보 변경 성공");
				} else {
					System.out.println("회원정보 변경 실패");
				}
			} else {
				System.out.println("비밀번호가 틀렸습니다. 다시 입력해주세요.");
			}
		}
	}
	
	// 회원 탈퇴
	public void deleteUser(WmUser user) {
		sc.nextLine();
		System.out.println("=== 회원 탈퇴 ===");
		while(true) {
			System.out.print("비밀번호를 입력하세요 : ");
			String pass = sc.next();
			if(user.getUser_pw().equals(pass)) {
				int result = mc.deleteUser(user);
				if(result > 0) {
					System.out.println("회원이 탈퇴되었습니다. 그동안 이용해주셔서 감사합니다.");
				} else {
					System.out.println("회원탈퇴에 실패하였습니다.");
				}
			} else {
				System.out.println("비밀번호가 틀렸습니다. 다시 입력해주세요.");
			}
		}
	}
	
	
	
}
