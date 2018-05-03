<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 24.04.2018
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Staff list</title>
    <c:url var="css" value="/main.css"/>
    <link href="${css}" rel="stylesheet">
</head>
<body>
<h2>Staff list</h2>
<table>
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Department</th>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <c:forEach var="staff" items="${staffList}">
        <tr>
            <td>${staff.firstName}</td>
            <td>${staff.lastName}</td>
            <td>${staff.department}</td>
            <td class="empty">
                <c:url var="staffEdit" value="/admin/staffedit.html">
                    <c:param name="id" value="${staff.id}"/>
                </c:url>
                <a href="${staffEdit}" class="edit"/>
            </td>
            <td>
                <c:url var="staffDelete" value="/admin/staffdelete.html">
                    <c:param name="id" value="${staff.id}"/>
                </c:url>
                <a href="${staffDelete}" class="delete"></a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/admin/staffedit.html" class="add"></a>
</body>
</html>
