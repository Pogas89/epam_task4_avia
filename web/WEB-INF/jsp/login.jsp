<%--
  Created by IntelliJ IDEA.
  User: uZer
  Date: 02.05.2018
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <c:url var="css" value="/main.css"/>
    <link href="${css}" rel="stylesheet">
</head>
<body>
<h2>Login</h2>
<c:if test="${not empty param.message}">
    <p class="error">Login or password incorrect</p>
</c:if>
<c:url var="login" value="/login.html"/>
<form action="${login}" method="post">
    <label for="login">Login:</label>
    <input id="login" name="login" required>
    <label for="password">Password:</label>
    <input id="password" name="password" type="password" required>
    <button class="login">LogIn</button>
</form>
</body>
</html>
