<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 24.04.2018
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<u:admin title="User list">
    <h2>User list</h2>
    <table>
        <tr>
            <th>Login</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Email</th>
            <th>Role</th>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.login}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.userRole}</td>
                <td>
                    <c:url var="userEdit" value="/admin/useredit.html">
                        <c:param name="id" value="${user.id}"/>
                    </c:url>
                    <a href="${userEdit}" class="edit"></a>
                </td>
                <td>
                    <c:url var="userDelete" value="/admin/userdelete.html">
                        <c:param name="id" value="${user.id}"/>
                    </c:url>
                    <a href="${userDelete}" class="delete"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/admin/useredit.html" class="add"></a>
    </body>
    </html>
</u:admin>