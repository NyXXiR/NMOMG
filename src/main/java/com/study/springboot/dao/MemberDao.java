package com.study.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.vo.Member;

@Mapper
public interface MemberDao {
	public int memberInsert(Member member);
	
	public Member memberInfo(int memberNum);
	
	public int memberIdCheck(String memberId);
	
	public int memberLogin(Member member);
}
