package com.study.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.vo.Board;

@Mapper
public interface MyLogDao {
	public List<Board> myLogList(int memberNum);
}
