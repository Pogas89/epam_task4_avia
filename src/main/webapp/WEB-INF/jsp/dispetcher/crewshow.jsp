<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 10.05.2018
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<fmt:message key="crewshow.title" var="title"/>
<u:tags title="${title}">
    <h3>${crew.name}:</h3>
    <table>
        <tr>
            <th><fmt:message key="crewshow.table.firstname"/></th>
            <th><fmt:message key="crewshow.table.lastname"/></th>
            <th><fmt:message key="crewshow.table.department"/></th>
            <th><fmt:message key="crewshow.table.delete"/> </th>
        </tr>
        <c:forEach var="staff" items="${staffInCrew}">
            <tr>
                <td>${staff.firstName}</td>
                <td>${staff.lastName}</td>
                <td><fmt:message key="${staff.department.name}"/></td>
                <td>
                    <c:url var="staffDelete" value="/dispetcher/deletefromcrew.html">
                        <c:param name="staffId" value="${staff.id}"/>
                        <c:param name="crewId" value="${crew.id}"/>
                    </c:url>
                    <a href="${staffDelete}" class="delete"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <hr>
    <c:url var="AddStaff" value="/dispetcher/addstaffincrew.html"/>
    <form action="${AddStaff}" method="post">
        <input name="crewId" value="${crew.id}" type="hidden">
        <label for="staffId"><fmt:message key="crewshow.table.addstaff"/></label>
        <select id="staffId" name="staffId">
            <c:forEach var="staff" items="${freeStaff}">
                <option value="${staff.id}">
                        ${staff.firstName} ${staff.lastName} <fmt:message key="${staff.department.name}"/>
                </option>
            </c:forEach>
        </select><br>
        <button class="add"><fmt:message key="crewshow.button.add"/></button>
    </form>
    <c:url var="crewList" value="/dispetcher/crewlist.html"/><br>
    <a href="${crewList}" class="cancel"></a>
</u:tags>
