package com.example.servlets.servlets;

import com.example.servlets.CheckAuth;
import com.example.servlets.dao.TaskDAO;
import com.example.servlets.db.DBConnector;
import com.example.servlets.dto.TaskDTO;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "taskServlet", value = "/taskServlet")
public class TaskServlet extends HttpServlet {
    private static final TaskDAO taskDAO=TaskDAO.getInstance(DBConnector.INSTANCE);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out=response.getWriter();
        out.println(taskDAO.getTasks());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CheckAuth.checkAuth(request, response);
        TaskDTO task =new TaskDTO(request.getParameter("name"),request.getParameter("description"));
        taskDAO.addTask(task);
        response.sendRedirect("/task");

    }

   /* @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id= Integer.parseInt(request.getParameter("id"));
        taskDAO.deleteTask(id);
        response.sendRedirect("/task");

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id=Integer.parseInt(request.getParameter("id"));
        taskDAO.editTask(request.getParameter("name"),id);
        response.sendRedirect("/task");
    }*/
}
