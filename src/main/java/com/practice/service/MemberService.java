package com.practice.service;

import com.practice.auth.CustomUserDetails;
import com.practice.domain.MemberDTO;
import java.util.List;

public interface MemberService {
    CustomUserDetails getMemberById(String id);

    String getIdByEmail(String email);

    //회원가입
     void insertMember(MemberDTO memberDTO);

    //회원가입 아이디 중복체크
    int idCheck(String id);

    //회원가입 이메일 존재여부
    int emailCheck(String email);
}
