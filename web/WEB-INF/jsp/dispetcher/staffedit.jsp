<%--
  Created by IntelliJ IDEA.
  User: uZer
  Date: 02.05.2018
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<c:if test="${empty staff}">
    <jsp:useBean id="staff" class="by.epam.ivanov.aviacompany.entity.Staff"/>
</c:if>
<fmt:message  var="title" key="${not empty staff.id?'staffedit.title.add':'staffedit.title.edit'}"/>
<u:dispetcher title="${title}">
    <h2>${title}</h2>
    <c:url var="StaffList" value="/dispetcher/stafflist.html"/>
    <c:url var="StaffSave" value="/dispetcher/staffsave.html"/>
    <form action="${StaffSave}" method="post">
        <c:if test="${not empty staff.id}">
            <input name="id" value="${staff.id}" type="hidden">
        </c:if>
        <label><fmt:message key="staffedit.form.firstname"/></label>
        <input type="text" name="firstName" id="${staff.firstName}" value="${staff.firstName}" required><br>
        <label><fmt:message key="staffedit.form.lastname"/></label>
        <input type="text" name="lastName" id="${staff.lastName}" value="${staff.lastName}" required><br>
        <label for="department"><fmt:message key="staffedit.form.department"/></label>
        <select id="department" name="department">
            <c:forEach var="department" items="${departments}">
                <option value="${department.id}" ${department.id == staff.department.id} ?
                'selected' :'' ><fmt:message key="${department.name}"/></option>
            </c:forEach>
        </select><br>
        <button class="save"><fmt:message key="staffedit.button.save"/></button>
    </form>
</u:dispetcher>
