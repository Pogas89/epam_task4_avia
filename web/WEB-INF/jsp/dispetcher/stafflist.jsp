<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 24.04.2018
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<fmt:message key="stafflist.title" var="title"/>
<u:dispetcher title="${title}">
    <h2>${title}</h2>
    <table>
        <tr>
            <th><fmt:message key="stafflist.table.firstname"/></th>
            <th><fmt:message key="stafflist.table.lastname"/></th>
            <th><fmt:message key="stafflist.table.department"/></th>
            <th><fmt:message key="stafflist.button.edit"/></th>
            <th><fmt:message key="stafflist.button.delete"/></th>
        </tr>
        <c:forEach var="staff" items="${staffList}">
            <tr>
                <td>${staff.firstName}</td>
                <td>${staff.lastName}</td>
                <td><fmt:message key="${staff.department.name}"/></td>
                <td class="empty">
                    <c:url var="staffEdit" value="/dispetcher/staffedit.html">
                        <c:param name="id" value="${staff.id}"/>
                    </c:url>
                    <a href="${staffEdit}" class="edit"/>
                </td>
                <td>
                    <c:url var="staffDelete" value="/dispetcher/staffdelete.html">
                        <c:param name="id" value="${staff.id}"/>
                    </c:url>
                    <a href="${staffDelete}" class="delete"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/dispetcher/staffedit.html" class="add"></a>
</u:dispetcher>
