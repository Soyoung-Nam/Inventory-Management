package com.practice.mapper;

import com.practice.auth.CustomUserDetails;
import com.practice.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper //mapper 인터페이스, MyBatis 매핑XML에 기재된 SQL를 호출하기 위한 인터페이스
public interface MemberMapper {
    CustomUserDetails getMemberById(String id);

    //회원가입
    void insertMember(MemberDTO memberDTO);
}
