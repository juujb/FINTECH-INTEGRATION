package br.com.fintech.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fintech.bean.DBException;
import br.com.fintech.bean.Wallet;
import br.com.fintech.dao.interfaces.GenericDao;

public class WalletDao implements GenericDao<Wallet> {
	
	private Connection connection;
	
	public WalletDao() {
		super();
    }

	public void insert(Wallet wallet) throws DBException {
        connection = OracleConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String query = "INSERT INTO T_CARTEIRA (CD_CARTEIRA, CD_USUARIO, NM_CARTEIRA, DS_CARTEIRA) VALUES (SQ_CARTEIRA.NEXTVAL, ?, ?, ?)";
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, wallet.getUserCode());
			stmt.setString(2, wallet.getName());
			stmt.setString(3, wallet.getDescription());
			
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DBException("Erro ao criar carteira.");
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
	
	public ArrayList<Wallet> getAll() throws DBException {
        connection = OracleConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		var walletList = new ArrayList<Wallet>();
		
		try {
			String query = "SELECT * FROM T_CARTEIRA";
			stmt = connection.prepareStatement(query);
			ResultSet rst = stmt.executeQuery();
			
			while(rst.next()) {
				int code = rst.getInt(1);
				int userCode = rst.getInt(2);
				String name = rst.getString(3);
				String description = rst.getString(4);
	
				try {
					Wallet wallet = new Wallet(
							code, 
							userCode,
							name,
							description
					);
					
					walletList.add(wallet);
				} catch(Exception ex) {
					ex.printStackTrace();
					throw new DBException("Erro ao mapear carteira.");
				}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DBException("Erro ao listar carteira.");
		}finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new DBException("Erro ao fechar conexão.");
			}
		}
	
		return walletList;
	}
	
	public void delete(int code) throws DBException {
        connection = OracleConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String query = "DELETE FROM T_CARTEIRA WHERE CD_CARTEIRA = ?";
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, code);
			
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DBException("Erro ao deletar carteira.");
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
