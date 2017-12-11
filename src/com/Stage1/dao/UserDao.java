package com.Stage1.dao;

import java.sql.SQLException;

import com.Stage1.bean.User;

public interface UserDao {

	public User getUserByUserAccount(String userAccount) throws SQLException;
	public User registUser(String inputAccount, String inputPwd) throws SQLException;
}
