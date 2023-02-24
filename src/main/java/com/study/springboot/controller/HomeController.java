package com.study.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller

public class HomeController{
    @GetMapping("/")
    public String index(HttpSession session) {
    
        return "page/home";
    	}
    
    
    @GetMapping("/home")
    public String home(HttpSession session) {
    	if(session.getAttribute("memberNum") != null) {
    		return "member/login-after";
    	} else {
        return "home";
    	}
    }
}
