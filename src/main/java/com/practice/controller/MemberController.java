package com.practice.controller;

import com.practice.domain.MemberDTO;
import com.practice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MemberController {

    private MemberService memberService;

    @Autowired //연결
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public String loginAction(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String error = "";

        MemberDTO dto = new MemberDTO();
        dto.setId(id);
        dto.setPw(pw);

        MemberDTO result = memberService.memberLogin(dto);

        if(result != null) {
            return "/index";
        } else {
            return "/login/login";
        }
    }
}
