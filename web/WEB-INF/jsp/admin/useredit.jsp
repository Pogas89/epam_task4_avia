<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 26.04.2018
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <label for="login">Login:</label>
    <input type="text" name="login" id="${user.login}">
    <label for="password">password:</label>
    <input type="password" name="password" id="${user.password}">
    <label for="firstName">Firstname:</label>
    <input type="text" name="firstName" id="${user.firstName}">
    <label for="lastName">Lastname:</label>
    <input type="text" name="lastName" id="${user.lastName}">
    <label for="email">Email:</label>
    <input type="email" name="email" id="${user.email}">
    <label for="userRole">Role</label>
    <select id="userRole" name="userRole">
        <c:forEach var="userRole" items="${userRoles}">
            <c:choose>
                <c:when test="${userRole.id == user.userRole.id}">
                    <c:set var="selected" value="selected"/>
                </c:when>
                <c:otherwise>
                    <c:remove var="selected"/>
                </c:otherwise>
            </c:choose>
            <option value="${userRole.id}" ${selected}>${userRole.name}</option>
        </c:forEach>
    </select>
    <button class="save">Save</button>
</form>
</body>
</html>
