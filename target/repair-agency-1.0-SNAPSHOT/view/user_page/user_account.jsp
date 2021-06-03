<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administrator</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <script type="text/javascript" src="https://www.kryogenix.org/code/browser/sorttable/sorttable.js"></script>
    <style>
        body {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        footer {
            margin-top: auto;
        }

        .form {
            float: right;
        }
    </style>
</head>
<body class="container">
<c:choose>
    <c:when test="${sessionScope.role.equals('USER')}">
        <header class="d-flex flex-wrap justify-content-end py-3 mb-4 border-bottom">
            <div class="col-md-3 text-end">
                <form action="top_up_account" method="post" class="form">
                    <input type="hidden" name="command" value="topUpAccount">
                    <input type="submit" value="top up account" class="btn btn-outline-primary me-2">
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
                    <td class="title"><input type="submit" name="command" value="select"
                                             class="btn btn-outline-primary me-2"></td>
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
        <h2>Please login as User</h2>
    </c:when>
</c:choose>
</body>
</html>
