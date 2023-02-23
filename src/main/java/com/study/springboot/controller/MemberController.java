package com.study.springboot.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.springboot.dao.MemberDao;
import com.study.springboot.service.MemberService;
import com.study.springboot.vo.Member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
/* @RequestMapping("/member/*") */
public class MemberController {

	MemberDao memberDao;

	@Autowired
	public MemberController(MemberDao memberDao) {
		super();
		this.memberDao = memberDao;
	}
	
	
	@Autowired
	MemberService memberSer;

//
	@PostMapping("/home")
	@ResponseBody
	public HashMap censoringId(Member member, String loginIdCheck) {
		System.out.println("controller"+member);

		//서비스로 변수를 주고 반환값을 받아옴
		HashMap<String, String> join = memberSer.censorId(member, loginIdCheck);
		return join;
	}
	


	//로그인 포스트 맵핑
	@PostMapping("/member/login")
	public String login(Member member, Model model, HttpSession session) {
		//로그인시 검열기능 i=1이면 정상 로그인
		int i = memberDao.memberLogin(member);
	
		//정상 로그인 정보
		if (i == 1) {
			//세션에 저장할 memberNum & loginId
			int memberNum = memberDao.memberNum(member);
			session.setAttribute("memberNum", memberNum);
			session.setAttribute("loginId", member.getLoginId());
			
			String myNickname = (String)session.getAttribute("loginId");
			String memberNickname = memberSer.memberNickname(myNickname);
			
			System.out.println("가져온 닉네임아이디 "+memberNickname);
			model.addAttribute("nickname",memberNickname);
			return "member/login-after";
			
		} else {
			model.addAttribute("loginFail", "잘못된 정보입니다.");
			return "member/login-after";
		}
	}
	


}
