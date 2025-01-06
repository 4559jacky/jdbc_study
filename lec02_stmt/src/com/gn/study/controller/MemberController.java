package com.gn.study.controller;

import java.util.List;

import com.gn.study.model.dao.MemberDao;
import com.gn.study.model.vo.Member;

public class MemberController {
	
	// 회원 탈퇴
	public int deleteMember(String memberId) {
		int result = new MemberDao().deleteMember(memberId);
		return result;
	}
	
	// 회원 아이디와 비밀번호로 정보 조회
	public Member selectMemberOneByIdAndPw(String memberId, String memberPw) {
		Member m = new MemberDao().selectMemberOneByIdAndPw(memberId, memberPw);
		return m;
	}
	
	// 회원 정보 전체 수정
	public int updateMemberInfo(String memberId, String name, String phone, String email) {
		int result = new MemberDao().updateMemberInfo(memberId, name, phone, email);
		return result;
	}
	
	// 회원 아이디 수정
	public int updateMemberId(String oldMemberId, String newMemberId) {
		int result = new MemberDao().updateMemberId(oldMemberId, newMemberId);
		return result;
	}
	
	// 회원 비밀번호 수정
	public int updateMemberPw(String memberId, String memberPw) {
		int result = new MemberDao().updateMemberPw(memberId, memberPw);
		return result;
	}
	
	// 회원 이름 수정
	public int updateMemberName(String memberId, String memberName) {
		int result = new MemberDao().updateMemberName(memberId, memberName);
		return result;
	}
	
	// 회원 이메일 수정
	public int updateMemberEmail(String memberId, String memberEmail) {
		int result = new MemberDao().updateMemberEmail(memberId, memberEmail);
		return result;
	}
	
	// 회원 전화번호 수정
	public int updateMemberPhone(String memberId, String memberPhone) {
		int result = new MemberDao().updateMemberPhone(memberId, memberPhone);
		return result;
	}
	
	
	// 아이디랑 비밀번호로 회원 확인
	public boolean CheckMember(String memberId, String memberPw) {
		boolean result = new MemberDao().CheckMember(memberId, memberPw);
		return result;
	}
	
	public List<Member> selectMemberName(String memberName) {
		List<Member> list = new MemberDao().selectMemberName(memberName);
		return list;
	}
	
	public Member selectMemberId(String memberId) {
//		Member m = new MemberDao().selectMemberId(memberId);
//		return m;
		return new MemberDao().selectMemberId(memberId);
	}
	
	public List<Member> selectMemberAll() {
		List<Member> list = new MemberDao().selectMemberAll();
		return list;
	}
	
	public int insertMember(String memberId, String memberPw,
			String memberName, String memberEmail,
			String memberPhone, String memberGender) {
		Member m = new Member(memberId, memberPw, memberName, memberEmail, memberPhone, memberGender);
		int result = new MemberDao().insertMember(m);
		return result;
	}
}
								