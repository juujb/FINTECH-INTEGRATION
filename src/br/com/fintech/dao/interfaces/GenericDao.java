package br.com.fintech.dao.interfaces;

import java.util.ArrayList;

import br.com.fintech.bean.DBException;

public interface GenericDao<T> {
	ArrayList<T> getAll() throws DBException;
	void insert(T object) throws DBException;
	void delete(int code) throws DBException;
}
