package com.Stage1.bizImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import org.pmw.tinylog.Logger;
import com.Stage1.bean.Car;
import com.Stage1.bean.LendRecord;
import com.Stage1.biz.CarBiz;
import com.Stage1.dao.CarDao;
import com.Stage1.daoImpl.CarDaoImpl;

public class CarBizImpl implements CarBiz {
	private CarDao cd;
	private char[] cr;
	private String str2;
	private ArrayList<Car> carList;
	// private Car car;
	private ArrayList<LendRecord> lrList;
	// private int it;

	public CarBizImpl() {
		cd = new CarDaoImpl();
		;
		carList = new ArrayList<Car>();
		cr = null;
		str2 = "";
		// car = null;
		// it = 0;
	}

	@Override
	public void searchCar(String str, String userAccount) throws SQLException {
		cr = str.toCharArray();
		switch (cr[0]) {
		// 输入0:退出
		case '0':
			break;
		// 1+汽车编号:进入租车订单,租赁此编号汽车
		case '1':
			str2 = cr[2] + "";
			carList = cd.rentCar(str2, userAccount);
			break;
		// 2+1按价格降序排列,2+2按价格升序排列
		case '2':
			try {
				carList = cd.showCar();
				Collections.sort(carList);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (cr[2] == '1') {
				for (int i = carList.size() - 1; i >= 0; i--) {
					System.out.println(carList.get(i).toStringUser());
				}
			} else if (cr[2] == '2') {
				for (Car car : carList) {
					System.out.println(car.toStringUser());
				}
			} else {
				System.out.println("对不起,您输入的信息有误!");
			}
			break;
		// 3+类型编号:按类型搜索
		case '3':
			try {
				carList = cd.showCar();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			str2 = cr[2] + "";
			for (Car car : carList) {
				if (str2.equals(car.getCarTypeId() + "")) {
					System.out.println(car.toStringUser());
				}
			}
			str2 = "";
			break;
		// 4+品牌编号:按品牌搜索
		case '4':
			try {
				carList = cd.showCar();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			str2 = cr[2] + "";
			for (Car car : carList) {
				if (str2.equals(car.getCarBrandId() + "")) {
					System.out.println(car.toStringUser());
				}
			}
			str2 = "";
			break;
		// 5 查看全部汽车
		case '5':
			showUserAllCar();
			break;
		// 6 查看我的租车记录
		case '6':
			lrList = cd.showCarRentRecord();
			System.out.println("==================================================");
			System.out.println("编	 号" + "\t" + "汽车编号" + "\t" + "汽车名称" + "\t" + "租金总额" + "\t" + "备          注" + "\t"
					+ "品      牌    " + "\t" + "类          型    " + "\t" + "借车时间" + "\t" + "还车时间");
			for (LendRecord lr : lrList) {
				if (userAccount.equals(lr.getUserAccount()))
					System.out.println(lr.toStringUser());
			}
			// 6.查询租车记录后,自动跳转到另一查询界面,可还车
			System.out.println("6.查询租车记录后,自动跳转到另一查询界面,可还车");
			break;
		// 7+汽车编号:还车
		case '7':

			break;
		// 模糊查询 输入任意的车型名称/车品牌/大小类型
		case '8':
			for (int i = 2; i < cr.length; i++) {
				str2 += cr[i];
			}
			carList = cd.searchCarbyCarModelNameOrCarBrandOrCarType(str2);
			if (!carList.isEmpty()) {
				System.out.println("==================================================");
				System.out.println("编    号" + "\t" + "汽车名称" + "\t" + "备          注" + "\t" + "品      牌    " + "\t"
						+ "类          型    " + "\t" + "价          格    " + "\t" + "是否可租");
				for (Car car : carList) {
					if ("上架".equals(car.getCarPutStatus())) {
						System.out.println(car.toStringUser());
					}
				}
			} else {
				System.out.println("对不起,数据库中查不到此车型/品牌/车大小型号,或您输入的信息有误:不可同时输入车型,品牌和车大小型号!");
			}

			str2 = "";
			break;
		default:
			System.out.println("对不起,您输入的信息有误!");
			Logger.info("用户输入的查询字符不满足要求!");
			// System.out.println("switch(char),case值需要加''?");
			break;
		}

	}

	/**
	 * 给普通用户显示所有已上架的汽车
	 */
	@Override
	public void showUserAllCar() {
		try {
			carList = cd.showCar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("==================================================");
		System.out.println("编    号" + "\t" + "汽车名称" + "\t" + "备          注" + "\t" + "品      牌    " + "\t"
				+ "类          型    " + "\t" + "价          格    " + "\t" + "是否可租");
		for (Car car : carList) {
			if ("上架".equals(car.getCarPutStatus())) {
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
	public void showCarRentRecord() {
		// TODO Auto-generated method stub

	}

}
