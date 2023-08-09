package com.example.servlets.servlets;

import com.example.servlets.CheckAuth;
import com.example.servlets.dao.TaskDAO;
import com.example.servlets.dto.TaskDTO;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "taskServlet", value = "/taskServlet")
public class TaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out=response.getWriter();
        out.println(TaskDAO.getTasks());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CheckAuth.checkAuth(request, response);
        TaskDTO task =new TaskDTO(request.getParameter("name"),request.getParameter("description"));
        TaskDAO.addTask(task);
        response.sendRedirect("/task");

    }

   /* @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id= Integer.parseInt(request.getParameter("id"));
        TaskDAO.deleteTask(id);
        response.sendRedirect("/task");

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id=Integer.parseInt(request.getParameter("id"));
        TaskDAO.editTask(request.getParameter("name"),id);
        response.sendRedirect("/task");
    }*/
}
