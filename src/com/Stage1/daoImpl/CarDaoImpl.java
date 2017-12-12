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
 * @Description: TODO Car��LendRecord��Daoʵ����,�ڴ˽������ݿ�Ĳ���
 *
 * @author Administrator
 * @date 2017��12��12������3:54:31
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
	 * ��ͨUser��Admin,����car.getInt("carPutStatus")�ж�,�Ƿ��ӡ
	 * 
	 * @return �������е�Car,����δ�ϼܵ�
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Car> showCar() throws SQLException {
		// --�ʺŽ���ռλ��.
		array = new ArrayList<>();
		String sql = "select * from EHZC_Car";
		// --ͨ�����ӻ�ȡPreparedStatement����
		pStatement = mConnection.prepareStatement(sql);
		// --ͨ��PreparedStatement���÷�����ִ�в�ѯ��䲢��ȡ���������
		rSet = pStatement.executeQuery();
		// �����ĵ���������������.��������Ϊnull
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
		// ������ѯ����,��Ĭ����ʾȫ�����ϼܳ���,Ϊ�˼�����������ʾ,�˴�mConnection���ر�
		// mConnection.close();
		rSet = null;
		pStatement = null;
		// mConnection = null;

		return array;
	}

	/**
	 * ģ����ѯ,���ݳ�������,��Ʒ��,����С�ͺŲ�ѯ
	 */
	public ArrayList<Car> searchCarbyCarModelNameOrCarBrandOrCarType(String str) throws SQLException {
		// String sql = "select * from EHZC_Car where carModelName like '%��%' or
		// carBrand like '%��%' or carType like '%��%'";
		String sql = "select * from EHZC_Car where carModelName like ? or carBrand like ? or carType like ?";
		array = new ArrayList<>();
		pStatement = mConnection.prepareStatement(sql);
		// ���ü�'��:str = "'%"+str+"%'";
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
	 * �û���������
	 */
	@Override
	public ArrayList<Car> rentCar(String carId, String userAccount) {
		array = new ArrayList<>();
		arrayLR = new ArrayList<>();
		try {
			try {
				// ������ģʽ����Ϊ�ֶ��ύ����
				mConnection.setAutoCommit(false);
				// ��������ĸ��뼶��
				mConnection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
				// ���в��Ҳ���,�Ƿ��ϼ�,�Ƿ����
				String sqlrent = "select * from EHZC_Car where carId = ? and carIsLendable = '����' and carPutStatus = '�ϼ�'";
				pStatement = mConnection.prepareStatement(sqlrent);
				pStatement.setString(1, carId);
				rSet = pStatement.executeQuery();
				if (rSet == null) {
					System.out.println("�Բ���, �޴˱������,���ѽ��!");
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
				// ����ó�δ��Ԥ��,��Ԥ������賵��һ��,��update Car��,����LendRecord��¼
				if(car.getCarOrderBy().equalsIgnoreCase("NUll") || car.getCarOrderBy().equals(userAccount)) {
					sqlrent = "update EHZC_Car set carIsLendable = '��', carLendDate = ? where carId = ?";
					pStatement = mConnection.prepareStatement(sqlrent);
					pStatement.setString(1, "sysdate");
					pStatement.setString(2, carId);
					pStatement.executeUpdate();
				
					//�޸�car��Ϣ
					while (rSet.next()) {//�˴�����.rSetδ����,���õ���Update
						car = new Car(rSet.getInt("CarId"), rSet.getString("carModelName"), rSet.getString("carNote"),
								rSet.getString("carBrand"), rSet.getInt("carBrandId"), rSet.getString("carType"),
								rSet.getInt("carTypeId"), rSet.getLong("carLendPrice"), rSet.getString("carIsLendable"),
								rSet.getString("carPutStatus"), rSet.getString("carOrderBy"), rSet.getString("carColor"),
								rSet.getInt("carPrice"), rSet.getString("carPlateNum"), rSet.getDate("carLendDate"));
					}
					// �޸�LendRecord��: ����insert��¼����
				
					
					// �ύ����
					mConnection.commit();
				}else {
					System.out.println("�Բ���,�ó��ѱ�Ԥ��,������������������!");
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
				// ���������쳣���ع�����
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
	 * ��ȡȫ�����޼�¼
	 * 
	 * @return �������޼�¼
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
	 * ����4λ�����,���ַ�����
	 */
	public String generateRandomNum() {

		return null;
	}
	
}
