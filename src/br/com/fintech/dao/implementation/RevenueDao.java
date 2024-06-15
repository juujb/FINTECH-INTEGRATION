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
import br.com.fintech.bean.Revenue;
import br.com.fintech.dao.interfaces.GenericDao;

public class RevenueDao implements GenericDao<Revenue> {
	
	private Connection connection;
	private final ZoneOffset zoneOffset;
	
	public RevenueDao() {
		super();
        zoneOffset = ZoneOffset.of("+03:00");
    }

	public void insert(Revenue revenue) throws DBException {
        connection = OracleConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String query = "INSERT INTO T_RECEITA (CD_RECEITA, CD_USUARIO, DS_RECEITA, VL_RECEITA, DT_CRIACAO, DT_EFETIVACAO, ST_FIXA, TP_ENTRADA) VALUES (SQ_RECEITA.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, revenue.getUserCode());
			stmt.setString(2, revenue.getDescription());
			stmt.setDouble(3, revenue.getValue());
			
			Instant currentDate = java.time.OffsetDateTime.now(zoneOffset).toInstant();
			java.sql.Date creationDate = new java.sql.Date(currentDate.toEpochMilli());
			stmt.setDate(4, creationDate);
			
			if (revenue.getEfetivationDate() != null) {				
				java.sql.Date efetivationDate = new java.sql.Date(revenue.getEfetivationDate().toInstant().toEpochMilli());
				stmt.setDate(5, efetivationDate);
			}
			
			stmt.setBoolean(6, revenue.isFixed());
			stmt.setString(7, revenue.getTypeOfEntry());
			
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DBException("Erro ao criar receita.");
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
	
	public ArrayList<Revenue> getAll() throws DBException {
        connection = OracleConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		var revenueList = new ArrayList<Revenue>();
		
		try {
			String query = "SELECT * FROM T_RECEITA";
			stmt = connection.prepareStatement(query);
			ResultSet rst = stmt.executeQuery();
			
			while(rst.next()) {
				int code = rst.getInt(1);
				int userCode = rst.getInt(2);
				String description = rst.getString(3);
				double value = rst.getDouble(4);
				long createdDate = rst.getDate(5).getTime();
				long efetivationDate = rst.getDate(6).getTime();
				boolean fixed = rst.getBoolean(7);
				String typeOfEntry = rst.getString(8);
	
				try {
					Revenue revenue = new Revenue(
							code, 
							userCode, 
							value, 
							description, 
							fixed, 
							OffsetDateTime.ofInstant(Instant.ofEpochMilli(efetivationDate), zoneOffset),
							OffsetDateTime.ofInstant(Instant.ofEpochMilli(createdDate), zoneOffset),
							typeOfEntry
					);
					
					revenueList.add(revenue);
				} catch(Exception ex) {
					ex.printStackTrace();
					throw new DBException("Erro ao mapear receita.");
				}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DBException("Erro ao listar receitas.");
		}finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new DBException("Erro ao fechar conexão.");
			}
		}
	
		return revenueList;
	}
	
	public ArrayList<Revenue> getByUser(int code) throws DBException {
        connection = OracleConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		var revenueList = new ArrayList<Revenue>();
		
		try {
			String query = "SELECT * FROM T_RECEITA WHERE CD_USUARIO = ?";
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, code);
			stmt = connection.prepareStatement(query);
			ResultSet rst = stmt.executeQuery();
			
			while(rst.next()) {
				int revenueCode = rst.getInt(1);
				int userCode = rst.getInt(2);
				String description = rst.getString(3);
				double value = rst.getDouble(4);
				long createdDate = rst.getDate(5).getTime();
				long efetivationDate = rst.getDate(6).getTime();
				boolean fixed = rst.getBoolean(7);
				String typeOfEntry = rst.getString(8);
	
				try {
					Revenue revenue = new Revenue(
							revenueCode,
							userCode, 
							value, 
							description, 
							fixed, 
							OffsetDateTime.ofInstant(Instant.ofEpochMilli(efetivationDate), zoneOffset),
							OffsetDateTime.ofInstant(Instant.ofEpochMilli(createdDate), zoneOffset),
							typeOfEntry
					);
					
					revenueList.add(revenue);
				} catch(Exception ex) {
					ex.printStackTrace();
					throw new DBException("Erro ao mapear receita.");
				}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DBException("Erro ao listar receitas.");
		}finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new DBException("Erro ao fechar conexão.");
			}
		}
	
		return revenueList;
	}
	
	public void delete(int code) throws DBException {
        connection = OracleConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String query = "DELETE FROM T_RECEITA WHERE CD_RECEITA = ?";
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, code);
			
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DBException("Erro ao deletar receita.");
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
