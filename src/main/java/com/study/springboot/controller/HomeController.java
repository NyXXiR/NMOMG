package com.study.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.study.springboot.dao.*;
import com.study.springboot.vo.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
// 일단 page를 홈으로 삼고 리다이렉트한다(게시판이 많이 없으므로)
public class HomeController {
  final BoardDao boardDao;
  final MemberDao memberDao;

  @GetMapping("/")
  public String index(HttpSession session) {
    log.info("_____" + session.getAttribute("memberNum"));


    return "redirect:board/page";
  }



  @GetMapping("/home")
  public String home(HttpSession session) {
    if (session.getAttribute("memberNum") != null) {
      return "member/login-after";
    } else {
      return "home_backup2";
    }
  }

  // id와 pw를 입력받고 일치여부를 확인한다. 일치할 경우 memberNum을 반환해준다.


  @GetMapping("/login")
  public String login(Member member, Model model) {

    // id와 password가 일치하는 row가 있는지 체크. 값이 1이라면 로그인 진행, 없으면 alert 띄우고 로그인 화면으로 돌려보냄.

    int memberNum = memberDao.memberNum(member);
    Member selectedMember = memberDao.memberInfo(memberNum);

    model.addAttribute("member", selectedMember);
    model.addAttribute("memberNum", memberNum);

    return "redirect:board/page";
  }



}


