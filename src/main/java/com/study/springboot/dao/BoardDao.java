package com.study.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.vo.Board;

@Mapper
public interface BoardDao {
	public int boardWrite(Board board);
	
	public Board boardDetail(int boardNum);
	
	public List<Board> boardList();
	
	public int boardEdit(Board board);
	
	public int boardDel(int boardNum);
}
