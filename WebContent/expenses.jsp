<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-BR">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Despesas</title>
    <%@ include file="header.jsp" %>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
        }
    </style>
</head>
<body>
	<%@ include file="menu.jsp" %>
    <div class="container">
        <h1>Despesas</h1>

        <c:if test="${not empty success}">
            <div class="alert alert-success" role="alert">${success}</div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">${error}</div>
        </c:if>

        <form action="expenses" method="post" class="mb-3">
            <input type="hidden" name="action" value="createExpense">
            <div class="form-row">
                <div class="col-md-2 mb-3">
                    <input type="date" name="dueDate" class="form-control" placeholder="Vencimento" required>
                </div>
                <div class="col-md-3 mb-3">
                    <input type="text" name="description" class="form-control" placeholder="Descri��o" required>
                </div>
                <div class="col-md-2 mb-3">
                    <input type="number" name="value" class="form-control" step="0.01" placeholder="Valor" required>
                </div>
                <div class="col-md-2 mb-3">
                    <input type="number" name="installments" class="form-control" placeholder="Parcelas" required>
                </div>
                <div class="col-md-1 mb-3">
                    <select name="fixed" class="form-control" required>
                        <option value="true">Fixa</option>
                        <option value="false">N�o Fixa</option>
                    </select>
                </div>
                <div class="col-md-2 mb-3">
                    <select name="paidStatus" class="form-control" required>
                        <option value="true">Paga</option>
                        <option value="false">N�o Paga</option>
                    </select>
                </div>
                <div class="col-md-12 mb-3 text-right">
                    <button type="submit" class="btn btn-primary">Adicionar</button>
                </div>
            </div>
        </form>

        <table class="table">
            <thead class="thead-light">
                <tr>
                    <th>Vencimento</th>
                    <th>ID</th>
                    <th>Descri��o</th>
                    <th>Valor</th>
                    <th>Parcelas</th>
                    <th>Fixa</th>
                    <th>Paga</th>
                    <th>A��es</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="expense" items="${expenses}">
                    <tr>
                        <td>${expense.dueDate}</td>
                        <td>${expense.code}</td>
                        <td>${expense.description}</td>
                        <td>R$ ${expense.value}</td>
                        <td>${expense.installments}</td>
                        <td>${expense.fixed ? 'Sim' : 'N�o'}</td>
                        <td>${expense.paidStatus ? 'Sim' : 'N�o'}</td>
                        <td>
                            <form class="delete-form" action="expenses" method="post">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="code" value="${expense.code}">
                                <button type="submit" class="btn btn-danger">Remover</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <%@ include file="footer.jsp" %>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

