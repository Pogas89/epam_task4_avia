<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="title" required="true" type="java.lang.String" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
            <a href="/admin/userlist.html">List of Users</a>
        </li>
        <li>
            <a href="/admin/stafflist.html">List of Staffs</a>
        </li>
    </ul>
</div>
<jsp:doBody/>
</body>
</html>
