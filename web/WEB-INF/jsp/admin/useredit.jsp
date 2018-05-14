<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 26.04.2018
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<c:if test="${empty user}">
    <jsp:useBean id="user" class="by.epam.ivanov.aviacompany.entity.User"/>
</c:if>
<fmt:message var="title" key="${not empty user.id?'useredit.title.edit':'useredit.title.add'}"/>
<u:admin title="${title}">
    <h2>${title}</h2>
    <c:url var="UserList" value="/admin/userlist.html"/>
    <c:url var="UserSave" value="/admin/usersave.html"/>
    <form action="${UserSave}" method="post">
        <c:if test="${not empty user.id}">
            <input name="id" value="${user.id}" type="hidden">
        </c:if>
        <label for="${user.login}"><fmt:message key="useredit.form.login"/></label>
        <input type="text" name="login" id="${user.login}" value="${user.login}" required><br>
        <label for="${user.password}"><fmt:message key="useredit.form.password"/></label>
        <input type="password" name="password" id="${user.password}" value="${user.password}" required><br>
        <label for="${user.firstName}"><fmt:message key="useredit.form.firstname"/></label>
        <input type="text" name="firstName" id="${user.firstName}" value="${user.firstName}" required><br>
        <label for="${user.lastName}"><fmt:message key="useredit.form.lastname"/></label>
        <input type="text" name="lastName" id="${user.lastName}" value="${user.lastName}" required><br>
        <label for="${user.email}"><fmt:message key="useredit.form.email"/></label>
        <input type="email" name="email" id="${user.email}" value=" ${user.email}" required><br>
        <label for="userRole"><fmt:message key="useredit.form.userrole"/></label>
        <select id="userRole" name="userRole">
            <c:forEach var="userRole" items="${userRoles}">
                <option value="${userRole.id}" ${userRole.id == user.userRole.id ?
                        'selected' :''}><fmt:message key="${userRole.name}"/></option>
            </c:forEach>
        </select><br>
        <button class="save"><fmt:message key="useredit.button.save"/></button>
        <button class="cancel" formnovalidate formaction="${UserList}" formmethod="get">
            <fmt:message key="useredit.button.cancel"/>
        </button>
    </form>
</u:admin>