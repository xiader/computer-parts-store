<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
>
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
<%--

    <form:form action="${pageContext.request.contextPath}/users"  method="get">







    <div class="form-group">
        <form:label path="firstName"> Fist name</form:label>
        <form:input path="firstName" class="form-control"
                    placeholder="First name"/>
    </div>



    <div class="form-group">
        <form:label path="lastName"> Fist name</form:label>
        <form:input path="lastName" class="form-control"
                    placeholder="Last name"/>
    </div>

    <div class="form-group">
        <form:label path="email"> Fist name</form:label>
        <form:input path="email" class="form-control"
                    placeholder="Email"/>
    </div>

    <div class="form-group">
        <form:label path="Password"> Fist name</form:label>
        <form:input path="Password" type="password" class="form-control"
                    placeholder="Password"/>
    </div>
    <button type="submit" class="btn btn-primary">SAVE</button>

</form:form>

--%>

</body>
</html>