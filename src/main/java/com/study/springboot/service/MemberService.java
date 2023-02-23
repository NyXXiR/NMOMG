package com.study.springboot.service;

import com.study.springboot.vo.Member;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

public interface MemberService {

    //아이디 중복 체크 및 회원가입
    HashMap<String, String> censorId(Member member, String loginIdCheck);
}
