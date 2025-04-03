package com.example.todo.auth;

import com.example.todo.auth.dto.AuthResponseDto;
import jakarta.servlet.http.HttpSession;

public class SessionManager {
    public static final String LOGIN_USER = "loginUser";

    public static void setLoginUser(HttpSession session, AuthResponseDto user) {
        session.setAttribute(LOGIN_USER, user);
        // 30분 세션 만료
        session.setMaxInactiveInterval(60*30);
    }

    public static void logout(HttpSession session) {
        session.invalidate();
    }

    private static AuthResponseDto getLoginUser(HttpSession session) {
        return (AuthResponseDto)session.getAttribute(LOGIN_USER);
    }
    //  로그인 여부
    public static boolean isLogin(HttpSession session) {
        return session != null && getLoginUser(session) != null;
    }


}
