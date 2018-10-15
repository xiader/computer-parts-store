<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
>
<%@ taglib prefix ="security" uri ="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Users page</title>
</head>
<body>



<c:forEach items="${users}" var="user">
    <tr>

        <td>${user.email}</td>
        <td>${user.name}</td>
        <td>${user.surname}</td>
        <td>${user.id}</td>
    </tr>
</c:forEach>
</body>
</html>