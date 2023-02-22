package com.study.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.springboot.vo.Board;
import com.study.springboot.vo.PaginationVo;

@Mapper
public interface MyLogDao {

	public List<Board> myLogList(@Param("paginationVo")PaginationVo paginationVo, @Param("memberNum") int memberNum);
	public List<Board> myLogLike(int memberNum);
	public List<Board> myLogComment(int memberNum);
//	public String myLikeId(int memberNum);
	
	public int total(int memberNum);
	
}
