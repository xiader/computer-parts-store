<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Create Item page</title>
</head>
<html lang="en">
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form:form action="${pageContext.request.contextPath}/item.create" modelAttribute="item" method="get">
                <form:errors path="*" cssClass="error" element="div"/>
                <a href="${pageContext.request.contextPath}/logout"
                   class=" btn btn-primary" aria-pressed="true" role="button">Log Out</a>
                <a href="${pageContext.request.contextPath}/store"
                   class=" btn btn-primary" aria-pressed="true" role="button">Store</a>
                <div class="form-group">
                    <form:input path="name" class="form-control" placeholder="name"/>
                </div>
                <textarea type="text" name="description" class="form-control"
                          id="description" placeholder="description" required rows="10" cols="30"></textarea>
                <div class="form-group">
                    <form:input path="price" class="form-control" placeholder="price"
                                type="text"/>
                </div>
                <button type="submit" class="btn btn-primary">Create Item</button>
            </form:form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
</body>
</html>
