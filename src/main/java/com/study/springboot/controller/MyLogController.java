package com.study.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.study.springboot.dao.MyLogDao;
import com.study.springboot.vo.Board;

import jakarta.servlet.http.HttpSession;
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
	
	@GetMapping("/myLog/myWrite")
	public String myWrite(Model model, HttpSession session) {
		int memberNum = (int)session.getAttribute("memberNum");
		log.info("세션 memberNum"+memberNum);
		List<Board> list = myLogDao.myLogList(memberNum);
		model.addAttribute("list",list);
		
		
		log.info("ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ"+list.get(0).getTitle());
		
		return "/myLog/myWrite";
//		return "/member/login-after";
	}
	
	@GetMapping("/myLog/myLike")
	public String myLike(Model model, HttpSession session) {
		int memberNum = (int)session.getAttribute("memberNum");
		log.info("세션 memberNum"+memberNum);
		List<Board> list = myLogDao.myLogLike(memberNum);
		model.addAttribute("list",list);
		
		log.info("++++++++++++++++++++++++"+list.get(0).loginId);
//		String myLikeId = myLogDao.myLikeId(memberNum);
//		model.addAttribute("myLikeId",myLikeId);
		
		
		//log.info("ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ"+list.get(0).getTitle());
		
		return "/myLog/myLike";
//		return "/member/login-after";
	}

	
	// 내가 댓글 단 글
		@GetMapping("/myLog/myComment")
		public String myComment(Model model, HttpSession session) {
			int memberNum = (int)session.getAttribute("memberNum");
			log.info("세션 memberNum"+memberNum);
			List<Board> list = myLogDao.myLogComment(memberNum);
			model.addAttribute("list",list);
			log.info("++++++++++++++++++++++++"+list.get(0).loginId);
			
			return "/myLog/myComment";
		}
}
