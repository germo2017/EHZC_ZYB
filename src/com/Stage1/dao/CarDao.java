package com.Stage1.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.Stage1.bean.Car;

public interface CarDao {

	public ArrayList<Car> showCar() throws SQLException;
	public ArrayList<Car> searchCar(String str);
	public ArrayList<Car> rentCar();
	public ArrayList<Car> returnCar();
	public ArrayList<Car> showCarRentRecord();
	
	
}
