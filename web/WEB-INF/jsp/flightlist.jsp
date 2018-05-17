<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11.05.2018
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<fmt:message key="flightlist.title" var="title"/>
<u:tags title="${title}">
    <h2>${title}</h2>
    <table>
        <tr>
            <th><fmt:message key="flightlist.table.name"/></th>
            <th><fmt:message key="flightlist.table.departure"/></th>
            <th><fmt:message key="flightlist.table.destination"/></th>
            <th><fmt:message key="flightlist.table.date"/></th>
            <th><fmt:message key="flightlist.table.time"/></th>
            <th><fmt:message key="flightlist.table.status"/></th>
            <th><fmt:message key="flightlist.table.crew"/></th>
            <th><fmt:message key="flightlist.button.edit"/></th>
            <c:if test="${sessionScope.currentUser.userRole.id==0}">
                <th><fmt:message key="flightlist.button.delete"/></th>
            </c:if>
        </tr>
        <c:forEach var="flight" items="${flightList}">
            <tr>
                <td>${flight.name}</td>
                <td>${flight.departure}</td>
                <td>${flight.destination}</td>
                <td>${flight.date}</td>
                <td>${flight.time}</td>
                <td><fmt:message key="${flight.status.name}"/></td>
                <td>${flight.crew.name}</td>
                <td>
                    <c:url var="flightEdit" value="/flightedit.html">
                        <c:param name="id" value="${flight.id}"/>
                    </c:url>
                    <a href="${flightEdit}" class="edit"></a>
                </td>
                <c:if test="${sessionScope.currentUser.userRole.id==0}">
                    <td>
                        <c:url var="flightDelete" value="/flightdelete.html">
                            <c:param name="id" value="${flight.id}"/>
                        </c:url>
                        <a href="${flightDelete}" class="delete"></a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${sessionScope.currentUser.userRole.id==0}">
        <a href="${flightEdit}" class="add"></a>
    </c:if>
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
