package com.example.servlets.servlets;

import com.example.servlets.dao.AuthDAO;
import com.example.servlets.db.DBConnector;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AuthServlet", value = "/AuthServlet")
public class AuthServlet extends HttpServlet {
    private static final AuthDAO authDAO = AuthDAO.getInstance(DBConnector.INSTANCE);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login=request.getParameter("login");
        if(!authDAO.isAuth(login,request.getParameter("password"))){
            response.sendRedirect("/auth");
        }else {
            HttpSession session= request.getSession();
            session.setAttribute("login",login);
            session.setMaxInactiveInterval(-1);
            Cookie cookie=new Cookie("SessionId",session.getId());
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
            response.sendRedirect("/task");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession();
        session.setMaxInactiveInterval(-1);
        Cookie cookie=new Cookie("SessionId",session.getId());
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.sendRedirect("/auth");
    }
}
