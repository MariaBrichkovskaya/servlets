package com.example.servlets;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class CheckAuth {
    public static void checkAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("SessionId")) {
                    String sessionId = cookie.getValue();
                    HttpSession session = request.getSession(false);
                    if (session != null && sessionId.equals(session.getId())) {
                        return;
                    }
                }
            }
        }
        response.sendRedirect("/auth");

    }
}