package com.study.springboot.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {

  private int memberNum;
  private String loginId;
  private String password;
  private String nickname;
}
