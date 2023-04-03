package com.practice.service;

import com.practice.domain.MemberDTO;

import java.util.List;

public interface MemberService {

    //로그인
    public MemberDTO memberLogin(MemberDTO dto);
}
