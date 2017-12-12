package com.Stage1.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.Stage1.bean.Car;
import com.Stage1.bean.LendRecord;
/**
 * @ClassName:	 CarDao.java
 * @Package:	 com.Stage1.dao
 * @Description: TODO Car�͡�LendRecord���Dao�ӿ�
 *
 * @author	Administrator
 * @date	2017��12��12������5:19:10
 * @version	1.0
 */
public interface CarDao {

	public ArrayList<Car> showCar() throws SQLException;
	public ArrayList<Car> searchCarbyCarModelNameOrCarBrandOrCarType(String str) throws SQLException;
	public ArrayList<Car> rentCar(String str, String userAccount);
	public ArrayList<Car> returnCar();
	public ArrayList<LendRecord> showCarRentRecord() throws SQLException;
	
	
}
