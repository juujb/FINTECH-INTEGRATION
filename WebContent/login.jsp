<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<%@ include file="header.jsp" %>
</head>
<body>
	<div class="d-flex align-items-center justify-content-center">
		<div class="text-center">
			<h1 class="mt-5 mb-4">JSP - FINTECH</h1>
			<form class="form-group mb-3" action="login" method="post">
		    	<input class="form-control mb-3" type="text" name="email" placeholder="E-mail">
		        <input class="form-control mb-4" type="password" name="senha" placeholder="Senha">
		        <div class="row">
			      	<button class="btn btn-outline-success mb-2" type="submit">Entrar</button>
			      	<a class="btn btn-primary" href="register-user.jsp">Cadastre-se</a>
		        </div>
		    </form>
		</div>
	</div>
<%@ include file="footer.jsp" %>
</body>
</html>