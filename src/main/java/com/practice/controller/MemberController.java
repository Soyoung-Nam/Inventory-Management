package com.practice.controller;

import com.practice.domain.MemberDTO;
import com.practice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    //회원가입 페이지
    @GetMapping("/login/join")
    public String joinPage() {
        return "/login/join";
    }

    //회원가입 Action
    @PostMapping("/join")
    public String joinAction(HttpServletRequest request, MemberDTO memberDTO) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String pw = request.getParameter("pw");
        String email = request.getParameter("email");
        String birthDate = request.getParameter("birthDate");
        String phone = request.getParameter("phone");

        MemberDTO dto = new MemberDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setPw(pw);
        dto.setEmail(email);
        dto.setBirthDate(birthDate);
        dto.setPhone(phone);

        System.out.println(memberDTO.toString());

        memberService.insertMember(memberDTO);
        return "/login/login";
    }

}
