package com.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //기본페이지(index파일로 자동 연결), 기본페이지를 login페이지로 변경
    @GetMapping("/")
    public String loginPage() {
        return "/login/login";
    }
}
