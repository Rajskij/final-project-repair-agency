<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>

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
    <c:when test="${sessionScope.role.equals('USER')}">
        <header class="d-flex flex-wrap justify-content-end py-3 mb-4 border-bottom">
            <div class="col-md-3 d-flex justify-content-end">
                <form action="engineerPage" method="post" class="m-r-2">
                    <input type="hidden" name="command" value="userPage">
                    <input type="submit" value="<fmt:message key="BackToList"/>" class="btn btn-primary">
                </form>
            </div>
        </header>
        <h3><fmt:message key="CreateInvoice"/></h3>
        <form action="account" method="post">
            <table class="table table-bordered sortable">
                <tr>
                    <th class="title"><fmt:message key='Position'/></th>
                    <th class="title"><fmt:message key='Values'/></th>
                </tr>
                <tr>
                    <th class="title"><fmt:message key='Brand'/></th>
                    <td class="title">
                        <div class="form-floating d-flex flex-grow-1">
                            <input name="brand" type="text" class="form-control" id="brandInput" required>
                            <label for="brandInput"><fmt:message key='InputYourBrand'/></label>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th class="title"><fmt:message key='Model'/></th>
                    <td class="title">
                        <div class="form-floating d-flex flex-grow-1">
                            <input name="model" type="text" class="form-control" id="modelInput" required>
                            <label for="modelInput"><fmt:message key='InputYourModel'/></label>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th class="title"><fmt:message key='Description'/></th>
                    <td class="title">
                        <div class="form-floating d-flex flex-grow-1">
                            <input name="description" type="text" class="form-control" id="descriptionInput" required>
                            <label for="descriptionInput"><fmt:message key='InputYourDescription'/></label>
                        </div>
                    </td>
                </tr>
            </table>
            <input type="hidden" name="command" value="addNewInvoice">
            <input type="submit" value="<fmt:message key='Submit'/>" class="btn btn-primary w-25">
        </form>
        <footer>
            <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05);">
                ?? 2021 Copyright:
                <a class="text-reset fw-bold">repair-agency.com</a>
            </div>
        </footer>
    </c:when>
    <c:when test="${!sessionScope.role.equals('USER')}">
        <h2>Please login as User</h2>
    </c:when>
</c:choose>
</body>
</html>
