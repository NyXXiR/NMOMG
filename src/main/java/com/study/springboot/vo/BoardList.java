package com.study.springboot.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class BoardList {
  public int boardNum;
  public String title;
  public String content;
  public int memberNum;
  public String date;
  public String category;
  public String startDate;
  public String loginId;
  private String[] stack;
}
