package com.Stage1.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Stage1.bean.User;
import com.Stage1.dao.UserDao;
import com.Stage1.tool.DBHelper;


public class UserDaoImpl implements UserDao{

	private Connection mConnection;
	private PreparedStatement mStatement;
	private ResultSet rSet;
	private DBHelper mDB;
	private int rInt;

	public UserDaoImpl() {
		mDB = new DBHelper();
		mConnection = mDB.getConnection();
		rSet = null;
	}

	@Override
	public User getUserByUserAccount(String userAccount) throws SQLException{
		// --问号叫做占位符.
		String sql = "select * from EHZC_User where userAccount = ?";
		// --通过连接获取PreparedStatement对象
		mStatement = mConnection.prepareStatement(sql);
		// --通过PreparedStatement对象给占位符做赋值操作
		mStatement.setString(1, userAccount);
		// --通过PreparedStatement调用方法来执行查询语句并获取结果集对象
		// executeQuery 是查询专用
		/*
		 * 如果查的到则结果集中有数据.否则结果集为null
		 */
		rSet = mStatement.executeQuery();
		if (rSet == null) {
			return null;
		}
		User u = null;
		while (rSet.next()) {
			u = new User();
			u.setUserId(rSet.getInt("USERID"));
			u.setUserAccount(rSet.getString("USERACCOUNT"));
			u.setUserPwd(rSet.getString("USERPWD"));
			u.setUserType(rSet.getInt("USERTYPE"));
			//System.out.println(u);
		}
		return u;
	}
	
	public User registUser(String inputAccount, String inputPwd) throws SQLException {
		String sql = "insert into EHZC_User values(User_seq.nextval, ?, ?, default)";
		//通过连接获取PreparedStatement对象
		mStatement = mConnection.prepareStatement(sql);
		mStatement.setString(1, inputAccount);
		mStatement.setString(2, inputPwd);
		/*
		 *mStatement.executeUpdate()返回更新的结果集中的数据行数
		 */
		rInt = mStatement.executeUpdate();
		if (rInt > 0) {
			return getUserByUserAccount(inputAccount);
		}
		return null;
	}
	
	
}
