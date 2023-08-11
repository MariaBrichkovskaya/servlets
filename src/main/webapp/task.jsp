<%@ page import="com.example.servlets.dto.TaskDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.servlets.dao.TaskDAO" %>
<%@ page import="com.example.servlets.CheckAuth" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09.08.2023
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>tasks</title>
</head>
<body>
<h1>Список задач</h1><br>
<a href="/AuthServlet">Выйти</a><br><br>
<%
    CheckAuth.checkAuth(request, response);
%>
<%
    List<TaskDTO> tasks = TaskDAO.getInstance().getTasks();
    for(TaskDTO task: tasks) {
        out.print("<b>Название: </b>" + task.getName() + "<br>");
        out.print("<b>Описание: </b>" + task.getDescription() + "<br>");
        out.print("<b>Дата и время: </b>" + task.getTime() + "<br>");
        out.println("<br>");
    }
%>

<h3>Добавление задачи</h3>
<form action="/taskServlet" method="post">
    <b>Название: </b><input name="name" type="text"><br><br>
    <b>Описание: </b><input name="description" type="text" step="0.01"><br><br>
    <button>Добавить</button>
</form><hr><br>
</body>
</html>
