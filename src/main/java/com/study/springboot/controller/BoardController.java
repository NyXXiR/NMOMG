package com.study.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	public BoardController(BoardDao BoardDao) {
		super();
		this.boardDao = BoardDao;
	}
	
//	@GetMapping("/ex2")
//	/*public void ex2(Model model, HttpServletRequest request) {
//		Session session = (Session) request.getSession(); */
//		//session적용
//	public void ex2(Model model) {
//		
//		List<String> strList = IntStream.range(1, 10)
//				.mapToObj(i -> "Data"+i).collect(Collectors.toList());
//		log.info("------");
//		Map<String, String> map =new HashMap<>();
//		map.put("A","AAA");
//		map.put("B", "BBB");
//		model.addAttribute("map", map);
//		model.addAttribute("list", strList);
//	}
//	// 리턴이 없으니까 mapping 된 /ex/ex2로 list가 가는거야
//	
	
	
	//board list
	@Autowired
	Board board;
	
	@GetMapping("/list")
	public void list(Model model) {
		List<Board>list = boardDao.boardList(board);
		model.addAttribute(list);
		
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
		
	@PostMapping("/update")
	public String upload(Board board) {
		boardDao.boardEdit(board);
		return "redirect:/board/boardList";
	}
	
	
	
}
