<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>

<html>
<head>
    <title>Top up account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <style>
        html, body {
            height: 100%;
        }

        body {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            background-color: #f5f5f5;
        }

        .edit-wallet-form {
            display: flex;
            align-items: flex-start;
            padding-top: 40px;
            padding-bottom: 40px;
        }

        .edit-wallet-form .form-signin {
            margin-top: 150px;
        }

        .form-signin {
            width: 100%;
            max-width: 360px;
            padding: 15px;
            margin: auto;
        }

        .form-signin .checkbox {
            font-weight: 400;
        }

        .form-signin .form-floating:focus-within {
            z-index: 2;
        }

        .form-signin input[type="email"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }

        footer {
            margin-top: auto;
        }

        .con {
            display: flex;
            align-items: start;
            margin: 50px;
            max-width: 700px;
            height: 500px;
            border-radius: 45px;
            background-color: #e9e9e9;
            box-shadow: 10px 5px 5px lightgray;
        }

        .main {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 120px;
        }
    </style>
</head>
<body class="container">
<c:choose>
    <c:when test="${sessionScope.role.equals('ADMIN')}">
        <header class="d-flex flex-wrap justify-content-between py-3 mb-4 border-bottom">
            <div class="d-flex justify-content-between">
                <h3 class="fw-normal"><fmt:message key='Current'/> ${currentUser} <fmt:message
                        key='Wallet'/>: ${currentWallet} $</h3>
            </div>
            <div class="col-md-3 text-end">
                <form action="account" method="post" class="form">
                    <input type="hidden" name="command" value="adminPage">
                    <input type="submit" value="<fmt:message key='BackToList'/>" class="w-75 btn btn-primary">
                </form>
            </div>
        </header>
        <div class="d-flex justify-content-center mt-5">
            <div class="con">
                <main class="main mx-5">
                    <h3 class="my-5">1.<fmt:message key='SelectUser'/></h3>
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                                data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <fmt:message key='ShowUser'/>
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <c:forEach var="users" items="${users}">
                                <form action="wallet" method="post">
                                    <input type="hidden" name="command" value="topUpAccount">
                                    <input type="hidden" name="usersWallet" value="${users.wallet}">
                                    <input type="hidden" name="usersId" value="${users.id}">
                                    <input type="hidden" name="usersLogin" value="${users.login}">
                                    <input class="dropdown-item" type="submit" value="${users.login}">
                                </form>
                            </c:forEach>
                        </div>
                    </div>
                </main>
                <main class="main mx-5">
                    <h3 class="my-5">2.<fmt:message key='Edit'/> ${currentUser} <fmt:message key='Wallet'/></h3>
                    <form action="wallet" method="post">
                        <input type="hidden" name="command" value="topUpAccount">
                        <input type="hidden" name="usersWallet" value="${currentWallet}">
                        <input type="hidden" name="usersId" value="${currentId}">
                        <input type="hidden" name="usersLogin" value="${currentUser}">
                        <div class="form-floating">
                            <input type="number" name="usersEditWallet" class="w-100 form-control" id="floatingInput"
                                   required>
                            <label for="floatingInput">
                                <fmt:message key='Value'/>
                                00.00</label>
                        </div>
                        <input class="w-100 btn btn-lg btn-primary" type="submit"
                               value="<fmt:message key='Submit'/>">
                    </form>
                </main>
            </div>

        </div>
        <footer>
            <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05);">
                ?? 2021 Copyright:
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