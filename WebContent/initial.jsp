<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FINTECH</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: url('img/background-initial.png') no-repeat center center fixed;
            background-size: cover;
        }
        .navbar-brand {
            font-weight: bold;
        }
        .navbar {
            background-color: rgba(255, 255, 255, 0.8);
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light">
    <a class="navbar-brand" href="#">FINTECH</a>
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <button class="btn btn-primary mr-2" onclick="location.href='create-user.jsp'">Cadastrar</button>
            </li>
            <li class="nav-item">
                <button class="btn btn-secondary" onclick="location.href='login.jsp'">Login</button>
            </li>
        </ul>
    </div>
</nav>
</body>
</html>

