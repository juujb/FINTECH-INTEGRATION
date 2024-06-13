package br.com.fintech.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.bean.User;
import br.com.fintech.dao.implementation.UserDao;
import br.com.fintech.bean.DBException;
import br.com.fintech.factory.DaoFactory;

@WebServlet("/user-register")
public class UserRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private UserDao dao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		dao = DaoFactory.getUserDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			String name = request.getParameter("nome");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			User user = new User(name, email, password); 
			dao.createUser(user);
			
			request.setAttribute("msg", "Usuário cadastrado!");
		}catch(DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("erro","Por favor, valide os dados");
		}
		request.getRequestDispatcher("register-user.jsp").forward(request, response);
	}

}