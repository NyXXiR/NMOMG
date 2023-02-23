package com.study.springboot.controller;

import java.io.File;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:home";
	}


	//로그인 포스트 맵핑
	@PostMapping("/login")
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
	
	@PostMapping("/updateInfo")
	public String updateInfo(Member member, MultipartFile file) {
		String uploadFolder = "/home/ubuntu/nmomg/assets/profile";

	    log.info("upload file name: " + file.getOriginalFilename());
	    log.info("upload file size: " + file.getSize());

	    int max = boardDao.max() + 1;
	    File saveFile = new File(uploadFolder, file.getOriginalFilename());

	    try {
	      uploadFile.transferTo(saveFile);
	      // MultipartFile은 생성하자마자 파일을 바로 업로드하므로 업로드 후 파일명을 변경한다.
	      File renamedFile = new File(uploadFolder, (Integer.toString(max) + ".png"));
	      saveFile.renameTo(renamedFile);
	    } catch (Exception e) {
	      log.error("파일 전송 에러: " + e.getMessage());
	    }
	    // 파일업로드 끝

		return null;
	
	}


}
