package com.study.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springboot.dao.MemberDao;
import com.study.springboot.vo.Member;

import jakarta.servlet.http.HttpSession;
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
	
	//회원가입 맵핑
	@PostMapping("/join")
	public String postJoin(Member member, Model model, String loginIdCheck) {
		
		//중복된 아이디 검열
		int checkId = memberDao.memberIdCheck(member.getLoginId());
		log.info(checkId);
		//아이디 중복체크도 같은 맵핑이기 때문에 조건문을 이용하여 아이디 중복체크 및 회원가입 실행 => 중복체크가 사용가능한 아이디이며 이름과 패스워드가 공백이 아니면 가입
		if (checkId == 0 && loginIdCheck.equals("사용가능한 아이디 입니다.") && member.getNickname() != "" && member.getPassword() != "") {
			int res = memberDao.memberInsert(member); //회원가입 정보를 db에 저장
			log.info(checkId+"1");
			return "/member/login-after";
		} else {
			//사용가능한 아이디, 존재하는 아이디 찾기.
			if (checkId != 0 && !loginIdCheck.equals("이미 존재하는 아이디 입니다.")) {
				model.addAttribute("idCheck", "이미 존재하는 아이디 입니다.");
				log.info(checkId+"2");
				return "/member/joinForm";
			} else if (checkId == 0 && !loginIdCheck.equals("사용가능한 아이디 입니다.")) {
				model.addAttribute("idCheck", "사용가능한 아이디 입니다.");
				log.info(checkId+"3");
				return "/member/joinForm";
			}

			model.addAttribute("idCheck", loginIdCheck);
			log.info(checkId+"4");
			return "/member/login-after";
		}
	}

	//로그인 포스트 맵핑
	@PostMapping("/login")
	public String login(Member member, Model model, HttpSession session) {
		//로그인시 검열기능 i=1이면 정상 로그인
		int i = memberDao.memberLogin(member);
	
		//정상 로그인 정보
		if (i == 1) {
			//세션에 저장할 memberNum
			int memberNum = memberDao.memberNum(member);
			session.setAttribute("memberNum", memberNum);
			return "/member/login-after";
			
		} else {
			model.addAttribute("loginFail", "잘못된 정보입니다.");
			return "/member/main";
		}
	}


}
