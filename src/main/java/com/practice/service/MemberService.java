package com.practice.service;

import com.practice.auth.CustomUserDetails;
import com.practice.domain.MemberDTO;
import java.util.List;

public interface MemberService {
    CustomUserDetails getMemberById(String id);

    //회원가입
     void insertMember(MemberDTO memberDTO);

}
