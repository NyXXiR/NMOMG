package com.study.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.study.springboot.dao.MyLogDao;
import com.study.springboot.vo.Board;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MyLogController {
	private final MyLogDao myLogDao;
	
	// 내가 작성한 게시 글
	@GetMapping("/member/login-after")
	public String list(Model model,int memberNum) {
		List<Board> list = myLogDao.myLogList(memberNum);
		model.addAttribute("list",list);
		
		
		log.info("ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ"+list.get(0).getTitle());
		
		return "/member/login-after";
//		return "/member/login-after";
	}
	
	@GetMapping("/MyLog/myWrite")
	public String myWrite(Model model,int memberNum) {
		List<Board> list = myLogDao.myLogList(memberNum);
		model.addAttribute("list",list);
		
		
		log.info("ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ"+list.get(0).getTitle());
		
		return "/MyLog/myWrite";
//		return "/member/login-after";
	}

}
