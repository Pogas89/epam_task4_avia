<%--
  Created by IntelliJ IDEA.
  User: uZer
  Date: 02.05.2018
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty staff}">
    <jsp:useBean id="staff" class="by.epam.ivanov.aviacompany.entity.Staff"/>
</c:if>
<html>
<head>
    <meta charset="UTF-8">
    <title>Staff data</title>
</head>
<body>
<h2>Staff data</h2>
<c:url var="StaffList" value="/admin/stafflist.html"/>
<c:url var="StaffSave" value="/admin/staffsave.html"/>
<form action="${StaffSave}" method="post">
    <c:if test="${not empty staff.id}">
        <input name="id" value="${staff.id}" type="hidden">
    </c:if>
    <label>Firstname:</label>
    <input type="text" name="firstName" id="${staff.firstName}" value="${staff.firstName}" required><br>
    <label>Lastname:</label>
    <input type="text" name="lastName" id="${staff.lastName}" value="${staff.lastName}" required><br>
    <label for="department">Role</label>
    <select id="department" name="department">
        <c:forEach var="department" items="${departments}">
            <option value="${department.id}" ${department.id == staff.department.id} ? 'selected' :'' >${department.name}</option>
        </c:forEach>
    </select><br>
    <button class="save">Save</button>
</form>
</body>
</html>
