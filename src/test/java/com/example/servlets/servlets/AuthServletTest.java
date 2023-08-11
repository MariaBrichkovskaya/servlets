package com.example.servlets.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class AuthServletTest {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpSession session = mock(HttpSession.class);
    Cookie cookie = mock(Cookie.class);


    @Test
    void doGet() throws IOException, ServletException {
        when(request.getSession()).thenReturn(session);
        when(session.getId()).thenReturn("1234");

        AuthServlet servlet = new AuthServlet();
        servlet.doGet(request, response);
        verify(session).setMaxInactiveInterval(-1);
        verify(response).addCookie(any());
        verify(response).sendRedirect("/auth");
    }
    @Test
    void doPost(){

    }
}