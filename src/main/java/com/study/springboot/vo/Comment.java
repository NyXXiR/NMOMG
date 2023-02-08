package com.study.springboot.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Comment {

  public int commentNum;
  public String commentContent;
  public String date;
  public int depth;
  public int memberNum;
  public int boardNum;
}
