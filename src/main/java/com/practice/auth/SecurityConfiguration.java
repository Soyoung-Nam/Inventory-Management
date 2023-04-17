package com.practice.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //SecurityFilterChain이 자동으로 포함된다.
public class SecurityConfiguration {

    //로그인 실패 핸들러
    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Autowired
    private UserDetailsService CustomUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //csrf : 사용자가 자신의 의지와는 무관하게 공격자가 의도한 행위를 특정 웹사이트에 요청하게하는 공격을 말한다.
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/", "/join", "/findId", "/findPw", "/idCheckAjax", "/css/**", "/img/**").permitAll() //() 페이지는 허용
                        .anyRequest().authenticated() //이 외의 다른 페이지는 인증된(로그인한) 사용자만 허용
                )

                //로그인 설정
                .formLogin((form) -> form
                        .loginPage("/") //사용자 정의 로그인페이지
                        .loginProcessingUrl("/login") //로그인 form Action URL
                        .usernameParameter("id") //로그인 form에서 input태그의 name이 ()로 설정되어야한다.
                        .passwordParameter("pw") //로그인 form에서 input태그의 name이 ()로 설정되어야한다.
                        .defaultSuccessUrl("/index") //로그인 성공 후 이동페이지
                        .failureHandler(loginFailureHandler) //로그인 실패 핸들러
                        .permitAll()
                ) //로그아웃 설정
                .logout((logout) -> logout.permitAll());

                //아이디 저장
        http    .rememberMe()
                .rememberMeParameter("rememberCheck")
                .tokenValiditySeconds(3600)
                .userDetailsService(CustomUserDetailsService); //없다면 rememberMe 사용불가 반드시 설정

        return http.build();
    }

    //비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}