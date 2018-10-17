<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <meta charset="utf-8"/>
    <title>Error page</title>
</head>
<body>
<h2>Error! What a surprise!</h2>
<h3>Info:</h3>
Requested URL=${url}<br><br><br>
Exception=${exception.message}<br><br>
ExceptionCause=${exception.cause.message}<br><br>
<strong>Stack Trace</strong><br>
<c:forEach items="${exception.stackTrace}" var="stack">
    ${stack}
</c:forEach>
</body>
</html>