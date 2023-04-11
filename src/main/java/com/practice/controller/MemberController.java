package com.practice.controller;

import com.practice.auth.CustomUserDetails;
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

    @Autowired
    private MemberService memberService;

    @GetMapping("/index")
    public String indexPage(Model model) { //인증된 사용자의 정보를 보여준다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //token에 저장되어 있는 인증된 사용자의 값
        String id = authentication.getName();
        CustomUserDetails customUserDetails = memberService.getMemberById(id);
        model.addAttribute("cud", customUserDetails);
        return "/index";
    }

    //회원가입 Page
    @GetMapping("/join")
    public String joinPage(Model model) {
        model.addAttribute("member", new MemberDTO()); //빈 오브젝트 보낸다.
        return "/login/join";
    }

    //회원가입 Action
    @PostMapping("/join")
    public String joinAction(@ModelAttribute("member") @Valid MemberDTO memberDTO, BindingResult bindingResult, HttpServletRequest request, Model model) {
        //System.out.println(memberDTO.toString());

        if(bindingResult.hasErrors()) {
            model.addAttribute("dto", memberDTO);
            return "/login/join";
        } else {
            memberService.insertMember(memberDTO);
            return "/login/login";
        }
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
