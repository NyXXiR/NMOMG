package com.study.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.vo.Member;

@Mapper
public interface MemberDao {
	public int memberInsert(Member member);
	
	public Member memberInfo(int memberNum);
	
	public int memberIdCheck(String memberId);
	
	//로그인시 로그인 정보 검사
	public int memberLogin(Member member);
	
	//로그인시 세션에 저장할 memberNum 찾기
	public int memberNum(Member member);
}
