<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="title" required="true" type="java.lang.String" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <c:url var="css" value="/main.css"/>
    <link href="${css}" rel="stylesheet">
</head>
<body>
<div id="menu">
    <ul>
        <li>
            <a href="/admin/userlist.html"><fmt:message key="userlist.title"/></a>
        </li>
        <li>
            <a href="/admin/flightlist.html"><fmt:message key="flightlist.title"/></a>
        </li>
        <li>
            <a href="/admin/userlist.html">в никуда</a>
        </li>
        <c:if test="${not empty currentUser}">
            <li>
                <fmt:message key="app.welcome"/>: ${currentUser.login}
            </li>
            <li>
                <a href="/logout.html"><fmt:message key="app.button.loguot"/></a>
            </li>
        </c:if>
    </ul>
</div>
<jsp:doBody/>
</body>
</html>
