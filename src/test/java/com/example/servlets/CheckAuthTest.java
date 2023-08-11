package com.example.servlets;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckAuthTest {

    @Test
    void checkAuth() throws IOException {
        // Set up mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Set up mock Cookie
        Cookie cookie = mock(Cookie.class);
        when(cookie.getName()).thenReturn("SessionId");
        when(cookie.getValue()).thenReturn("12345");

        // Set up mock HttpSession
        HttpSession session = mock(HttpSession.class);
        when(session.getId()).thenReturn("12345");

        // Set up mock HttpServletRequest getCookies method
        when(request.getCookies()).thenReturn(new Cookie[]{cookie});

        // Set up mock HttpServletRequest getSession method
        when(request.getSession(false)).thenReturn(session);

        // Call checkAuth method
        CheckAuth.checkAuth(request, response);

        // Verify that no redirect was sent
        verify(response, never()).sendRedirect(anyString());

        // Set up mock HttpSession to return null
        when(request.getSession(false)).thenReturn(null);

        // Call checkAuth method again
        CheckAuth.checkAuth(request, response);

        // Verify that redirect to /auth was sent
        verify(response).sendRedirect("/auth");
    }
}