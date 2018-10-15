<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix ="security" uri ="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Users page</title>
</head>
<body>


<tbody>
<jsp:useBean id="users" scope="request" type="java.util.List"/>
<c:forEach items="${users}" var="user">
    <tr>
        <th scope="row"><label>
            <input type="checkbox" name="ids" value="${user.id}">
        </label></th>
        <securiry:authorize access="hasAuthority('SECURITY')">
            <th>${user.id}</th>
        </securiry:authorize>
        <th>${user.email}</th>
        <th>${user.name}</th>
        <th>${user.surname}</th>
        <securiry:authorize access="hasAuthority('SALE')">
            <th>${user.profile.phone}</th>
        </securiry:authorize>
        <securiry:authorize access="hasAuthority('CHANGE_USER_ROLE')">
            <th>${user.role.name}</th>
        </securiry:authorize>
        <th>${user.profile.userId}</th>
        <th>${user.status}</th>
        <securiry:authorize access="hasAuthority('SHOW_USER_ORDERS')">
            <td>
                <a href="${pageContext.request.contextPath}/orders/user.orders/${user.id}"
                   class="btn btn-primary"
                   aria-pressed="true" role="button">User orders</a>
            </td>
        </securiry:authorize>
        <securiry:authorize access="hasAuthority('CHANGE_USER_ROLE')">
            <td>
                <a href="${pageContext.request.contextPath}/users/user.role/${user.id}"
                   class="btn btn-primary"
                   aria-pressed="true" role="button">Change Role</a>
            </td>
        </securiry:authorize>
        <securiry:authorize access="hasAuthority('CUSTOMER')">
            <td>
                <a href="${pageContext.request.contextPath}/profile/${user.id}"
                   class="btn btn-primary"
                   aria-pressed="true" role="button">Profile</a>
            </td>
        </securiry:authorize>
        <securiry:authorize access="hasAuthority('CHANGE_USER_PASSWORD')">
            <td>
                <a href="${pageContext.request.contextPath}/users/user.password/${user.id}"
                   class="btn btn-primary"
                   aria-pressed="true" role="button">Change Password</a>
            </td>
        </securiry:authorize>
        <securiry:authorize access="hasAuthority('DISABLE_USER')">
            <td>
                <a href="${pageContext.request.contextPath}/users/${user.id}/enable"
                   class="btn btn-primary"
                   aria-pressed="true" role="button">Enable</a>
            </td>
        </securiry:authorize>
        <securiry:authorize access="hasAuthority('DISABLE_USER')">
            <td>
                <a href="${pageContext.request.contextPath}/users/${user.id}/disable"
                   class="btn btn-primary"
                   aria-pressed="true" role="button">Disable</a>
            </td>
        </securiry:authorize>
        <securiry:authorize access="hasAuthority('DELETE_USER')">
            <td>
                <a href="${pageContext.request.contextPath}/users/${user.id}/delete"
                   class="btn btn-primary"
                   aria-pressed="true" role="button">Delete</a>
            </td>
        </securiry:authorize>
    </tr>
</c:forEach>
</tbody>
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

