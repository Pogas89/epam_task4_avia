<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 24.04.2018
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User list</title>
    <c:url var="css" value="/main.css"/>
    <link href="${css}" rel="stylesheet">
</head>
<body>
<h2>User list</h2>
<table>
    <tr>
        <th>Login</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Email</th>
        <th>Role</th>
        <td>&nbsp;</td>
    </tr>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.login}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>${user.userRole}</td>
            <td class="empty">
                <c:url var="userEdit" value="/admin/useredit.html">
                    <c:param name="id" value="${user.id}"/>
                </c:url>
                <a href="${userEdit}" class="edit"/>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/admin/useradd.html" class="add"></a>
</body>
</html>
