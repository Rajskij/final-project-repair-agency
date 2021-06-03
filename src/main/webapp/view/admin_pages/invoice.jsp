<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Invoice</title>
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
    <c:when test="${sessionScope.role.equals('ADMIN') || sessionScope.role.equals('ENGINEER')}">
        <header class="d-flex flex-wrap justify-content-end py-3 mb-4 border-bottom">
            <div class="col-md-3 d-flex justify-content-end">
                <c:choose>
                    <c:when test="${sessionScope.role.equals('ADMIN')}">
                        <form action="top_up_account" method="post">
                            <input type="hidden" name="command" value="topUpAccount">
                            <input type="submit" value="top up account" class="btn btn-outline-primary me-2">
                        </form>
                        <form action="adminPage" method="post" class="m-r-2">
                            <input type="hidden" name="command" value="adminPage">
                            <input type="submit" value="back to list" class="btn btn-primary">
                        </form>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${sessionScope.role.equals('ENGINEER')}">
                        <form action="engineerPage" method="post" class="m-r-2">
                            <input type="hidden" name="command" value="adminPage">
                            <input type="submit" value="back to list" class="btn btn-primary">
                        </form>
                    </c:when>
                </c:choose>
            </div>
        </header>
        <h3>Invoice</h3>
        <table class="table table-bordered sortable">
            <tr>
                <th class="title">Position</th>
                <th class="title">Current values</th>
                <th class="title">Editable values</th>
            </tr>
            <tr>
                <th class="title">Id</th>
                <td class="title">${invoice.id}</td>
                <td class="title"></td>
            </tr>
            <tr>
                <th class="title">User</th>
                <td class="title">${invoice.user}</td>
                <td class="title"></td>
            </tr>
            <tr>
                <th class="title">Brand</th>
                <td class="title">${invoice.brand}</td>
                <td class="title"></td>
            </tr>
            <tr>
                <th class="title">Model</th>
                <td class="title">${invoice.model}</td>
                <td class="title"></td>
            </tr>
            <tr>
                <th class="title">Description</th>
                <td class="title">${invoice.description}</td>
                <td class="title"></td>
            </tr>
            <tr>
                <th class="title">Price</th>
                <td class="title">${invoice.price}</td>
                <td>
                    <c:choose>
                        <c:when test="${sessionScope.role.equals('ADMIN')}">
                            <form action="editPrice" method="post">
                                <input type="hidden" name="command" value="selectInvoice">
                                <input type="hidden" name="invoiceId" value="${invoice.id}">
                                <div class="form-floating d-flex flex-grow-1">
                                    <input name="price" type="number" class="form-control h-25" id="floatingInput">
                                    <label for="floatingInput">put price</label>
                                    <input type="submit" value="Set price" class="h-25 btn btn-primary">
                                </div>
                            </form>
                        </c:when>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <th class="title">Feedback</th>
                <td class="title">${invoice.feedback}</td>
                <td class="title"></td>
            </tr>
            <tr>
                <th class="title">Status</th>
                <td class="title">${invoice.status}</td>
                <td>
                    <c:choose>
                        <c:when test="${!requestScope.invoice.status.equals('Done')
                        && !requestScope.invoice.status.equals('In work')
                        && sessionScope.role.equals('ADMIN')}">
                            <div class="dropdown">
                                <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownStatusButton"
                                        data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Select status
                                </button>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <form action="editStatus" method="post">
                                        <input type="hidden" name="command" value="selectInvoice">
                                        <input type="hidden" name="invoiceId" value="${invoice.id}">
                                        <input class="dropdown-item" type="submit" name="status"
                                               value="Payment expected">
                                    </form>
                                    <form action="editStatus" method="post">
                                        <input type="hidden" name="command" value="selectInvoice">
                                        <input type="hidden" name="invoiceId" value="${invoice.id}">
                                        <input class="dropdown-item" type="submit" name="status" value="Paid">
                                    </form>
                                    <form action="editStatus" method="post">
                                        <input type="hidden" name="command" value="selectInvoice">
                                        <input type="hidden" name="invoiceId" value="${invoice.id}">
                                        <input class="dropdown-item" type="submit" name="status" value="Canceled">
                                    </form>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${!requestScope.invoice.status.equals('Payment expected')
                        && !requestScope.invoice.status.equals('Canceled')
                        && sessionScope.role.equals('ENGINEER')}">
                            <div class="dropdown">
                                <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownStatusButton"
                                        data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Select status
                                </button>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <form action="editStatus" method="post">
                                        <input type="hidden" name="command" value="selectInvoice">
                                        <input type="hidden" name="invoiceId" value="${invoice.id}">
                                        <input class="dropdown-item" type="submit" name="status"
                                               value="In work">
                                    </form>
                                    <form action="editStatus" method="post">
                                        <input type="hidden" name="command" value="selectInvoice">
                                        <input type="hidden" name="invoiceId" value="${invoice.id}">
                                        <input class="dropdown-item" type="submit" name="status" value="Done">
                                    </form>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <th class="title">Engineer</th>
                <td class="title">${invoice.engineer}</td>
                <td>
                    <c:choose>
                        <c:when test="${sessionScope.role.equals('ADMIN')}">
                            <div class="dropdown">
                                <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownEngineerButton"
                                        data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Select engineers
                                </button>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <c:forEach var="engineer" items="${engineerList}">
                                        <form action="editEngineer" method="post">
                                            <input type="hidden" name="command" value="selectInvoice">
                                            <input type="hidden" name="engineerId" value="${engineer.id}">
                                            <input type="hidden" name="invoiceId" value="${invoice.id}">
                                            <input class="dropdown-item" type="submit" value="${engineer.login}">
                                        </form>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>
                </td>
            </tr>
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
