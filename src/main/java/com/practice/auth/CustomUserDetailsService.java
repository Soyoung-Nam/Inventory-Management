package com.practice.auth;

import com.practice.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

///// DB에서 유저 정보를 가져오는 역할 /////
//loadUserByUsername() 메소드에서 CustomUserDetails으로 사용자정보를 받아오면 된다.
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        CustomUserDetails customUserDetails = memberMapper.getMemberById(id);
        if (customUserDetails == null) {
            throw new UsernameNotFoundException("아이디가 일치하는 사용자가 없습니다.");
        }
        return customUserDetails;
    }
}
