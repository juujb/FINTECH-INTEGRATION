package br.com.fintech.dao.interfaces;

import br.com.fintech.bean.DBException;
import br.com.fintech.bean.User;

public interface UserDaoInterface {
	User auth(User user) throws DBException;
	void createUser(User user) throws DBException;
}
