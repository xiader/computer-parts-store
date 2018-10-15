<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Business Card page</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-12">
            <form action="${pageContext.request.contextPath}/items" method="get">
                <div class="row">
                    <div class="col-md-12">
                        <a href="${pageContext.request.contextPath}/logout" class=" btn btn-primary"
                           aria-pressed="true" role="button">Log Out</a>
                        <button type="submit" class="btn btn-primary">Items</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">businessCard id</th>
                                <th scope="col">businessCard title</th>
                                <th scope="col">businessCard full name</th>
                                <th scope="col">businessCard workingPhone</th>
                                <th scope="col">businessCard user id</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${businessCards}" var="businessCard">
                                <tr>
                                    <th scope="row"><label>
                                        <input type="checkbox" name="ids" value="${businessCard.id}">
                                    </label></th>
                                    <th>${businessCard.id}</th>
                                    <th>${businessCard.title}</th>
                                    <th>${businessCard.fullName}</th>
                                    <th>${businessCard.workingPhone}</th>
                                    <th>${businessCard.user.id}</th>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
</body>
</html>