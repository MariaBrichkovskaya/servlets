package com.example.servlets;

import com.example.servlets.dto.TaskDTO;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.*;
import java.time.LocalDateTime;


@WebServlet(name = "task", value = "/task")
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out=response.getWriter();

        try (Statement statement=DBConnector.CONNECTION.createStatement();
              ResultSet resultSet=statement.executeQuery("select * from tasks");){

            int i = 2;
            while (resultSet.next()) {
                if(i>4) i=2;
                String name = resultSet.getString(i++);
                String description = resultSet.getString(i++);
                LocalDateTime time =resultSet.getTimestamp(i++).toLocalDateTime();
                TaskDTO task = new TaskDTO(name,description,time);
                out.println(task);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name=request.getParameter("name");
        String description=request.getParameter("description");
        TaskDTO task =new TaskDTO(name,description);

        try (PreparedStatement statement=  DBConnector.CONNECTION.prepareStatement("insert into tasks(name,description,date) values (?,?,?)");){
            statement.setString(1,task.getName());
           statement.setString(2,task.getDescription());
           statement.setObject(3,task.getTime());
           statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id= Integer.parseInt(request.getParameter("id"));

        try (PreparedStatement statement=  DBConnector.CONNECTION.prepareStatement("delete from tasks where id=?"); ){

            statement.setLong(1,id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        String name=request.getParameter("name");

        long id=Integer.parseInt(request.getParameter("id"));
        try ( PreparedStatement statement=  DBConnector.CONNECTION.prepareStatement("UPDATE tasks SET name = ? WHERE id = ?;");
        ){
           statement.setString(1,name);
            statement.setLong(2,id);
            statement.executeUpdate();
            System.out.println(name+id);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
