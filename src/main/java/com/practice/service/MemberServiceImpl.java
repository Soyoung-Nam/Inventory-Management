package com.practice.service;

import com.practice.domain.MemberDTO;
import com.practice.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //데이터 비즈니스 로직, 구현체
public class MemberServiceImpl implements MemberService {

    private MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    //Member 테이블 가져오기
    public List<MemberDTO> getMemberList() {
        return memberMapper.getMemberList();
    }

    //회원정보 가져오기
    public MemberDTO getMemberByNo(int no) {
        return memberMapper.getMemberByNo(no);
    }

    public MemberDTO getMemberById(String id) {
        return memberMapper.getMemberById(id);
    }

    public MemberDTO getMemberByIdName(int no) {
        return memberMapper.getMemberByIdName(no);
    }

    //회원가입
    public void insertMember(MemberDTO memberDTO) {
        if (!memberDTO.getId().equals("") && !memberDTO.getName().equals("")) {
            //password는 암호화해서 DB에 저장
            memberDTO.setPw(passwordEncoder.encode(memberDTO.getPw()));
            memberMapper.insertMember(memberDTO);
        }
    }

}
