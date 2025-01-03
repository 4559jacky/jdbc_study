package com.gn.study.view;

import java.util.List;
import java.util.Scanner;

import com.gn.study.controller.MemberController;
import com.gn.study.model.vo.Member;

public class MemberMenu {
	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	// View : 사용자와의 상호작용
	public void mainMenu() {
		System.out.println("환영합니다!!");
		// 사용자에게 정보 입력받아서
		// Controller에게 전달
		while(true) {
			System.out.println("=== 회원 관리 프로그램 ===");
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 아이디 검색");
			System.out.println("4. 회원 이름으로 키워드 검색");
			System.out.println("5. 회원 정보 수정");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			
			System.out.println("메뉴 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			switch(menu) {
				case 1 : createMember(); break;
				case 2 : selectMemberAll(); break;
				case 3 : selectMemberId(); break;
				case 4 : selectMemberName(); break;
				case 5 : updateMemberInfo(); break;
				case 6 : break;
				case 0 : System.out.println("잘가용~ 안농~~"); return;
				default : System.out.println("잘못된 번혼디용ㅠㅠ");
			}
		}
	}
	
	// 회원 정보 수정
	public void updateMemberInfo() {
		System.out.println("=== 회원 정보 수정 ===");
		System.out.print("아이디 : ");
		String memberId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		boolean result = mc.CheckMember(memberId, memberPw);
		if(result == true) {
			while(true) {
				System.out.println("=== 회원 정보 수정 ===");
				System.out.println("1. 아이디 변경");
				System.out.println("2. 비밀번호 변경");
				System.out.println("3. 회원 이름 수정");
				System.out.println("4. 이메일 변경");
				System.out.println("5. 전화번호 수정");
				System.out.println("0. 회원정보 수정 종료하기");
				
				System.out.println("수정할 항목 : ");
				int menu = sc.nextInt();
				switch(menu) {
					case 1 : updateMemberId(memberId); break;
					case 2 : updateMemberPw(memberId); break;
					case 3 : updateMemberName(memberId); break;
					case 4 : updateMemberEmail(memberId); break;
					case 5 : updateMemberPhone(memberId); break;
					case 0 : System.out.println("잘가용~ 안농~~"); return;
					default : System.out.println("잘못된 번혼디용ㅠㅠ");
				}
			}
		} else {
			System.out.println("아이디 또는 비밀번호를 확인해주세요.");
		}
	}
	
	// 회원 아이디 수정
	public void updateMemberId(String oldMemberId) {
		System.out.println("=== 회원 아이디 수정 ===");
		System.out.print("수정할 아이디 : ");
		String NewMemberId = sc.next();
		int result = mc.updateMemberId(oldMemberId, NewMemberId);
		if(result>0) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
	}
	// 회원 비밀번호 수정
	public void updateMemberPw(String memberId) {
		System.out.println("=== 회원 비밀번호 수정 ===");
		System.out.print("수정할 비밀번호 : ");
		String memberPw = sc.next();
		int result = mc.updateMemberPw(memberId, memberPw);
		if(result>0) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
	}
	// 회원 이름 수정
	public void updateMemberName(String memberId) {
		System.out.println("=== 회원 이름 수정 ===");
		System.out.print("수정할 이름 : ");
		String memberName = sc.next();
		int result = mc.updateMemberName(memberId, memberName);
		if(result>0) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
	}
	// 회원 이메일 수정
	public void updateMemberEmail(String memberId) {
		System.out.println("=== 회원 이메일 수정 ===");
		System.out.print("수정할 이메일 : ");
		String memberEmail = sc.next();
		int result = mc.updateMemberEmail(memberId, memberEmail);
		if(result>0) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
	}
	// 회원 전화번호 수정
	public void updateMemberPhone(String memberId) {
		System.out.println("=== 회원 전화번호 수정 ===");
		System.out.print("수정할 전화번호 : ");
		String memberPhone = sc.next();
		int result = mc.updateMemberPhone(memberId, memberPhone);
		if(result>0) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
	}
	
	// 회원 이름으로 키워드 검색
	public void selectMemberName() {
		System.out.println("=== 회원 이름으로 키워드 검색 ===");
		System.out.print("이름 : ");
		String memberName = sc.nextLine();
		List<Member> list = mc.selectMemberName(memberName);
		if(list.isEmpty()) {
			System.out.println("검색된 이름이 없습니다.");
		} else {
			for(Member m : list) {
				System.out.println(m);
			}
		}
	}
	
	// 회원 아이디 검색
	public void selectMemberId() {
		System.out.println("=== 회원 아이디 검색 ===");
		System.out.print("아이디 : ");
		String memberId = sc.nextLine();
		Member m = mc.selectMemberId(memberId);
		if(m != null) {
			System.out.println(m);
		} else {
			System.out.println("검색하신 아이디는 없는 아이디입니다.");
		}
	}
	
	
	// 전체 회원 조회
	public void selectMemberAll() {
		System.out.println("=== 회원 전체 조회 ===");
		List<Member> list = mc.selectMemberAll();
		// (1) 만약에 list가 비어있다면 -> 조회된 결과가 없습니다.
		// (2) 목록 출력
		if(list.isEmpty()) {
			System.out.println("조회된 결과가 없습니다.");
		} else {
			for(Member m : list) {
				System.out.println(m);
			}
		}
	}
	
	// 회원 추가 화면
	public void createMember() {
		System.out.println("=== 회원 정보 추가 ===");
		System.out.print("아이디 : ");
		String memberId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String memberPw = sc.nextLine();
		System.out.print("이름 : ");
		String memberName = sc.nextLine();
		System.out.print("이메일 : ");
		String memberEmail = sc.nextLine();
		System.out.print("전화번호(-빼고 입력) : ");
		String memberPhone = sc.nextLine();
		System.out.print("성별 : ");
		String memberGender = sc.nextLine();
		
		int result = mc.insertMember(memberId, memberPw, memberName, memberEmail, memberPhone, memberGender);
		if(result > 0) {
			System.out.println("성공!!");
		} else {
			System.out.println("실패ㅠㅠ");
		}
	}
	
	
	
}
