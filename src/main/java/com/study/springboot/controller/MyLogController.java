package com.study.springboot.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.study.springboot.dao.MyLogDao;
import com.study.springboot.vo.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MyLogController {
  private final MyLogDao myLogDao;


  @GetMapping("/member/login-after")
  public String list(Model model, HttpSession session) {
    // List<Board> list = myLogDao.myLogList(memberNum);
    // model.addAttribute("list",list);

    // log.info("ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ"+list.get(0).getTitle());

    Integer memberNum = (Integer) session.getAttribute("memberNum");
    model.addAttribute("memberNum", memberNum);
    return "member/login-after";
  }

  // 내가 작성한 글 목록 보기
  @GetMapping("/myLog/myWrite")
  public String selectListWrite(HttpSession session, final Model model,
      @RequestParam(value = "page", defaultValue = "1") final int page) {

    int memberNum = (int) session.getAttribute("memberNum");
    log.info("세션 memberNum" + memberNum);
    log.info("세션 memberNum 적용 개수" + myLogDao.totalWrite(memberNum));
    PaginationVo paginationVo = new PaginationVo(myLogDao.totalWrite(memberNum), page); // 모든 게시글 개수
                                                                                        // 구하기.

    List<Board> list = myLogDao.myLogList(paginationVo, memberNum);

    model.addAttribute("boardList", list);
    model.addAttribute("page", page);
    model.addAttribute("pageVo", paginationVo);

    model.addAttribute("myLog", "작성한 게시글");
    model.addAttribute("pageMapping", "/myLog/myWrite?page=");

    return "myLog/myLog";
  }


  // 찜한 글 목록 보기
  @GetMapping("/myLog/myLike")
  public String selectListLike(HttpSession session, final Model model,
      @RequestParam(value = "page", defaultValue = "1") final int page) {

    int memberNum = (int) session.getAttribute("memberNum");
    log.info("세션 memberNum" + memberNum);
    log.info("세션 memberNum 적용 개수" + myLogDao.totalLike(memberNum));
    PaginationVo paginationVo = new PaginationVo(myLogDao.totalLike(memberNum), page); // 모든 게시글 개수
                                                                                       // 구하기.

    List<Board> list = myLogDao.myLogLike(paginationVo, memberNum);

    model.addAttribute("boardList", list);
    model.addAttribute("page", page);
    model.addAttribute("pageVo", paginationVo);

    model.addAttribute("myLog", "찜한 게시글");
    model.addAttribute("pageMapping", "/myLog/myLike?page=");
    return "myLog/myLog";
  }


  // 댓글 단 글 목록 보기
  @GetMapping("/myLog/myComment")
  public String selectListComment(HttpSession session, final Model model,
      @RequestParam(value = "page", defaultValue = "1") final int page) {

    int memberNum = (int) session.getAttribute("memberNum");
    log.info("세션 memberNum" + memberNum);
    log.info("세션 memberNum 적용 개수" + myLogDao.totalComment(memberNum));
    PaginationVo paginationVo = new PaginationVo(myLogDao.totalComment(memberNum), page); // 모든 게시글
                                                                                          // 개수 구하기.

    List<Board> list = myLogDao.myLogComment(paginationVo, memberNum);

    model.addAttribute("boardList", list);
    model.addAttribute("page", page);
    model.addAttribute("pageVo", paginationVo);

    model.addAttribute("myLog", "댓글 단 게시글");
    model.addAttribute("pageMapping", "/myLog/myComment?page=");
    return "myLog/myLog";
  }



}
