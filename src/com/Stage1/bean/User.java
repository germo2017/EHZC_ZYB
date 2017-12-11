package com.Stage1.bean;


public class User {
	private int userId;
	private String userAccount;
	private String userPwd;	
	private int userType;
	
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	public User(int userId, String userAccount, String userPwd, int userType) {
		super();
		this.userAccount = userAccount;
		this.userPwd = userPwd;
		this.userId = userId;
		this.userType = userType;
	}
	
	public User() {
		super();
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userAccount=" + userAccount + ", userPwd=" + userPwd + ", userType="
				+ userType + "]";
	}
	
	
}
