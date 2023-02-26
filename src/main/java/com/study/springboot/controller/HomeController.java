package com.study.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller

//일단 page를 홈으로 삼고 리다이렉트한다(게시판이 많이 없으므로)
public class HomeController{
    @GetMapping("/")
    public String index(HttpSession session) {
    
        return "redirect:board/page";
    	}
    
  
    
    
    @GetMapping("/home")
    public String home(HttpSession session) {
    	if(session.getAttribute("memberNum") != null) {
    		return "member/login-after";
    	} else {
        return "home_backup2";
    	}
    }
}
