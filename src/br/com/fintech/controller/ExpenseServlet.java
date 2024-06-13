package br.com.fintech.controller;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.bean.Expense;
import br.com.fintech.bean.DBException;
import br.com.fintech.dao.implementation.ExpenseDao;
import br.com.fintech.factory.DaoFactory;

@WebServlet("/despesas")
public class ExpenseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ExpenseDao expenseDao;
    
    public void init() throws ServletException {
        super.init();
        expenseDao = DaoFactory.getExpenseDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("createExpense".equals(action)) {
            try {
                createExpense(request, response);
                request.setAttribute("success", "Despesa criada com sucesso");
            } catch (Exception e) {
                request.setAttribute("error", "Erro ao processar requisição: " + e.getMessage());
            }
        }

        listExpenses(request, response);
    }

    private void createExpense(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String description = request.getParameter("description");
            double value = Double.parseDouble(request.getParameter("value"));
            int installments = Integer.parseInt(request.getParameter("installments"));
            boolean fixed = Boolean.parseBoolean(request.getParameter("fixed"));
            boolean paidStatus = Boolean.parseBoolean(request.getParameter("paidStatus"));
            OffsetDateTime expenseDate = OffsetDateTime.parse(request.getParameter("expenseDate"));

            Expense expense = new Expense(0, 0, value, description, fixed, paidStatus, null, installments, expenseDate);
            expenseDao.insert(expense);

        } catch (NumberFormatException | DBException | DateTimeParseException e) {
            throw new ServletException("Erro ao criar despesa", e);
        }
    }
        
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            deleteExpense(request, response);
        }

        listExpenses(request, response);
    }

    private void listExpenses(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Expense> expenses = expenseDao.getAll();
            request.setAttribute("expenses", expenses);
        } catch (DBException e) {
            request.setAttribute("error", "Erro ao listar despesas: " + e.getMessage());
        }
        
        
        request.getRequestDispatcher("/despesas.jsp").forward(request, response);
    }

    private void deleteExpense(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int expenseCode = Integer.parseInt(request.getParameter("code"));
            expenseDao.delete(expenseCode);
            request.setAttribute("success", "Despesa deletada com sucesso");
        } catch (NumberFormatException | DBException e) {
            request.setAttribute("error", "Erro ao deletar despesa: " + e.getMessage());
        }

        listExpenses(request, response);
    }
}
