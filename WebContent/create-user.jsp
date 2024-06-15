<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usuário</title>
<%@ include file="header.jsp" %>
</head>
<body>
<div class="container">
	<h1 class="text-center mt-3">Cadastro</h1>
	<form action="create-user" method="post" class="mx-auto">
		<div class="form-group mb-3">
			<label for="id-nome">Nome</label>
			<input type="text" name="nome" id="id-nome" class="form-control">
		</div>
		<div class="form-group mb-3">
			<label for="id-email">Email</label>
			<input type="text" name="email" id="id-email" class="form-control">
		</div>
		<div class="form-group mb-4">
			<label for="id-password">Senha</label>
			<input type="password" name="password" id="id-password" class="form-control">
		</div>
		<div class="form-group text-center">
			<input type="submit" value="Cadastrar" class="btn btn-primary btn-lg w-50">
		</div>
	</form>
	<c:if test="${not empty error}">
		<div class="alert alert-danger" role="alert">${error}</div>
	</c:if>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>