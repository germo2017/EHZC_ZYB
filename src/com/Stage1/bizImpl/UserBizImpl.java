package com.Stage1.bizImpl;

import java.sql.SQLException;
import com.Stage1.bean.User;
import com.Stage1.biz.UserBiz;
import com.Stage1.dao.UserDao;
import com.Stage1.daoImpl.UserDaoImpl;

public class UserBizImpl implements UserBiz{
	private UserDao ud;
	private User u;
	
	
	public User getU() {
		return u;
	}

	public UserBizImpl() {
		ud = new UserDaoImpl();
		u = null;
	}
	
	@Override
	public boolean userlogin(String userAccount, String userPwd) {
		try {
			u = ud.getUserByUserAccount(userAccount);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		if(u == null) {
			return false;
		}
	
		return userPwd.equals(u.getUserPwd());
	}
	
	@Override
	public boolean userRegister(String inputAccount, String inputPwd) {
		if (inputAccount == null || inputPwd == null ||  inputAccount.length() == 0 || inputPwd.length() == 0 ) {
			return false;
		}
		try {
			u = ud.registUser(inputAccount, inputPwd);
			return true;
		} catch (Exception e) {
			System.out.println("您注册的账户名称不可用!");
		}
		
		return false;
	}
	
	
	
}
