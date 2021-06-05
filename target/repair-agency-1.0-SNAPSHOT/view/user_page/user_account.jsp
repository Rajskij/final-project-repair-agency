<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>

<html>
<head>
    <title>User</title>
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

        .title {
            max-width: 50px;
            word-wrap: break-word;
            /*overflow: hidden;*/
            /*text-overflow: ellipsis;*/
        }
    </style>
</head>
<body class="container">
<c:choose>
    <c:when test="${sessionScope.role.equals('USER')}">
        <header class="d-flex flex-wrap justify-content-between py-3 mb-4 border-bottom">

            <div class="d-flex justify-content-between">
                <h3 class="fw-normal"><fmt:message key='YourCurrentAmount'/> ${wallet} $</h3>
            </div>
            <div class="col-md-3 d-flex justify-content-end">
                <form action="open-invoice" method="post">
                    <input type="hidden" name="command" value="openNewInvoice">
                    <input type="submit" value="<fmt:message key='AddNewInvoice'/>" class="btn btn-outline-primary me-2">
                </form>
                <form action="/repair" method="post">
                    <input type="submit" value="<fmt:message key='MainPage'/>" class="btn btn-outline-primary me-2">
                </form>
                <form action="repair" method="post">
                    <input type="hidden" name="command" value="logOut" class="m-r-2">
                    <input type="submit" value="<fmt:message key='LogOut'/>" class="btn btn-primary me-2">
                </form>
            </div>
        </header>
        <c:choose>
            <c:when test="${requestScope.invoiceList.size() == 0}">
                <h3><fmt:message key='YouHaveNoInvoice'/></h3>
            </c:when>
            <c:otherwise>
                <h3><fmt:message key='InvoiceTable'/></h3>
                <table class="table table-bordered sortable">
                    <thead>
                    <tr>
                        <th class="title"><fmt:message key='Brand'/></th>
                        <th class="title"><fmt:message key='Model'/></th>
                        <th class="title"><fmt:message key='ProblemDescription'/></th>
                        <th class="title"><fmt:message key='Price'/></th>
                        <th class="title"><fmt:message key='Status'/></th>
                        <th class="title"><fmt:message key='Engineer'/></th>
                        <th class="title"><fmt:message key='LeaveFeedback'/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>

                        <c:forEach var="invoiceList" items="${invoiceList}">
                    <tr>
                        <td class="title"> ${invoiceList.brand} </td>
                        <td class="title">${invoiceList.model}</td>
                        <td class="title">${invoiceList.description}</td>
                        <th class="title">${invoiceList.price}</th>
                        <td class="title">${invoiceList.status}</td>
                        <td class="title">${invoiceList.engineer}</td>
                        <td class="title">
                            <c:choose>
                                <c:when test="${invoiceList.status.equals('Done')}">
                                    <form action="feedback" method="get">
                                        <input type="hidden" name="invoiceBrand" value="${invoiceList.brand}">
                                        <input type="hidden" name="invoiceModel" value="${invoiceList.model}">
                                        <input type="hidden" name="invoiceId" value="${invoiceList.id}">
                                        <input type="hidden" name="command" value="feedback">
                                        <input type="submit" value="<fmt:message key='Feedback'/>" class="btn btn-outline-primary me-2">
                                    </form>
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
        <footer>
            <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05);">
                © 2021 Copyright:
                <a class="text-reset fw-bold">repair-agency.com</a>
            </div>
        </footer>
    </c:when>
    <c:when test="${!sessionScope.role.equals('USER')}">
        <h2>Please login as user</h2>
    </c:when>
</c:choose>
</body>
</html>
