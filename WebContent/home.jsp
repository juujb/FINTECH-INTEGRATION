<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-BR">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Carteira</title>
    <%@ include file="header.jsp" %>

    <style>
       
        body {
            font-family: Arial, sans-serif;
        }
       
    </style>
</head>
<body>
	<%@ include file="menu.jsp" %>
<div class="container">
  <div class="row justify-content-center">
    <div class="col-12 text-center mb-4">
      <h1>Carteira</h1>
    </div>
    <div class="col-md-6 text-center">
      <a href="revenue.jsp" class="btn btn-primary d-block mb-3">Receitas</a>
      <a href="expenses.jsp" class="btn btn-primary d-block mb-3">Despesas</a>
    </div>
  </div>
</div>


    <%@ include file="footer.jsp" %>
</body>
</html>