<%@page contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
>
<%@ taglib prefix="securiry" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Users page</title>
</head>
<body>

<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Email</th>
        <th scope="col">Name</th>
        <th scope="col">Surname</th>

        <securiry:authorize access="hasAuthority('CHANGE_USER_PASSWORD')">
            <th scope="col">Change Password</th>
        </securiry:authorize>
        <securiry:authorize access="hasAuthority('CHANGE_USER_ROLE')">
            <th scope="col">Change Role</th>
        </securiry:authorize>
        <securiry:authorize access="hasAuthority('DISABLE_USER')">
            <th scope="col">Disable user</th>
        </securiry:authorize>
        <securiry:authorize access="hasAuthority('DELETE_USER')">
            <th scope="col">Delete User</th>
        </securiry:authorize>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.email}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>

            <securiry:authorize access="hasAuthority('CHANGE_USER_PASSWORD')">
                <td>
                    <a href="${pageContext.request.contextPath}/users/${user.id}/password/"
                       class="btn btn-primary"
                       aria-pressed="true" role="button">Change Password</a>
                </td>
            </securiry:authorize>

            <securiry:authorize access="hasAuthority('CHANGE_USER_PASSWORD')">
                <td>
                    <a href="${pageContext.request.contextPath}/users/${user.id}/password/"
                       class="btn btn-primary"
                       aria-pressed="true" role="button">Change Password</a>
                </td>
            </securiry:authorize>
        </tr>
    </c:forEach>


    </tbody>
</table>

<div class="row">
    <div class="col-md-12">
        <a href="${pageContext.request.contextPath}/business-card/cards" class="btn btn-primary"
           aria-pressed="true" role="button">To Business Cards</a>
    </div>
</div>
</body>
</html>