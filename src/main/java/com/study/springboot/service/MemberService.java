package com.study.springboot.service;

import com.study.springboot.vo.Member;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

public interface MemberService {

    //아이디 중복 체크 및 회원가입
    HashMap<String, String> censorId(Member member, String loginIdCheck);
    
    //닉네임찾기 => 마이페이지에서 사용될것
	String memberNickname(String loginId);
    
}
