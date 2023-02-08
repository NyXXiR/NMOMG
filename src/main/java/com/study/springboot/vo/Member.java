package com.study.springboot.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {

  public int memberNum;
  public String loginId;
  public String password;
  public String nickname;
}
