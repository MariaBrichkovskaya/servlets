package com.example.servlets.servlets;

import com.example.servlets.dao.AuthDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AuthServlet", value = "/AuthServlet")
public class AuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!AuthDAO.isAuth((String) request.getAttribute("login"), (String) request.getAttribute("password"))){
            response.sendRedirect("/auth");
        }
        Cookie cookie = new Cookie("id",(String) request.getAttribute("login") );
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
        response.sendRedirect("/task");

    }
}
