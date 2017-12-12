package com.Stage1.biz;

import java.sql.SQLException;

public interface CarBiz {
	public void searchCar(String str1, String str2) throws SQLException;
	public void showUserAllCar();
	public void RentCar();
	public void ReturnCar();
	public void showCarRentRecord();
	
}
