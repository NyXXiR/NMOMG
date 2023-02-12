package com.study.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springboot.dao.BoardDao;
import com.study.springboot.vo.Board;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/board/*")
public class BoardController {
	BoardDao boardDao;

	public BoardController(BoardDao BoardDao) {
		super();
		this.boardDao = BoardDao;
	}
	
	//board list
	@Autowired(required=false)
	Board board;
	@GetMapping(value = "list")
	public void list(Model model) {
		System.out.println("=================>> 여기1");
		List<Board> list = boardDao.boardList();
		System.out.println("=================>> 여기2");
		System.out.println("================>> list :: " + list);
		model.addAttribute("list",list);
	}

	//board 상세페이지 
	@GetMapping("/detail")
	public void detailList(int boardNum,Model model) {
		List<Board>detail = (List<Board>) boardDao.boardDetail(boardNum);
		model.addAttribute("detail", detail);
	}
	
	//board update 
	@GetMapping("/update")
	public void update(int boardNum, Model model) {
		Board board = boardDao.boardDetail(boardNum);
		model.addAttribute("board", board);
		
	}
/*
	@PostMapping("/update")
	public String upload(Board board) {
		boardDao.boardEdit(board);
		return "redirect:/board/list";
	}
*/
}
