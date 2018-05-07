<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="title" required="true" type="java.lang.String" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="en"/>

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
            <a href="/dispetcher/stafflist.html">List of Staffs</a>
        </li>
        <li>
            <a href="/dispetcher/stafflist.html">В никуда</a>
        </li>
    </ul>
</div>
<jsp:doBody/>
</body>
</html>