<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Change address</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-12">
                    <a href="${pageContext.request.contextPath}/logout" class=" btn btn-primary"
                       aria-pressed="true" role="button">Log Out</a>
                    <a href="${pageContext.request.contextPath}/profile"
                       class=" btn btn-primary" aria-pressed="true" role="button">Profile</a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <form action="${pageContext.request.contextPath}/profile/profile.update.address/${user.id}"
                          method="post">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">user id</th>
                                <th scope="col">user address</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <th>${user.id}</th>
                                <th>${user.profile.address}</th>
                            </tr>
                            </tbody>
                        </table>
                        <label class="my-1 mr-2">Address</label>
                        <input type="text" name="address">
                        <button type="submit" class="btn btn-primary my-1">Update</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
</body>
</html>