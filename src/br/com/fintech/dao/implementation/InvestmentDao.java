package br.com.fintech.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

import br.com.fintech.bean.DBException;
import br.com.fintech.bean.Investment;
import br.com.fintech.dao.interfaces.GenericDao;

public class InvestmentDao implements GenericDao<Investment> {
	
	private Connection connection;
	private final ZoneOffset zoneOffset;
	
	public InvestmentDao() {
		super();
        zoneOffset = ZoneOffset.of("+03:00");
    }

	public void insert(Investment investment) throws DBException {
        connection = OracleConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String query = "INSERT INTO T_INVESTIMENTO (CD_INVESTIMENTO, CD_CARTEIRA, CD_USUARIO, DS_INVESTIMENTO, VL_INVESTIMENTO, DT_CRIACAO, DT_EFETIVACAO, ST_FIXA, TP_INVESTIMENTO) VALUES (SQ_INVESTIMENTO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, investment.getWalletCode());
			stmt.setInt(2, investment.getUserCode());
			stmt.setString(3, investment.getDescription());
			stmt.setDouble(4, investment.getValue());
			
			Instant currentDate = java.time.OffsetDateTime.now(zoneOffset).toInstant();
			java.sql.Date creationDate = new java.sql.Date(currentDate.toEpochMilli());
			stmt.setDate(5, creationDate);
			
			if (investment.getEfetivationDate() != null) {				
				java.sql.Date efetivationDate = new java.sql.Date(investment.getEfetivationDate().toInstant().toEpochMilli());
				stmt.setDate(6, efetivationDate);
			}
			
			stmt.setBoolean(7, investment.isFixed());
			stmt.setString(8, investment.getInvestmentType());
			
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DBException("Erro ao criar investimento.");
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
	
	public ArrayList<Investment> getAll() throws DBException {
        connection = OracleConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		var investmentList = new ArrayList<Investment>();
		
		try {
			String query = "SELECT * FROM T_INVESTIMENTO";
			stmt = connection.prepareStatement(query);
			ResultSet rst = stmt.executeQuery();
			
			while(rst.next()) {
				int code = rst.getInt(1);
				int walletCode = rst.getInt(2);
				int userCode = rst.getInt(3);
				String description = rst.getString(4);
				double value = rst.getDouble(5);
				long createdDate = rst.getDate(6).getTime();
				long efetivationDate = rst.getDate(7).getTime();
				boolean fixed = rst.getBoolean(8);
				String investmentType = rst.getString(9);
	
				try {
					Investment investment = new Investment(
							code, 
							userCode, 
							walletCode, 
							value, 
							description, 
							fixed, 
							OffsetDateTime.ofInstant(Instant.ofEpochMilli(efetivationDate), zoneOffset),
							OffsetDateTime.ofInstant(Instant.ofEpochMilli(createdDate), zoneOffset),
							investmentType
					);
					
					investmentList.add(investment);
				} catch(Exception ex) {
					ex.printStackTrace();
					throw new DBException("Erro ao mapear investimento.");
				}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DBException("Erro ao listar investimentos.");
		}finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new DBException("Erro ao fechar conexão.");
			}
		}
	
		return investmentList;
	}
	
	public ArrayList<Investment> getByWalletCode(int code) throws DBException {
        connection = OracleConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		var investmentList = new ArrayList<Investment>();
		
		try {
			String query = "SELECT * FROM T_INVESTIMENTO WHERE CD_CARTEIRA = ?";
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, code);
			
			ResultSet rst = stmt.executeQuery();
			
			while(rst.next()) {
				int investmentCode = rst.getInt(1);
				int walletCode = rst.getInt(2);
				int userCode = rst.getInt(3);
				String description = rst.getString(4);
				double value = rst.getDouble(5);
				long createdDate = rst.getDate(6).getTime();
				long efetivationDate = rst.getDate(7).getTime();
				boolean fixed = rst.getBoolean(8);
				String investmentType = rst.getString(9);
	
				try {
					Investment investment = new Investment(
							investmentCode, 
							userCode, 
							walletCode, 
							value, 
							description, 
							fixed, 
							OffsetDateTime.ofInstant(Instant.ofEpochMilli(efetivationDate), zoneOffset),
							OffsetDateTime.ofInstant(Instant.ofEpochMilli(createdDate), zoneOffset),
							investmentType
					);
					
					investmentList.add(investment);
				} catch(Exception ex) {
					ex.printStackTrace();
					throw new DBException("Erro ao mapear investimento.");
				}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DBException("Erro ao listar investimentos.");
		}finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new DBException("Erro ao fechar conexão.");
			}
		}
	
		return investmentList;
	}
	
	public void delete(int code) throws DBException {
        connection = OracleConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String query = "DELETE FROM T_INVESTIMENTO WHERE CD_INVESTIMENTO = ?";
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, code);
			
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DBException("Erro ao deletar investimento.");
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
