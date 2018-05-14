<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 10.05.2018
  Time: 8:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<c:if test="${empty crew}">
    <jsp:useBean id="crew" class="by.epam.ivanov.aviacompany.entity.Crew"/>
</c:if>
<fmt:message  var="title" key="${not empty crew.id?'crewedit.title.add':'crewedit.title.edit'}"/>
<u:tags title="${title}">
    <h2>${title}</h2>
    <c:url var="CrewList" value="/dispetcher/crewlist.html"/>
    <c:url var="CrewSave" value="/dispetcher/crewsave.html"/>
    <form action="${CrewSave}" method="post">
        <c:if test="${not empty crew.id}">
            <input name="id" value="${crew.id}" type="hidden">
        </c:if>
        <label for="${crew.name}"><fmt:message key="crewedit.form.name"/></label>
        <input type="text" name="name" id="${crew.name}" value="${crew.name}" required><br>
        <label><fmt:message key="crewedit.form.creater"/></label>
        <input type="text" name="user" id="${user.login}" value="${user.login}" disabled><br>
        <button class="save"><fmt:message key="crewedit.button.save"/></button>
        <button class="cancel" formnovalidate formaction="${CrewList}" formmethod="post">
            <fmt:message key="crewedit.button.cancel"/>
        </button>
    </form>
</u:tags>
