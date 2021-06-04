<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>

<html>
<head>
    <title>Administrator</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
            crossorigin="anonymous"></script>
    <style>
        body {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        footer {
            margin-top: auto;
        }
    </style>
</head>
<body class="container">
<c:choose>
    <c:when test="${sessionScope.role.equals('ADMIN')}">
        <header class="d-flex flex-wrap justify-content-end py-3 mb-4 border-bottom">
            <div class="col-md-3 d-flex justify-content-end">
                <form action="top_up_account" method="post">
                    <input type="hidden" name="command" value="topUpAccount">
                    <input type="submit" value="<fmt:message key='TopUpAccount'/>" class="btn btn-outline-primary me-2">
                </form>
                <form action="/repair" method="post">
                    <input type="submit" value="Main page" class="btn btn-outline-primary me-2">
                </form>
                <form action="repair" method="post" class="m-r-2">
                    <input type="hidden" name="command" value="logOut">
                    <input type="submit" value="Log out" class="btn btn-primary me-2">
                </form>
            </div>
        </header>
        <h3>Table of invoices</h3>
        <table class="table table-bordered sortable">
            <thead>
            <tr>
                <th class="title">Id</th>
                <th class="title">Brand</th>
                <th class="title">Model</th>
                <th class="title">Problem description</th>
                <th class="title">Price</th>
                <th class="title">Feedback</th>
                <th class="title">Select invoice</th>
            </tr>
            </thead>
            <tbody>
            <tr>

                <c:forEach var="invoiceList" items="${invoiceList}">
            <tr>
                <td>${invoiceList.id}</td>
                <td class="title"> ${invoiceList.brand} </td>
                <td class="title">${invoiceList.model}</td>
                <td class="title">${invoiceList.description}</td>
                <td class="title">${invoiceList.price}</td>
                <td class="title">${invoiceList.feedback}</td>
                <form action="invoice" method="get">
                    <input type="hidden" name="invoiceId" value="${invoiceList.id}">
                    <input type="hidden" name="command" value="selectInvoice">
                    <td class="title">
                        <input type="submit" value="Select invoice" class="btn btn-outline-primary me-2">
                    </td>
                </form>

            </tr>
            </c:forEach>
            </tbody>
        </table>
        <footer>
            <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05);">
                © 2021 Copyright:
                <a class="text-reset fw-bold">repair-agency.com</a>
            </div>
        </footer>
    </c:when>
    <c:when test="${!sessionScope.role.equals('ADMIN')}">
        <h2>Please login as administrator</h2>
    </c:when>
</c:choose>
</body>
</html>
