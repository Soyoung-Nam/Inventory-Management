package com.practice.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

///// AuthenticationProvider : DB에서 가져온 정보와 input된 정보가 비교되서 체크되는 로직이 포함되어있는 인터페이스 /////
@Component //@Component : 직접 개발한 클래스를 Bean으로 등록하고자 하는 경우
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            String id = (String) authentication.getPrincipal(); //로그인 form에 입력한 id
            String pw = (String) authentication.getCredentials(); //로그인 form에 입력한 pw

            CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(id);
            UsernamePasswordAuthenticationToken token; //추후 인증이 끝나고 SecurityContextHolder에 등록될 Authentication 객체

            //일치하는 user 정보가 있는지 확인
            if (customUserDetails != null && passwordEncoder.matches(pw, customUserDetails.getPassword())) { //passwordEncoder.matches(패스워드, 암호화된 패스워드)
                token = new UsernamePasswordAuthenticationToken(customUserDetails.getId(), null, customUserDetails.getAuthorities());
                //인증된 user 정보를 담아 SecurityContextHolder에 저장되는 token
                //token에는 사용자의 password와 개인정보는 저장하지 않고 primaryKey, name, role정도만 담는 것이 좋다.
                return token;
            }
                throw new BadCredentialsException("일치하는 사용자가 없습니다.");
                //Exception을 던지지 않고 다른 값을 반환하면 authenticate() 메서드는 정상적으로 실행된 것이므로 인증되지 않았다면 Exception을 throw 해야 한다.
        }

        //AuthenticationProvider 표시된 Authentication 객체를 지원하는지 여부를 반환한다.
        @Override
        public boolean supports(Class<?> authentication) {
            return true;
        }
}
