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
import br.com.fintech.bean.DBException;
import br.com.fintech.factory.DaoFactory;

@WebServlet("/usuario")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private UserDao dao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		dao = DaoFactory.getUserDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("login".equals(action)) {
            login(request, response);
        }
        
		createUser(request, response);
	}
	
	private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String name = request.getParameter("nome");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			User user = new User(name, email, password); 
			dao.createUser(user);
			
			request.setAttribute("msg", "Bem vindo!");
		}catch(DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("erro","Por favor, valide os dados");
		}
		
		request.getRequestDispatcher("register-user.jsp").forward(request, response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = new User(email, password); 

		if (dao.auth(user)) {
			request.setAttribute("msg", "Bem vindo!");
			HttpSession session = request.getSession();
			session.setAttribute("user", email);
		} else {
			request.setAttribute("erro", "Usuário e/ou senha inválidos");
		}
			
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
}