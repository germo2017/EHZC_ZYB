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
		// ����0:�˳�
		case '0':
			break;
		// 1+�������:�����⳵����,���޴˱������
		case '1':
			str2 = cr[2] + "";
			carList = cd.rentCar(str2, userAccount);
			break;
		// 2+1���۸�������,2+2���۸���������
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
				System.out.println("�Բ���,���������Ϣ����!");
			}
			break;
		// 3+���ͱ��:����������
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
		// 4+Ʒ�Ʊ��:��Ʒ������
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
		// 5 �鿴ȫ������
		case '5':
			showUserAllCar();
			break;
		// 6 �鿴�ҵ��⳵��¼
		case '6':
			lrList = cd.showCarRentRecord();
			System.out.println("==================================================");
			System.out.println("��	 ��" + "\t" + "�������" + "\t" + "��������" + "\t" + "����ܶ�" + "\t" + "��          ע" + "\t"
					+ "Ʒ      ��    " + "\t" + "��          ��    " + "\t" + "�賵ʱ��" + "\t" + "����ʱ��");
			for (LendRecord lr : lrList) {
				if (userAccount.equals(lr.getUserAccount()))
					System.out.println(lr.toStringUser());
			}
			// 6.��ѯ�⳵��¼��,�Զ���ת����һ��ѯ����,�ɻ���
			System.out.println("6.��ѯ�⳵��¼��,�Զ���ת����һ��ѯ����,�ɻ���");
			break;
		// 7+�������:����
		case '7':

			break;
		// ģ����ѯ ��������ĳ�������/��Ʒ��/��С����
		case '8':
			for (int i = 2; i < cr.length; i++) {
				str2 += cr[i];
			}
			carList = cd.searchCarbyCarModelNameOrCarBrandOrCarType(str2);
			if (!carList.isEmpty()) {
				System.out.println("==================================================");
				System.out.println("��    ��" + "\t" + "��������" + "\t" + "��          ע" + "\t" + "Ʒ      ��    " + "\t"
						+ "��          ��    " + "\t" + "��          ��    " + "\t" + "�Ƿ����");
				for (Car car : carList) {
					if ("�ϼ�".equals(car.getCarPutStatus())) {
						System.out.println(car.toStringUser());
					}
				}
			} else {
				System.out.println("�Բ���,���ݿ��в鲻���˳���/Ʒ��/����С�ͺ�,�����������Ϣ����:����ͬʱ���복��,Ʒ�ƺͳ���С�ͺ�!");
			}

			str2 = "";
			break;
		default:
			System.out.println("�Բ���,���������Ϣ����!");
			Logger.info("�û�����Ĳ�ѯ�ַ�������Ҫ��!");
			// System.out.println("switch(char),caseֵ��Ҫ��''?");
			break;
		}

	}

	/**
	 * ����ͨ�û���ʾ�������ϼܵ�����
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
		System.out.println("��    ��" + "\t" + "��������" + "\t" + "��          ע" + "\t" + "Ʒ      ��    " + "\t"
				+ "��          ��    " + "\t" + "��          ��    " + "\t" + "�Ƿ����");
		for (Car car : carList) {
			if ("�ϼ�".equals(car.getCarPutStatus())) {
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
