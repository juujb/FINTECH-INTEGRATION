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
import br.com.fintech.bean.Expense;
import br.com.fintech.dao.interfaces.GenericDao;

public class ExpenseDao implements GenericDao<Expense> {
	
	private Connection connection;
	private final ZoneOffset zoneOffset;
	
	public ExpenseDao() {
		super();
        zoneOffset = ZoneOffset.of("+03:00");
    }

	public void insert(Expense expense) throws DBException {
        connection = OracleConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String query = "INSERT INTO T_DESPESA (CD_DESPESA, CD_CARTEIRA, CD_USUARIO, DS_DESPESA, VL_DESPESA, NR_PARCELAS, DT_CRIACAO, DT_EFETIVACAO, DT_VENCIMENTO, ST_FIXA, ST_PAGA) VALUES (SQ_DESPESA.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, expense.getWalletCode());
			stmt.setInt(2, expense.getUserCode());
			stmt.setString(3, expense.getDescription());
			stmt.setDouble(4, expense.getValue());
			stmt.setInt(5, expense.getInstallments());
			
			Instant currentDate = java.time.OffsetDateTime.now(zoneOffset).toInstant();
			java.sql.Date creationDate = new java.sql.Date(currentDate.toEpochMilli());
			stmt.setDate(6, creationDate);
			
			if (expense.getEfetivationDate() != null) {				
				java.sql.Date efetivationDate = new java.sql.Date(expense.getEfetivationDate().toInstant().toEpochMilli());
				stmt.setDate(7, efetivationDate);
			}
			
			if (expense.getDueDate() != null) {				
				java.sql.Date dueDate = new java.sql.Date(expense.getDueDate().toInstant().toEpochMilli());
				stmt.setDate(8, dueDate);			
			}
			
			stmt.setBoolean(9, expense.isFixed());
			stmt.setBoolean(10, expense.getPaidStatus());
			
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DBException("Erro ao criar despesa.");
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
	
	public ArrayList<Expense> getAll() throws DBException {
        connection = OracleConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		var expenseList = new ArrayList<Expense>();
		
		try {
			String query = "SELECT * FROM T_DESPESA";
			stmt = connection.prepareStatement(query);
			ResultSet rst = stmt.executeQuery();
			
			while(rst.next()) {
				int expenseCode = rst.getInt(1);
				int walletCode = rst.getInt(2);
				int userCode = rst.getInt(3);
				String description = rst.getString(4);
				double expenseValue = rst.getDouble(5);
				int installments = rst.getInt(6);
				long createdDate = rst.getDate(7).getTime();
				long efetivationDate = rst.getDate(8).getTime();
				long dueDate = rst.getDate(9).getTime();
				boolean isFixed = rst.getBoolean(10);
				boolean paidStatus = rst.getBoolean(11);
	
				try {
					Expense expense = new Expense(
							expenseCode,
							userCode,
							walletCode,
							expenseValue,
							description,
							isFixed,
							paidStatus,
							OffsetDateTime.ofInstant(Instant.ofEpochMilli(efetivationDate), zoneOffset),
							OffsetDateTime.ofInstant(Instant.ofEpochMilli(createdDate), zoneOffset),
							installments,
							OffsetDateTime.ofInstant(Instant.ofEpochMilli(dueDate), zoneOffset)
					);
					
					expenseList.add(expense);
				} catch(Exception ex) {
					ex.printStackTrace();
					throw new DBException("Erro ao mapear despesa.");
				}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DBException("Erro ao listar despesas.");
		}finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new DBException("Erro ao fechar conexão.");
			}
		}
	
		
		return expenseList;
	}
	
	public ArrayList<Expense> getByWalletCode(int code) throws DBException {
        connection = OracleConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		var expenseList = new ArrayList<Expense>();
		
		try {
			String query = "SELECT * FROM T_DESPESA WHERE CD_CARTEIRA = ?";
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, code);
			
			ResultSet rst = stmt.executeQuery();
			
			while(rst.next()) {
				int expenseCode = rst.getInt(1);
				int walletCode = rst.getInt(2);
				int userCode = rst.getInt(3);
				String description = rst.getString(4);
				double expenseValue = rst.getDouble(5);
				int installments = rst.getInt(6);
				long createdDate = rst.getDate(7).getTime();
				long efetivationDate = rst.getDate(8).getTime();
				long dueDate = rst.getDate(9).getTime();
				boolean isFixed = rst.getBoolean(10);
				boolean paidStatus = rst.getBoolean(11);
	
				try {
					Expense expense = new Expense(
							expenseCode,
							userCode,
							walletCode,
							expenseValue,
							description,
							isFixed,
							paidStatus,
							OffsetDateTime.ofInstant(Instant.ofEpochMilli(efetivationDate), zoneOffset),
							OffsetDateTime.ofInstant(Instant.ofEpochMilli(createdDate), zoneOffset),
							installments,
							OffsetDateTime.ofInstant(Instant.ofEpochMilli(dueDate), zoneOffset)
					);
					
					expenseList.add(expense);
				} catch(Exception ex) {
					ex.printStackTrace();
					throw new DBException("Erro ao mapear despesa.");
				}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DBException("Erro ao listar despesas.");
		}finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new DBException("Erro ao fechar conexão.");
			}
		}
	
		
		return expenseList;
	}
	
	public void delete(int code) throws DBException {
        connection = OracleConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String query = "DELETE FROM T_DESPESA WHERE CD_DESPESA = ?";
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, code);
			
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DBException("Erro ao deletar despesa.");
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
