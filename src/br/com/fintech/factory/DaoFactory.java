package br.com.fintech.factory;

import br.com.fintech.dao.implementation.*;

public abstract class DaoFactory {

	public static ExpenseDao getExpenseDao() {
		return new ExpenseDao();
	}
	
	public static RevenueDao getRevenueDao() {
		return new RevenueDao();
	}
	
	public static InvestmentDao getInvestmentDao() {
		return new InvestmentDao();
	}
	
	public static UserDao getUserDao() {
		return new UserDao();
	}

	public static WalletDao getWalletDao() {
		return new WalletDao();
	}
	
}
