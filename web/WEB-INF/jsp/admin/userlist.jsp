<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 24.04.2018
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<fmt:message key="userlist.title" var="title"/>
<u:tags title="${title}">
    <h2>${title}</h2>
    <table>
        <tr>
            <th><fmt:message key="userlist.table.login"/></th>
            <th><fmt:message key="userlist.table.firstname"/></th>
            <th><fmt:message key="userlist.table.lastname"/></th>
            <th><fmt:message key="userlist.table.email"/></th>
            <th><fmt:message key="userlist.table.userrole"/></th>
            <th><fmt:message key="userlist.button.edit"/></th>
            <th><fmt:message key="userlist.button.delete"/></th>
        </tr>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.login}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td><fmt:message key="${user.userRole.name}"/></td>
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
    <div id="pagination" class="pagin">
        <c:url value="${currentPage}" var="prev">
            <c:param name="page" value="${page-1}"/>
        </c:url>
        <c:if test="${page > 1}">
            <a href="<c:out value="${prev}" />" class="pn prev">
                <button class="btn btn-info">Назад</button>
            </a>
        </c:if>
        <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
            <c:choose>
                <c:when test="${page == i.index}">
                    <span>${i.index}</span>
                </c:when>
                <c:otherwise>
                    <c:url value="${currentPage}" var="url">
                        <c:param name="page" value="${i.index}"/>
                    </c:url>
                    <a href='<c:out value="${url}" />'>
                        <button class="btn btn-info">${i.index}</button>
                    </a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:url value="${currentPage}" var="next">
            <c:param name="page" value="${page + 1}"/>
        </c:url>
        <c:if test="${page + 1 <= maxPages}">
            <a href='<c:out value="${next}" />' class="pn next">
                <button class="btn btn-info">Вперед</button>
            </a>
        </c:if>
    </div>
</u:tags>