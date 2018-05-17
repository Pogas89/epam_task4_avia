<%--
  Created by IntelliJ IDEA.
  User: uZer
  Date: 08.05.2018
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<fmt:message key="crewlist.title" var="title"/>
<u:tags title="${title}">
    <h2>${title}</h2>
    <table>
        <tr>
            <th><fmt:message key="crewlist.table.name"/></th>
            <th><fmt:message key="crewlist.table.creater"/></th>
            <th><fmt:message key="crewlist.button.edit"/></th>
            <th><fmt:message key="crewlist.button.delete"/></th>
            <th><fmt:message key="crewlist.button.show"/></th>
        </tr>
        <c:forEach var="crew" items="${crewList}">
            <tr>
                <td>${crew.name}</td>
                <td>${crew.user.login}</td><!-- todo: не работает, подтянуть usera создателя -->
                <td class="empty">
                    <c:url var="crewEdit" value="/dispetcher/crewedit.html">
                        <c:param name="id" value="${crew.id}"/>
                    </c:url>
                    <a href="${crewEdit}" class="edit"/>
                </td>
                <td>
                    <c:url var="crewDelete" value="/dispetcher/crewdelete.html">
                        <c:param name="id" value="${crew.id}"/>
                    </c:url>
                    <a href="${crewDelete}" class="delete"></a>
                </td>
                <td>
                    <c:url var="crewShow" value="/dispetcher/crewshow.html">
                        <c:param name="id" value="${crew.id}"/>
                    </c:url>
                    <a href="${crewShow}" class="show"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/dispetcher/crewedit.html" class="add"></a>
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
