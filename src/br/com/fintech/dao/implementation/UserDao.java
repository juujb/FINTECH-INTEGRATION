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

	public boolean auth(User user) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	        connection = OracleConnectionManager.getInstance().getConnection();
			stmt = connection.prepareStatement("SELECT * FROM T_USUARIO WHERE DS_EMAIL = ? AND DS_SENHA = ?");
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());
			rs = stmt.executeQuery();

			if (rs.next()){
				stmt.close();
				rs.close();
				connection.close();
				
				return true;
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
		
		return false;
	}

	public void createUser(User user) throws DBException {
        connection = OracleConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String query = "INSERT INTO T_USUARIO (CD_USUARIO, NM_USUARIO, DS_EMAIL, DS_SENHA, DS_CPF, DS_TELEFONE) VALUES (SQ_USUARIO.NEXTVAL, ?, ?, ?, ?, ?)";
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, user.getUserCode());
			stmt.setString(2, user.getName());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPassword());
			stmt.setString(5, user.getCpf());
			stmt.setString(6, user.getPhoneNumber());
			
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