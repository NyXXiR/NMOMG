package com.study.springboot.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Comment {

  private int commentNum;
  private String commentContent;
  private String date;
  private int depth;
  private int memberNum;
  private int boardNum;
}
