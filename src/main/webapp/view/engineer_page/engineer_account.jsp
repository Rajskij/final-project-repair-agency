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
    <c:when test="${sessionScope.role.equals('ENGINEER')}">
        <header class="d-flex flex-wrap justify-content-end py-3 mb-4 border-bottom">
            <div class="col-md-3 d-flex justify-content-end">
                <form action="/repair" method="post">
                    <input type="submit" value="<fmt:message key='MainPage'/>" class="btn btn-outline-primary me-2 mx-2">
                </form>
                <form action="repair" method="post" class="form">
                    <input type="hidden" name="command" value="logOut">
                    <input type="submit" value="<fmt:message key='LogOut'/>" class="btn btn-primary me-2">
                </form>
            </div>
        </header>
        <h3><fmt:message key='InvoiceTable'/></h3>
        <table class="table table-bordered sortable">
            <thead>
            <tr>
                <th class="title"><fmt:message key='Id'/></th>
                <th class="title"><fmt:message key='Brand'/></th>
                <th class="title"><fmt:message key='Model'/></th>
                <th class="title"><fmt:message key='ProblemDescription'/></th>
                <th class="title"><fmt:message key='Price'/></th>
                <th class="title"><fmt:message key='Feedback'/></th>
                <th class="title"><fmt:message key='SelectInvoice'/></th>
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
                        <input type="submit" value="<fmt:message key='SelectInvoice'/>" class="btn btn-outline-primary me-2">
                    </td>
                </form>
            </tr>
            </c:forEach>
            </tbody>
        </table>
        <footer>
            <div class="d-flex flex-row justify-content-center">
                <c:forEach var="i" begin="1" end="${pages}">
                    <c:choose>
                        <c:when test="${pages != 1}">
                            <form action="account" method="get">
                                <input type="hidden" name="command" value="engineerPage">
                                <input type="hidden" name="page" value="${i}">
                                <input type="submit" class="btn btn-outline-secondary mx-1" value="${i}">
                            </form>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </div>
            <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05);">
                © 2021 Copyright:
                <a class="text-reset fw-bold">repair-agency.com</a>
            </div>
        </footer>
    </c:when>
    <c:when test="${!sessionScope.role.equals('ENGINEER')}">
        <h2><fmt:message key='PleaseLoginAsMaster'/></h2>
    </c:when>
</c:choose>
</body>
</html>
