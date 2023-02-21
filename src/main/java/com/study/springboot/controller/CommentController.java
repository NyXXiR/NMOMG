package com.study.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.study.springboot.dao.BoardDao;
import com.study.springboot.vo.Comment;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Controller

// 리다이렉트 때문에 requestmapping하지 않음
@Log4j2
@RequiredArgsConstructor
public class CommentController {
  final BoardDao boardDao;

  // 로그인아이디 대신 임시로 아무 memberNum 넣었음(1대신 loginId 입력)
  @PostMapping("/comment/insert")
  public String commentInsert(Comment comment) {
    int res = boardDao.commentWrite(comment);

    return String.format("redirect:/board/detail?boardNum=%s", comment.boardNum);

  }


  @GetMapping("/comment/update")
  public String commentUpdate() {
    return "";
  }

  @PostMapping("/comment/delete")
  public String commentDelete(HttpServletRequest request, int commentNum, int boardNum,
      int memberNum) {
    HttpSession session = request.getSession();

    Integer loginNum = (Integer) session.getAttribute("memberNum");

    if (loginNum != null) {
      if (loginNum == memberNum) {
        int res = boardDao.commentDelete(commentNum);
      }
    }



    return String.format("redirect:/board/detail?boardNum=%s", boardNum);
  }



}
