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
  private int boardNum;
  private String title;
  private String content;
  private int memberNum;
  private String date;
  private String category;
  private String startDate;
  private String loginId;
  private int commentCount;
  private String[] stack;
}
