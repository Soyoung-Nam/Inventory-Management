package com.practice.service;

import com.practice.auth.CustomUserDetails;
import com.practice.domain.MemberDTO;
import com.practice.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //데이터 비즈니스 로직, 구현체
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CustomUserDetails getMemberById(String id) {
        return memberMapper.getMemberById(id);
    }

    //회원가입
    public void insertMember(MemberDTO memberDTO) {
        if (memberDTO != null) {
            //비밀번호는 암호화해서 DB에 저장한다.
            memberDTO.setPw(passwordEncoder.encode(memberDTO.getPw()));
            memberMapper.insertMember(memberDTO);
        }
    }

    //회원가입 아이디 중복체크
    @Override
    public int idCheck(String id) {
        return memberMapper.idCheck(id);
    }
}
