package com.study.springboot.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.study.springboot.vo.Board;
import com.study.springboot.vo.Stack;

@Mapper
public interface BoardDao {
  public int boardWrite(Board board);

  public Board boardDetail(int boardNum);

  public List<Board> boardList();

  public List<Board> boardList(String Category);

  public int boardEdit(Board board);

  public int boardDel(int boardNum);

  public Board selectByNum(int boardNum);

  public int max();

  public int stackWrite(Stack stack);
}
