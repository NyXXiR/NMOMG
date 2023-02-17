package com.study.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import com.study.springboot.dao.BoardDao;
import com.study.springboot.vo.Comment;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Controller
@RequiredArgsConstructor
@Log4j2
public class CommentController {
  final BoardDao boardDao;


  // 로그인아이디 대신 임시로 아무 memberNum 넣었음(1대신 loginId 입력)

  @PostMapping("/comment/insert")
  public String commentInsert(Comment comment) {
    int res = boardDao.commentWrite(comment);

    return String.format("redirect:/board/detail?boardNum=%s", comment.boardNum);

  }
}
