package com.practice.service;

import com.practice.domain.MemberDTO;

import java.util.List;

public interface MemberService {

    //Member 테이블 가져오기
    List<MemberDTO> getMemberList();

    //회원정보 가져오기
    MemberDTO getMemberByNo(int no);

    MemberDTO getMemberById(String id);

    MemberDTO getMemberByIdName(int no);

    //회원가입
    void insertMember(MemberDTO memberDTO);

}
