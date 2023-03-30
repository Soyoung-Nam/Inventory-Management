package com.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") //index페이지 login페이지로 변경
    public String indexPage() {
        return "login/login";
    }
}
