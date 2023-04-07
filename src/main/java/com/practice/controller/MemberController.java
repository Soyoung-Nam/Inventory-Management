package com.practice.controller;

import com.practice.domain.MemberDTO;
import com.practice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/index")
    public String indexPage(Model model) { //인증된 사용자의 정보를 보여준다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //token에 저장되어 있는 인증된 사용자의 값
        int no = Integer.parseInt(authentication.getName());
        MemberDTO dto = memberService.getMemberByIdName(no);
        model.addAttribute("dto", dto);
        return "/index";
    }

    //회원가입 Page
    @GetMapping("/join")
    public String joinPage(Model model) {
        model.addAttribute("member", new MemberDTO());
        return "/login/join";
    }

    //회원가입 Action
    @PostMapping("/join")
    public String joinAction(@ModelAttribute("member") @Valid MemberDTO memberDTO, BindingResult bindingResult, HttpServletRequest request) {
        //System.out.println(memberDTO.toString());

        if(bindingResult.hasErrors()) {
            return "/login/join";
        }

        if(!bindingResult.hasErrors()) {
            memberService.insertMember(memberDTO);
            return "/login/login";
        }
        return "/login/join";
    }

    //아이디찾기 페이지
    @GetMapping("/findId")
    public String findIdPage() {
        return "/login/findId";
    }

    //비밀번호찾기 페이지
    @GetMapping("/findPw")
    public String findPwPage() {
        return "/login/findPw";
    }

}
