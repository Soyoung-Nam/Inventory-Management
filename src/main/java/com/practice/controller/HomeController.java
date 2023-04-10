package com.practice.controller;

import com.practice.domain.MemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    //기본페이지(index파일로 자동 연결), 기본페이지를 login페이지로 변경
    @GetMapping("/")
    public String loginPage(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "exception", required = false) String exception, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/login/login";
    }
}
