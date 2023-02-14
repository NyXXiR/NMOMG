package com.study.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.study.springboot.dao.BoardDao;
import com.study.springboot.vo.Board;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {
	 final BoardDao boardDao;

	@GetMapping("/list")
	public String list(Model model) {
		System.out.println("=================>> 여기1");
		List<Board> list = boardDao.boardList();
		System.out.println("=================>> 여기2");
		System.out.println("================>> list :: " + list);
		model.addAttribute("list",list);
		return "board/list";
	}

	//board 상세페이지 
	@GetMapping("/detail")
	public void detailList(int boardNum, Model model) {
		Board board = boardDao.boardDetail(boardNum);
		model.addAttribute("detail", board);
	}
	
	//게시글쓰기 write 페이지
	@GetMapping("/write")
	public void writeform() {}
		
	
	@PostMapping("/write")	//데이터를 서버로 제출해서 insert, update
	public String insert(Board board) {
		int res = boardDao.boardWrite(board);

		return "redirect:list";
	}
	
	
	//board 게시물 수정
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
	
	
}
