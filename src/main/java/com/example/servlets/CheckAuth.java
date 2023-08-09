package com.example.servlets;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CheckAuth {
    public static String checkAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            if(cookie.getName().equals("id")) login=cookie.getValue();
        }
        if(login==null) response.sendRedirect("/auth");
        return login;
    }
}