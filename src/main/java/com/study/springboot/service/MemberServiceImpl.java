package com.study.springboot.service;


import com.study.springboot.dao.MemberDao;
import com.study.springboot.vo.Member;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    MemberDao memberDao;

    @Autowired
    public MemberServiceImpl(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    @Override
    public HashMap<String, String> censorId(Member member, String loginIdCheck) {
        //반환 해줄 데이터 형식
        HashMap<String, String> censorship = new HashMap<>();

        //중복된 아이디가 존재하는지 체크
        int checkId = memberDao.memberIdCheck(loginIdCheck);

        //화원가입할 정보 db에 삽입
        if(member.getLoginId() !=null && member.getNickname() !=null && member.getPassword()!=null) {
            int joinInfo = memberDao.memberInsert(member);
        }

        //조건문으로 아이디 중복 검열기능
        if (checkId == 0 && loginIdCheck != null && loginIdCheck !="") {
            censorship.put("idCheck", "사용가능한 아이디 입니다.");
            censorship.put("loginIdCheck", loginIdCheck);
            return censorship;
        } else {
            censorship.put("idCheck", "이미 존재하는 아이디 입니다.");
            return censorship;
        }
    }

    //닉네임 찾기
	@Override
	public String memberNickname(String loginId) {
		System.out.println("impl로 날라오 로그인된 아이디 "+loginId);
		String myNickname = memberDao.memberNickname(loginId);
		return myNickname;
	}

}
