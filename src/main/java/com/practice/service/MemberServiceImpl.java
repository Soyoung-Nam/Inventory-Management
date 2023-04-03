package com.practice.service;

import com.practice.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.practice.domain.MemberDTO;

import java.util.List;

@Service //데이터 비즈니스 로직, 구현체
public class MemberServiceImpl implements MemberService {

    private MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    //로그인
    public MemberDTO memberLogin(MemberDTO dto) {
        return memberMapper.memberLogin(dto);
    }
}
