package com.study.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.springboot.dao.MyLogDao;
import com.study.springboot.vo.Board;
import com.study.springboot.vo.PaginationVo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MyLogController {
	private final MyLogDao myLogDao;
	
	
	@GetMapping("/member/login-after")
	public String list(Model model,int memberNum) {
		//List<Board> list = myLogDao.myLogList(memberNum);
		//model.addAttribute("list",list);

		//log.info("ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ"+list.get(0).getTitle());
		
		return "/member/login-after";
	}
	
	// 내가 작성한 게시 글
//	@GetMapping("/myLog/myWrite")
//	public String myWrite(Model model, HttpSession session) {
//		int memberNum = (int)session.getAttribute("memberNum");
//		log.info("세션 memberNum"+memberNum);
//		List<Board> list = myLogDao.myLogList(memberNum);
//		model.addAttribute("list",list);
//		
//		log.info("ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ"+list.get(0).getTitle());
//		model.addAttribute("myLog", "작성한 게시글");
//		return "/myLog/myLog";
//	}
	
	@GetMapping("/myLog/myLike")
	public String myLike(Model model, HttpSession session) {
		int memberNum = (int)session.getAttribute("memberNum");
		log.info("세션 memberNum"+memberNum);
		List<Board> list = myLogDao.myLogLike(memberNum);
		model.addAttribute("list",list);
		
		log.info("++++++++++++++++++++++++"+list.get(0).loginId);
		
		model.addAttribute("myLog", "찜한 게시글");
		return "/myLog/myLog";
	}

	
	// 내가 댓글 단 글
		@GetMapping("/myLog/myComment")
		public String myComment(Model model, HttpSession session) {
			int memberNum = (int)session.getAttribute("memberNum");
			log.info("세션 memberNum"+memberNum);
			List<Board> list = myLogDao.myLogComment(memberNum);
			model.addAttribute("list",list);
			log.info("++++++++++++++++++++++++"+list.get(0).loginId);
			model.addAttribute("myLog", "댓글 단 게시글");
			return "/myLog/myLog";
		}
		
		
		
		
		@GetMapping("/myLog/myWrite")
		public String selectListAndPage(HttpSession session, final Model model, @RequestParam(value = "page", defaultValue = "1") final int page) {
			
			int memberNum = (int)session.getAttribute("memberNum");
			log.info("세션 memberNum"+memberNum);
			log.info("세션 memberNum 적용 개수"+myLogDao.total(memberNum));
		    PaginationVo paginationVo = new PaginationVo(myLogDao.total(memberNum), page); // 모든 게시글 개수 구하기.

		    List<Board> list = myLogDao.myLogList(paginationVo,memberNum);

		    model.addAttribute("boardList", list);
		    model.addAttribute("page", page);
		    model.addAttribute("pageVo", paginationVo);
		    

		    return "/myLog/myLog";
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
