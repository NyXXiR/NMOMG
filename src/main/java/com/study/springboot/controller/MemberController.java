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


	@GetMapping("/index")
	public void index() {
		
	}
	

	@GetMapping("/loginIdCheck")
	public void loginIdCheck() {
		
	}
	
	
	@GetMapping("/join")
	public void join(Member member) {
		int res = memberDao.memberInsert(member);
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
