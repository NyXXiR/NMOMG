package com.study.springboot.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.study.springboot.vo.Board;
import com.study.springboot.vo.BoardList;
import com.study.springboot.vo.Comment;
import com.study.springboot.vo.Stack;

@Mapper
public interface BoardDao {
  public int boardWrite(Board board);

  public Board boardDetail(int boardNum);

  public List<Board> boardList();

  public List<Board> boardList(String Category);

  public List<Board> boardListReverse();

  public List<Board> boardListReverse(String Category);
  
  public List<Board> boardSearch
	( @Param("search") String search, 
			@Param("type") String type);

  public List<Board>titleSearch
  ( @Param("search") String search, 
			@Param("type") String type);
  
  public int boardEdit(Board board);

  public int boardDel(int boardNum);

  public Board selectByNum(int boardNum);

  public int max();

  public int stackWrite(Stack stack);

  public String[] stackList(int boardNum);

  public int commentWrite(Comment comment);

  public List<Comment> commentList(int boardNum);
  

}
