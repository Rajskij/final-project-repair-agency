<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>

<html>
<head>
    <title>Thank you!</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/sorttable.js"
            language="JavaScript"></script>
</head>
<body>
<style>
    * {
        margin: 0;
        padding: 0;
    }

    .container {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        margin: 100px;
        max-width: 700px;
        height: 650px;
        border-radius: 45px;
        background-color: #f6f6f6;
        box-shadow: 10px 5px 5px lightgray;
    }

    .btn {
        margin-top: 10px;
        padding: 10px;
        width: 300px;
        font-size: 22px;
    }

    .text-center {
        margin-bottom: 20px;
        color: #565c5c;
    }

    .background {
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .oops {
        margin-bottom: 25px;
        height: 250px;
    }
</style>
<div class="background">
    <div class="container">
        <h1 class="text-center"><fmt:message key='ThanksForYourFeedback'/></h1><br>
        <img class="oops" src="https://www.pngkey.com/png/detail/85-852828_cat-friendly-properties-happy-cat.png">
        <h4 class="text-center"><fmt:message key='HopeToSeeYouAgain'/></h4>
        <h4 class="text-center"><fmt:message key='ClickTheButtonBellow'/></h4>
        <a href="/repair/" class="btn btn-primary"><fmt:message key='GoBackToMainPage'/></a>
    </div>
</div>
</body>
</html>