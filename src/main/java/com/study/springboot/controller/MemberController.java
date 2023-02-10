package com.study.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springboot.dao.MemberDao;
import com.study.springboot.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/member/*")
public class MemberController {
	MemberDao memberDao;

	@Autowired
	public MemberController(MemberDao memberDao) {
		super();
		this.memberDao = memberDao;
	}
	

	@GetMapping("/loginIdCheck")
	public void loginIdCheck() {
		
	}
	
	
	@PostMapping("/join")
	public String postJoin(Member member, Model model, String loginIdCheck) {
		int checkId = memberDao.memberIdCheck(member.getLoginId());
		
		if(loginIdCheck.equals("사용가능한 아이디 입니다.") && member.getNickname()!="" && member.getPassword()!="") { 
			if(checkId == 1) {			
				model.addAttribute("idCheck", "이미 존재하는 아이디 입니다.");
				return "/member/joinForm";
			} else if(checkId==0 )  {
				model.addAttribute("idCheck", "사용가능한 아이디 입니다.");
				return "/member/joinForm";
			}
			 int res = memberDao.memberInsert(member); 
			 return "/member/login"; 
			 } else {
		
			if(checkId == 1 && !loginIdCheck.equals("이미 존재하는 아이디 입니다.")) {			
				model.addAttribute("idCheck", "이미 존재하는 아이디 입니다.");
				return "/member/joinForm";
			} else if(checkId==0 && !loginIdCheck.equals("사용가능한 아이디 입니다.") ) {
				model.addAttribute("idCheck", "사용가능한 아이디 입니다.");
				return "/member/joinForm";
			}
	
			model.addAttribute("idCheck", loginIdCheck);
			return "/member/joinForm";
			 }
	}
	
	//아이디 중복체크는 겟 방식이 때문에 
	@GetMapping("/join")
	public String getJoin(Member member, Model model) {
		int checkId = memberDao.memberIdCheck(member.getLoginId());
		if(checkId == 1) {			
			model.addAttribute("idCheck", "이미 존재하는 아이디 입니다.");
			return "/member/joinForm";
		} else if(checkId==0) {
			model.addAttribute("idCheck", "사용가능한 아이디 입니다.");
			return "/member/joinForm";
		}
		return "index.html";
	}
	
	

	@GetMapping("/joinForm")
	public void joinForm() {

	}

	@GetMapping("/login")
	public String login(Member member, Model model) {
		int i = memberDao.memberLogin(member);
		if (i == 1) {
			return "index";
		} else {
			model.addAttribute("loginFail", "잘못된 정보입니다.");
			return "loginForm";
		}

	}
	
	@GetMapping("/loginForm")
	public void loginForm() {
		
		
	}

}
