<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 08.05.2018
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<fmt:message key="admin.page.title" var="title"/>
<u:admin title="${title}"/>
