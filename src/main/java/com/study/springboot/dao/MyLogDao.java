package com.study.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.springboot.vo.Board;
import com.study.springboot.vo.PaginationVo;

@Mapper
public interface MyLogDao {

	public List<Board> myLogList(@Param("paginationVo")PaginationVo paginationVo, @Param("memberNum") int memberNum);
	public List<Board> myLogLike(@Param("paginationVo")PaginationVo paginationVo, @Param("memberNum") int memberNum);
	public List<Board> myLogComment(@Param("paginationVo")PaginationVo paginationVo, @Param("memberNum") int memberNum);
//	public String myLikeId(int memberNum);
	
	public int totalWrite(int memberNum);
	public int totalLike(int memberNum);
	public int totalComment(int memberNum);
	
}
