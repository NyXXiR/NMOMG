package com.study.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.vo.Board;
import com.study.springboot.vo.PaginationVo;

@Mapper
public interface MyLogDao {

	public List<Board> myLogList(PaginationVo paginationVo, int memberNum);
	public List<Board> myLogLike(int memberNum);
	public List<Board> myLogComment(int memberNum);
//	public String myLikeId(int memberNum);
	
	public Integer total(int memberNum);
	
}
