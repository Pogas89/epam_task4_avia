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
        <%--@elvariable id="flightList" type="java.util.List"--%>
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
        <c:url var="flightAdd" value="/flightedit.html"/>
        <a href="${flightAdd}" class="add"></a>
    </c:if>
    <u:pagination/>
</u:tags>
