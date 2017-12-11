package com.Stage1.bizImpl;

import java.sql.SQLException;
import java.util.ArrayList;

import com.Stage1.bean.Car;
import com.Stage1.biz.CarBiz;
import com.Stage1.dao.CarDao;
import com.Stage1.daoImpl.CarDaoImpl;

public class CarBizImpl implements CarBiz{
	private CarDao cd;
	private ArrayList<Car> CarList;
	
	
	public CarBizImpl() {
		cd = new CarDaoImpl();;
		CarList = new ArrayList<Car>();
	}

	@Override
	public void searchCar(String str) {
		CarList = cd.searchCar(str);
		
	}
	
	@Override
	public void showUserAllCar() {
		try {
			CarList = cd.showCar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("==================================================");
		System.out.println("编    号"+"\t"+"汽车名称"+"\t"+"备          注"+"\t"+"品      牌    "+"\t"+"类          型    "+"\t"+"价          格    "+"\t"+"是否可租");
		for (Car car : CarList) {
			if(car.getCarPutStatus()==1) {
				System.out.println(car.toStringUser());
			}
		}
		
		
	
	}

	@Override
	public void RentCar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ReturnCar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RentRecord() {
		// TODO Auto-generated method stub
		
	}

	

}
