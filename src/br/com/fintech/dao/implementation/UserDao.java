package br.com.fintech.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fintech.bean.DBException;
import br.com.fintech.bean.User;
import br.com.fintech.dao.interfaces.UserDaoInterface;

public class UserDao implements UserDaoInterface {
	private Connection connection;

	public User auth(User user) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	        connection = OracleConnectionManager.getInstance().getConnection();
			stmt = connection.prepareStatement("SELECT * FROM T_USUARIO WHERE DS_EMAIL = ? AND DS_SENHA = ?");
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());
			rs = stmt.executeQuery();

			if (rs.next()){
				int userCode = rs.getInt(1);
				String name = rs.getString(2);
				
				User userAuth = new User(userCode, name, null, null);
				
				stmt.close();
				rs.close();
				connection.close();
				
				return userAuth;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return new User();
	}

	public void createUser(User user) throws DBException {
        connection = OracleConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String query = "INSERT INTO T_USUARIO (CD_USUARIO, NM_USUARIO, DS_EMAIL, DS_SENHA) VALUES (SQ_USUARIO.NEXTVAL, ?, ?, ?)";
			stmt = connection.prepareStatement(query);
			
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DBException("Erro ao criar novo usuário.");
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new DBException("Erro ao fechar conexão.");
			}
		}
	}

}