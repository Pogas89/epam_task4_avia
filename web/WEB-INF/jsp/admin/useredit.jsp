<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 26.04.2018
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty user}">
    <jsp:useBean id="user" class="by.epam.ivanov.aviacompany.entity.User"/>
</c:if>
<html>
<head>
    <meta charset="UTF-8">
    <title>User data</title>
</head>
<body>
<h2>User data</h2>
<c:url var="UserList" value="/admin/userlist.html"/>
<c:url var="UserSave" value="/admin/usersave.html"/>
<form action="${UserSave}" method="post">
    <c:if test="${not empty user.id}">
        <input name="id" value="${user.id}" type="hidden">
    </c:if>
    <label>Login:</label>
    <input type="text" name="login" id="${user.login}" value="${user.login}" required><br>
    <label>password:</label>
    <input type="password" name="password" id="${user.password}" value="${user.password}" required><br>
    <label>Firstname:</label>
    <input type="text" name="firstName" id="${user.firstName}" value="${user.firstName}" required><br>
    <label>Lastname:</label>
    <input type="text" name="lastName" id="${user.lastName}" value="${user.lastName}" required><br>
    <label>Email:</label>
    <input type="email" name="email" id="${user.email}" value=" ${user.email}" required><br>
    <label for="userRole">Role</label>
    <select id="userRole" name="userRole">
        <c:forEach var="userRole" items="${userRoles}">
            <option value="${userRole.id}" ${userRole.id == user.userRole.id} ? 'selected' :'' >${userRole.name}</option>
        </c:forEach>
    </select><br>
    <button class="save">Save</button>
</form>
</body>
</html>
