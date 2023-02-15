package com.study.springboot.controller;

import java.io.File;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.study.springboot.dao.BoardDao;
import com.study.springboot.vo.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
@Log4j2
public class BoardController {
  final BoardDao boardDao;


  // 카테고리 입력시 해당 카테고리만 select, 입력 안할시 전체 select함
  @GetMapping("/list")
  public String list(Model model, String category) {
    List<Board> list = boardDao.boardList(category);
    model.addAttribute("list", list);
    return "board/list";

  }

  // board 상세페이지
  @GetMapping("/detail")
  public String detailList(int boardNum, Model model) {
    Board board = boardDao.selectByNum(boardNum);
    model.addAttribute("detail", board);
    return "board/detail";
  }

  // 게시글쓰기 write 페이지
  @GetMapping("/write")
  public void writeform() {}


  @PostMapping("/write") // 데이터를 서버로 제출해서 insert, update
  public String insert(Board board, MultipartFile uploadFile) {

    String uploadFolder = "C:\\test";

    log.info("upload file name: " + uploadFile.getOriginalFilename());
    log.info("upload file size: " + uploadFile.getSize());

    File saveFile = new File(uploadFolder, uploadFile.getOriginalFilename());
    int max = boardDao.max() + 1;

    File renamedFile = new File(uploadFolder, (Integer.toString(max) + ".png"));
    saveFile.renameTo(renamedFile);
    try {
      uploadFile.transferTo(saveFile);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    int res = boardDao.boardWrite(board);
    // stackDB 트랜잭션 추가 필요
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
