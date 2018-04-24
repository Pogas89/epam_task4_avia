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
</head>
<body>
<h2>User list</h2>
<table>
    <tr>
        <th>User Login</th>
        <th>User Name</th>
        <th>User Surname</th>
        <th>User Email</th>
        <th>User Role</th>
    </tr>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.login}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>${user.userRole}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
