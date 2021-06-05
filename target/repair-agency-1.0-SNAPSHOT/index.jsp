<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>

<html>
<head>
    <title>Main page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
            crossorigin="anonymous"></script>
    <style>
        .form {
            float: right;
        }
    </style>
</head>
<body class="container">
<header class="d-flex flex-wrap justify-content-between py-3 mb-4 border-bottom">
    <div class="col-md-3 d-flex justify-content-start">
        <form action="${pageContext.request.requestURI}" method="post">
            <input type="hidden" name="lang" value="ua">
            <input type="submit" value="<fmt:message key='Ua'/>" class="w-100 btn btn-outline-secondary">
        </form>
        <form action="${pageContext.request.requestURI}" method="post" class="mx-2">
            <input type="hidden" name="lang" value="en">
            <input type="submit" value="En" class="w-100 btn btn-outline-secondary">
        </form>
    </div>
    <div class="col-md-3 d-flex justify-content-end">
        <c:choose>
            <c:when test="${sessionScope.role.equals('ADMIN')}">
                <form action="verification" method="get">
                    <input type="hidden" name="command" value="adminPage" class="m-r-2">
                    <input type="submit" value="<fmt:message key='Account'/>" class="btn btn-outline-primary me-2">
                </form>
            </c:when>
            <c:when test="${sessionScope.role.equals('ENGINEER')}">
                <form action="verification" method="get">
                    <input type="hidden" name="command" value="engineerPage" class="m-r-2">
                    <input type="submit" value="<fmt:message key='Account'/>" class="btn btn-outline-primary me-2">
                </form>
            </c:when>
            <c:when test="${sessionScope.role.equals('USER')}">
                <form action="verification" method="get">
                    <input type="hidden" name="command" value="userPage" class="m-r-2">
                    <input type="submit" value="<fmt:message key='Account'/>" class="btn btn-outline-primary me-2">
                </form>
            </c:when>
        </c:choose>
        <form action="verification" method="get">
            <input type="hidden" name="command" value="login" class="m-r-2">
            <input type="submit" value="<fmt:message key='Login'/>" class="btn btn-outline-primary me-2">
        </form>
        <form action="verification" method="get" class="form">
            <input type="hidden" name="command" value="register">
            <input type="submit" value="<fmt:message key='Sing-up'/>" class="btn btn-primary">
        </form>
    </div>
</header>
<h2 align="center"><fmt:message key='Welcome'/></h2>
<br/>
<img height="650" width="100%" src="https://чебсервис.рф/wp-content/uploads/2018/03/tg23ed6ye7du28ei922.jpg">
<footer>
    <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05);">
        © 2021 Copyright:
        <a class="text-reset fw-bold">repair-agency.com</a>
    </div>
</footer>
</body>
</html>