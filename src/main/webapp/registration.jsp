<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09.08.2023
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>registration</title>
</head>
<body>
<h1>Регистрация</h1>
<form action="/RegServlet" method="post">
    <b>Логин </b><input name="login" type="text"><br><br>
    <b>Пароль </b><input name="password" type="password"><br><br>
    <button>Зарегистрироваться</button>
</form>
</body>
</html>
