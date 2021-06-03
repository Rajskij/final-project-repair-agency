<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
    <style>
        html,
        body {
            height: 100%;
        }

        body {
            display: flex;
            align-items: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: auto;
        }

        .form-signin {
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

        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
    </style>
</head>
<body class="text-center">
<main class="form-signin">
    <form action="success-page" method="post">
        <img class="mb-4" src="https://w7.pngwing.com/pngs/118/466/png-transparent-maintenance-computer-icons-innovation-repair-angle-service-logo.png"
             alt="" width="100">
        <h1 class="h3 mb-3 fw-normal">Register your account</h1>
        <div class="form-floating">
            <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" name="email">
            <label for="floatingInput">Email address</label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control" id="login" placeholder="login" name="login">
            <label for="floatingInput">Login</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password">
            <label for="floatingPassword">Password</label>
        </div>

        <input class="btn btn-primary" type="hidden" name="command" value="registerSuccess">
        <input class="w-100 btn btn-lg btn-primary" type="submit" value="Sign in">
        <p class="mt-5 mb-3 text-muted">&copy; 2021</p>
    </form>
</main>
<!--<form action="success-page" method="post">
    <input type="hidden" name="command" value="registerSuccess">
    <h1>Please register account</h1>
    <p>Login</p><input type="text" name="login" value="user">
    <br>
    <p>Password</p><input type="password" name="password">
    <br>
    <input type="submit" value="Submit">
</form>-->
</body>
</html>