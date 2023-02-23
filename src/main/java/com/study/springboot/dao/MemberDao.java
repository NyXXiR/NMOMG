package com.study.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.vo.Member;

import jakarta.servlet.http.HttpSession;

@Mapper
public interface MemberDao {
	public int memberInsert(Member member);
	
	//member의 memberNum찾기
	public Member memberInfo(int memberNum);
	
	public int memberIdCheck(String memberId);
	
	//로그인시 로그인 정보 검사
	public int memberLogin(Member member);
	
	//로그인시 세션에 저장할 memberNum 찾기
	public int memberNum(Member member);
	
	//닉네임찾기 => 마이페이지에서 사용
	public String memberNickname(String loginId);
	
	//loginId로 사용자 모든 정보 찾기
	public Member selectAllByMemberNum(int fileNum);
	
}
