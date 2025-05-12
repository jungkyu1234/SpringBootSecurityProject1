package com.example.demo2.configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    public UserAuthenticationFailureHandler() {
        // 생성자에서 기본 설정
        setUseForward(true);
        setDefaultFailureUrl("/member/login?error=true");
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        request.setAttribute("errorMessage", "로그인에 실패하였습니다.");
        System.out.println("로그인에 실패하였습니다.");
        response.sendRedirect("/member/login");
        super.onAuthenticationFailure(request, response, exception);
    }
}
