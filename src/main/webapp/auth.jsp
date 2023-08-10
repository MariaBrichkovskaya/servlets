<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09.08.2023
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>authorization</title>
</head>
<body>
<h1>Авторизация</h1>
<form action="/AuthServlet" method="post">
    <b>Логин </b><input name="login" type="text"><br/><br/>
    <b>Пароль </b><input name="password" type="password">
    <button>Войти</button>
</form>
<a href="/registration">Зарегистрироваться</a>
</body>
</html>
