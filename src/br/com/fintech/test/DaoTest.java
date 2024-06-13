package br.com.fintech.test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import br.com.fintech.bean.*;
import br.com.fintech.dao.implementation.*;
import br.com.fintech.factory.DaoFactory;

public class DaoTest {
	private static ZoneOffset zoneOffset = ZoneOffset.of("-03:00");
	
	public static void main(String[] args) throws DBException {		
		RevenueDao revenueDao = DaoFactory.getRevenueDao();
		
		var revenues = revenueDao.getAll();

	}
}
