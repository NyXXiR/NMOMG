package com.study.springboot.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.study.springboot.dao.BoardDao;
import com.study.springboot.vo.Board;
import com.study.springboot.vo.BoardList;
import com.study.springboot.vo.Comment;
import com.study.springboot.vo.PaginationVo;
import com.study.springboot.vo.Stack;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
@Log4j2

public class BoardController {
  final BoardDao boardDao;



  // 카테고리 입력시 해당 카테고리만 select, 입력 안할시 전체 select함
  @GetMapping("/page")
  public String selectListAndPage(final Model model,
      @RequestParam(value = "page", defaultValue = "1") final int page, String category) {
    List<BoardList> boardList = new ArrayList<>();

    PaginationVo paginationVo = new PaginationVo(this.boardDao.getCount(category), page, category); // 모든
    // 게시글
    paginationVo.setCategory(category);
    List<Board> list = this.boardDao.getListPage(paginationVo);
    Collections.reverse(list);
    for (int i = 0; i < list.size(); i++) {
      String[] stacks = boardDao.stackList(list.get(i).getBoardNum());
      int boardNum1 = list.get(i).getBoardNum();
      String title1 = list.get(i).getTitle();
      String content1 = list.get(i).getContent();
      int memberNum1 = list.get(i).getMemberNum();
      String date1 = list.get(i).getDate();
      String category1 = list.get(i).getCategory();
      String startDate1 = list.get(i).getStartDate();
      String loginId1 = list.get(i).getLoginId();
      int commentCount1 = list.get(i).getCommentCount();

      BoardList boardList1 = new BoardList(boardNum1, title1, content1, memberNum1, date1,
          category1, startDate1, loginId1, commentCount1, stacks);
      boardList.add(boardList1);
    }
    Collections.reverse(boardList);


    log.info(page);
    model.addAttribute("boardList", boardList);
    model.addAttribute("page", page);
    model.addAttribute("pageVo", paginationVo);

    return "board/page";
  }

  @GetMapping("/list")
  public String list(Model model, String category, String search, String type) {
    log.info("----->" + category + "=====search:" + search + ":::type::" + type);
    List<BoardList> boardList = new ArrayList<>();
    List<Board> listReverse = new ArrayList<>();

    if (type == null) {
      type = "";
    }

    if (category != null) {
      listReverse = boardDao.boardListReverse(category);
    } else if (type.equals("S")) {
      listReverse = boardDao.boardSearch(search, type);
    } else if (type.equals("N")) {
      listReverse = boardDao.titleSearch(search, type);
    } else {

      listReverse = boardDao.boardListReverse();
    }

    for (int i = 0; i < listReverse.size(); i++) {
      String[] stacks = boardDao.stackList(listReverse.get(i).getBoardNum());
      int boardNum1 = listReverse.get(i).getBoardNum();
      String title1 = listReverse.get(i).getTitle();
      String content1 = listReverse.get(i).getContent();
      int memberNum1 = listReverse.get(i).getMemberNum();
      String date1 = listReverse.get(i).getDate();
      String category1 = listReverse.get(i).getCategory();
      String startDate1 = listReverse.get(i).getStartDate();
      String loginId1 = listReverse.get(i).getLoginId();
      int commentCount1 = listReverse.get(i).getCommentCount();

      BoardList boardList1 = new BoardList(boardNum1, title1, content1, memberNum1, date1,
          category1, startDate1, loginId1, commentCount1, stacks);
      boardList.add(boardList1);
    }
    model.addAttribute("boardList", boardList);

    return "board/list";

  }

  // 보드 검색
  @PostMapping("/list")
  public String search(Model model, String search, String type) {
    List<Board> list = boardDao.boardSearch(search, type);
    model.addAttribute("boardlist", list);
    return " board/list";
  }

  // board 상세페이지. boardNum으로 각 페이지 구분
  @GetMapping("/detail")
  public String detailList(int boardNum, Model model, HttpSession session) {
    int loginNum = 0;
    Board board = boardDao.selectByNum(boardNum);
    model.addAttribute("detail", board);
    List<Comment> comment = boardDao.commentList(boardNum);
    model.addAttribute("comment", comment);

    if (session.getAttribute("memberNum") != null) {
      loginNum = (int) session.getAttribute("memberNum");

    }



    log.info(loginNum);
    model.addAttribute("loginNum", loginNum);
    return "board/detail";

  }

  // 게시글쓰기 write 페이지
  @GetMapping("/write")
  public void writeform() {}

  @PostMapping("/write") // 데이터를 서버로 제출해서 insert, update
  public String insert(Board board, MultipartFile uploadFile, String[] stack) {

    String uploadFolder = "/home/ubuntu/nmomg/assets/img";

    log.info("upload file name: " + uploadFile.getOriginalFilename());
    log.info("upload file size: " + uploadFile.getSize());

    int max = boardDao.max() + 1;
    File saveFile = new File(uploadFolder, uploadFile.getOriginalFilename());

    try {
      uploadFile.transferTo(saveFile);
      // MultipartFile은 생성하자마자 파일을 바로 업로드하므로 업로드 후 파일명을 변경한다.
      File renamedFile = new File(uploadFolder, (Integer.toString(max) + ".png"));
      saveFile.renameTo(renamedFile);
    } catch (Exception e) {
      log.error("파일 전송 에러: " + e.getMessage());
    }
    // 파일업로드 끝

    // insert 쿼리
    int res = boardDao.boardWrite(board);

    // boardNum을 max로, stack 배열을 하나씩 가져온다
    for (int i = 0; i < stack.length; i++) {
      Stack stack1 = new Stack(max, stack[i]);
      boardDao.stackWrite(stack1);
    }

    return "redirect:list";
  }

  // board 게시물 수정
  @GetMapping("/update")
  public void update(int boardNum, Model model) {
    Board board = boardDao.boardDetail(boardNum);
    model.addAttribute("board", board);

  }

  @PostMapping("/update")
  public String upload(Board board) {
    boardDao.boardEdit(board);
    return "redirect:list";
  }

  @DeleteMapping("/delete")
  public String DeleteData() {

    return "redirect:list";

  }

  // apply part(write, update, list, detail) + (delete(관리자용))

  // 테스트

  @GetMapping("/multiSelect")
  public String MultiSelect() {
    return "board/multiSelect";
  }

}
