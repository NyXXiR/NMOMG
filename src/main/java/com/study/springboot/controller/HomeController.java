package com.study.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
<<<<<<< HEAD
public class HomeController{
    @GetMapping("/")
    public String home() {
        return "home";
    }
=======
public class HomeController {

  @GetMapping("/")
  public String home() {
    return "home";
  }

>>>>>>> origin/main
}
