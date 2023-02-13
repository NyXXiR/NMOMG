package com.study.springboot.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Board {

  public int boardNum;
  public String title;
  public String content;
  public int memberNum;
  public String date;
  public String category;
  public String startDate;
  public String loginId;
}
