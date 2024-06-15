package br.com.fintech.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fintech.bean.User;
import br.com.fintech.dao.implementation.UserDao;
import br.com.fintech.factory.DaoFactory;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private UserDao dao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		dao = DaoFactory.getUserDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User loginUser = new User(email, password);
		
		User validatedUser = dao.auth(loginUser);

		if (validatedUser.getName() != null) {
			request.setAttribute("msg", "Bem vindo!");
			HttpSession session = request.getSession();

			session.setAttribute("userCode", validatedUser.getUserCode());
			session.setAttribute("userName", validatedUser.getName());
			
			response.sendRedirect("home.jsp");
		} else {
			request.setAttribute("error", "Usuário e/ou senha inválidos");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
	
}