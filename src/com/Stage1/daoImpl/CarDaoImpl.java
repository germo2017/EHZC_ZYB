package com.Stage1.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.Stage1.bean.Car;
import com.Stage1.dao.CarDao;
import com.Stage1.tool.DBHelper;

public class CarDaoImpl implements CarDao {
	private Connection mConnection;
	private PreparedStatement pStatement;
	private ResultSet rSet;
	private DBHelper mDB;
	private int rInt;

	public CarDaoImpl() {
		mDB = new DBHelper();
		mConnection = mDB.getConnection();
		rSet = null;
	}

	/**
	 * 显示所有的Car,包括未上架的
	 * @throws SQLException 
	 */
	@Override
	public ArrayList<Car> showCar() throws SQLException {
		// --问号叫做占位符.
		ArrayList<Car> array = new ArrayList<>();
		String sql = "select * from EHZC_Car";
		// --通过连接获取PreparedStatement对象
		pStatement = mConnection.prepareStatement(sql);
		// --通过PreparedStatement调用方法来执行查询语句并获取结果集对象
		rSet = pStatement.executeQuery();
		//如果查的到则结果集中有数据.否则结果集为null
		if (rSet == null) {
			return null;
		}
		Car car = null;
		while (rSet.next()) {
			car = new Car();
			
			car.setCarId(rSet.getInt("CarId"));
			car.setCarModelName(rSet.getString("carModelName"));
			car.setCarNote(rSet.getString("carNote"));
			car.setCarBrand(rSet.getString("carBrand"));//s
			car.setCarBrandId(rSet.getInt("carBrandId"));//int
			car.setCarType(rSet.getString("carType"));//s
			car.setCarTypeId(rSet.getInt("carTypeId"));
			car.setCarLendPrice(rSet.getLong("carLendPrice"));//double
			car.setCarIsLendable(rSet.getInt("carIsLendable"));
			car.setCarPutStatus(rSet.getInt("carPutStatus"));
			car.setCarOrderBy(rSet.getString("carOrderBy"));//s
			car.setCarColor(rSet.getString("carColor"));//s
			car.setCarPrice(rSet.getInt("carPrice"));
			car.setCarPlateNum(rSet.getString("carPlateNum"));//s
			car.setCarLendDate(rSet.getDate("carLendDate"));
			array.add(car);
		
		}

		return array;
	}

	public ArrayList<Car> searchCar(String str){
		ArrayList<Car> array = new ArrayList<>();
		
		
		
		
		
		
		
		
		
		
		
		return array;
		
	}

	@Override
	public ArrayList<Car> rentCar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Car> returnCar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Car> showCarRentRecord() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
}
