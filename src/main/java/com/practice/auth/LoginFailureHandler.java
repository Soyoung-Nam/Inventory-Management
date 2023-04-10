package com.practice.auth;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Component //빈으로 등록
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    String errorMsg;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof BadCredentialsException) {
            errorMsg = "아이디 또는 비밀번호를 잘못 입력했습니다. 다시 확인해주세요.";
        } else if (exception instanceof UsernameNotFoundException) {
            errorMsg = "존재하지 않는 아이디입니다. 회원가입 후 로그인해주세요.";
        } else {
            errorMsg = "알 수 없는 오류로 로그인 요청을 처리할 수 없습니다. 관리자에게 문의하세요.";
        }

        errorMsg = URLEncoder.encode(errorMsg, "UTF-8"); //한글 인코딩이 깨지는 것을 방지하기 위해서
        setDefaultFailureUrl("/?error=true&exception="+ errorMsg);
        super.onAuthenticationFailure(request, response, exception); //실패 이후에 이동할 페이지를 지정할 경우 상위클래스에게 위임하는 것이 더 좋기 때문에.. 이해는 잘 안간다..
    }
}
