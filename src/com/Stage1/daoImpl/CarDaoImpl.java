package com.Stage1.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.pmw.tinylog.Logger;

import com.Stage1.bean.Car;
import com.Stage1.bean.LendRecord;
import com.Stage1.dao.CarDao;
import com.Stage1.tool.DBHelper;

/**
 * 
 * @ClassName: CarDaoImpl.java
 * @Package: com.Stage1.daoImpl
 * @Description: TODO Car和LendRecord的Dao实现类,在此进行数据库的操作
 *
 * @author Administrator
 * @date 2017年12月12日下午3:54:31
 * @version 1.0
 */
public class CarDaoImpl implements CarDao {
	private Connection mConnection;
	private PreparedStatement pStatement;
	private ResultSet rSet;
	private DBHelper mDB;
	private int rInt;
	private ArrayList<Car> array;
	private ArrayList<LendRecord> arrayLR;

	public CarDaoImpl() {
		mDB = new DBHelper();
		mConnection = mDB.getConnection();
		rSet = null;
		array = null;
		arrayLR = null;
	}

	/**
	 * 普通User和Admin,根据car.getInt("carPutStatus")判断,是否打印
	 * 
	 * @return 返回所有的Car,包括未上架的
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Car> showCar() throws SQLException {
		// --问号叫做占位符.
		array = new ArrayList<>();
		String sql = "select * from EHZC_Car";
		// --通过连接获取PreparedStatement对象
		pStatement = mConnection.prepareStatement(sql);
		// --通过PreparedStatement调用方法来执行查询语句并获取结果集对象
		rSet = pStatement.executeQuery();
		// 如果查的到则结果集中有数据.否则结果集为null
		if (rSet == null) {
			return null;
		}
		Car car = null;
		while (rSet.next()) {
			car = new Car();

			car.setCarId(rSet.getInt("CarId"));
			car.setCarModelName(rSet.getString("carModelName"));
			car.setCarNote(rSet.getString("carNote"));
			car.setCarBrand(rSet.getString("carBrand"));// s
			car.setCarBrandId(rSet.getInt("carBrandId"));// int
			car.setCarType(rSet.getString("carType"));// s
			car.setCarTypeId(rSet.getInt("carTypeId"));
			car.setCarLendPrice(rSet.getLong("carLendPrice"));// double
			car.setCarIsLendable(rSet.getString("carIsLendable"));
			car.setCarPutStatus(rSet.getString("carPutStatus"));
			car.setCarOrderBy(rSet.getString("carOrderBy"));// s
			car.setCarColor(rSet.getString("carColor"));// s
			car.setCarPrice(rSet.getInt("carPrice"));
			car.setCarPlateNum(rSet.getString("carPlateNum"));// s
			car.setCarLendDate(rSet.getDate("carLendDate"));
			array.add(car);

		}
		rSet.close();
		pStatement.close();
		// 因进入查询界面,会默认显示全部已上架车辆,为了继续搜索或显示,此处mConnection不关闭
		// mConnection.close();
		rSet = null;
		pStatement = null;
		// mConnection = null;

		return array;
	}

	/**
	 * 模糊查询,根据车型名称,车品牌,车大小型号查询
	 */
	public ArrayList<Car> searchCarbyCarModelNameOrCarBrandOrCarType(String str) throws SQLException {
		// String sql = "select * from EHZC_Car where carModelName like '%大%' or
		// carBrand like '%大%' or carType like '%大%'";
		String sql = "select * from EHZC_Car where carModelName like ? or carBrand like ? or carType like ?";
		array = new ArrayList<>();
		pStatement = mConnection.prepareStatement(sql);
		// 不用加'号:str = "'%"+str+"%'";
		str = "%" + str + "%";
		pStatement.setString(1, str);
		pStatement.setString(2, str);
		pStatement.setString(3, str);
		rSet = pStatement.executeQuery();
		if (rSet == null) {
			return null;
		}
		Car car = null;
		while (rSet.next()) {
			car = new Car(rSet.getInt("CarId"), rSet.getString("carModelName"), rSet.getString("carNote"),
					rSet.getString("carBrand"), rSet.getInt("carBrandId"), rSet.getString("carType"),
					rSet.getInt("carTypeId"), rSet.getLong("carLendPrice"), rSet.getString("carIsLendable"),
					rSet.getString("carPutStatus"), rSet.getString("carOrderBy"), rSet.getString("carColor"),
					rSet.getInt("carPrice"), rSet.getString("carPlateNum"), rSet.getDate("carLendDate"));
			array.add(car);
		}
		rSet.close();
		pStatement.close();
		// mConnection.close();
		rSet = null;
		pStatement = null;
		// mConnection = null;

		return array;
	}

	/**
	 * 用户租赁汽车
	 */
	@Override
	public ArrayList<Car> rentCar(String carId, String userAccount) {
		array = new ArrayList<>();
		arrayLR = new ArrayList<>();
		try {
			try {
				// 将事务模式设置为手动提交事务：
				mConnection.setAutoCommit(false);
				// 设置事务的隔离级别。
				mConnection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
				// 运行查找操作,是否上架,是否可租
				String sqlrent = "select * from EHZC_Car where carId = ? and carIsLendable = '可租' and carPutStatus = '上架'";
				pStatement = mConnection.prepareStatement(sqlrent);
				pStatement.setString(1, carId);
				rSet = pStatement.executeQuery();
				if (rSet == null) {
					System.out.println("对不起, 无此编号汽车,或车已借出!");
					return null;
				}
				Car car = null;
				while (rSet.next()) {
					car = new Car(rSet.getInt("CarId"), rSet.getString("carModelName"), rSet.getString("carNote"),
							rSet.getString("carBrand"), rSet.getInt("carBrandId"), rSet.getString("carType"),
							rSet.getInt("carTypeId"), rSet.getLong("carLendPrice"), rSet.getString("carIsLendable"),
							rSet.getString("carPutStatus"), rSet.getString("carOrderBy"), rSet.getString("carColor"),
							rSet.getInt("carPrice"), rSet.getString("carPlateNum"), rSet.getDate("carLendDate"));
				}				
				// 如果该车未被预订,或预定人与借车人一致,则update Car表,新增LendRecord记录
				if(car.getCarOrderBy().equalsIgnoreCase("NUll") || car.getCarOrderBy().equals(userAccount)) {
					sqlrent = "update EHZC_Car set carIsLendable = '否', carLendDate = ? where carId = ?";
					pStatement = mConnection.prepareStatement(sqlrent);
					pStatement.setString(1, "sysdate");
					pStatement.setString(2, carId);
					pStatement.executeUpdate();
				
					//修改car信息
					while (rSet.next()) {//此处有误.rSet未更新,且用的是Update
						car = new Car(rSet.getInt("CarId"), rSet.getString("carModelName"), rSet.getString("carNote"),
								rSet.getString("carBrand"), rSet.getInt("carBrandId"), rSet.getString("carType"),
								rSet.getInt("carTypeId"), rSet.getLong("carLendPrice"), rSet.getString("carIsLendable"),
								rSet.getString("carPutStatus"), rSet.getString("carOrderBy"), rSet.getString("carColor"),
								rSet.getInt("carPrice"), rSet.getString("carPlateNum"), rSet.getDate("carLendDate"));
					}
					// 修改LendRecord表: 新增insert记录操作
				
					
					// 提交事务
					mConnection.commit();
				}else {
					System.out.println("对不起,该车已被预定,请重新租赁其他车辆!");
				}			
				
			} finally {
				if (rSet != null) {
					rSet.close();
				}				
				if (pStatement != null) {
					pStatement.close();
				}
			/*	if (mConnection != null) {
					mConnection.close();
				}
			*/
			}
		} catch (Exception e) {
			try {
				// 若事务发生异常，回滚事务
				mConnection.rollback();
			} catch (SQLException e1) {
				Logger.error(e1.getMessage());
			}
		}

		return array;

	}

	@Override
	public ArrayList<Car> returnCar() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取全部租赁记录
	 * 
	 * @return 返回租赁记录
	 * @throws SQLException
	 */
	@Override
	public ArrayList<LendRecord> showCarRentRecord() throws SQLException {
		arrayLR = new ArrayList<>();
		String sql = "select * from EHZC_LendRecord";
		pStatement = mConnection.prepareStatement(sql);
		rSet = pStatement.executeQuery();
		if (rSet == null) {
			return null;
		}
		LendRecord lr = null;
		while (rSet.next()) {
			lr = new LendRecord(rSet.getString("lrId"), rSet.getInt("userId"), rSet.getString("userAccount"),
					rSet.getInt("carId"), rSet.getString("carModelName"), rSet.getDouble("carLendPrice"),
					rSet.getDouble("carCountLendPrince"), rSet.getString("carNote"), rSet.getString("carBrand"),
					rSet.getString("carType"), rSet.getDate("carLendDate"), rSet.getDate("carReturnDate"));
			arrayLR.add(lr);
		}

		rSet.close();
		pStatement.close();
		rSet = null;
		pStatement = null;

		return arrayLR;
	}

	/**
	 * 生成4位随机数,以字符返回
	 */
	public String generateRandomNum() {

		return null;
	}
	
}
