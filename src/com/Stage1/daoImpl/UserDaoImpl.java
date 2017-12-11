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
		// --�ʺŽ���ռλ��.
		String sql = "select * from EHZC_User where userAccount = ?";
		// --ͨ�����ӻ�ȡPreparedStatement����
		mStatement = mConnection.prepareStatement(sql);
		// --ͨ��PreparedStatement�����ռλ������ֵ����
		mStatement.setString(1, userAccount);
		// --ͨ��PreparedStatement���÷�����ִ�в�ѯ��䲢��ȡ���������
		// executeQuery �ǲ�ѯר��
		/*
		 * �����ĵ���������������.��������Ϊnull
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
		//ͨ�����ӻ�ȡPreparedStatement����
		mStatement = mConnection.prepareStatement(sql);
		mStatement.setString(1, inputAccount);
		mStatement.setString(2, inputPwd);
		/*
		 *mStatement.executeUpdate()���ظ��µĽ�����е���������
		 */
		rInt = mStatement.executeUpdate();
		if (rInt > 0) {
			return getUserByUserAccount(inputAccount);
		}
		return null;
	}
	
	
}
