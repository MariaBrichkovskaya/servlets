package com.example.servlets.servlets;

import com.example.servlets.dao.AuthDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "RegServlet", value = "/RegServlet")
public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!AuthDAO.isLoginUnique(request. getParameter("login"))){
            response.sendRedirect("/registration");
        }else {
            AuthDAO.addUser(request.getParameter("login"),request.getParameter("password"));
            response.sendRedirect("/auth");
        }

    }
}
