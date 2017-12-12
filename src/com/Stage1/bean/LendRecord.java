package com.Stage1.bean;

import java.util.Date;

public class LendRecord {
	private String lrId;
//	private int lrIsReturned; 车辆是否归还可以通过车辆属性carIsLendable获取,也可以通过carReturnDate是否为null判断
	private int userId;
	private String userAccount;
	private int carId;
	private String carModelName;
	private double carLendPrice;
	private double carCountLendPrince;
	private String carNote;
	private String carBrand;
	private String carType;
	private Date carLendDate;
	private Date carReturnDate;
	
	public LendRecord() {
		super();
	}

	public LendRecord(String lrId, int userId, String userAccount, int carId, String carModelName, double carLendPrice,
			double carCountLendPrince, String carNote, String carBrand, String carType, Date carLendDate,
			Date carReturnDate) {
		super();
		this.lrId = lrId;
		this.userId = userId;
		this.userAccount = userAccount;
		this.carId = carId;
		this.carModelName = carModelName;
		this.carLendPrice = carLendPrice;
		this.carCountLendPrince = carCountLendPrince;
		this.carNote = carNote;
		this.carBrand = carBrand;
		this.carType = carType;
		this.carLendDate = carLendDate;
		this.carReturnDate = carReturnDate;
	}
//-------------------------------
	public String getLrId() {
		return lrId;
	}

	public void setLrId(String lrId) {
		this.lrId = lrId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getCarModelName() {
		return carModelName;
	}

	public void setCarModelName(String carModelName) {
		this.carModelName = carModelName;
	}

	public double getCarLendPrice() {
		return carLendPrice;
	}

	public void setCarLendPrice(double carLendPrice) {
		this.carLendPrice = carLendPrice;
	}

	public double getCarCountLendPrince() {
		return carCountLendPrince;
	}

	public void setCarCountLendPrince(double carCountLendPrince) {
		this.carCountLendPrince = carCountLendPrince;
	}

	public String getCarNote() {
		return carNote;
	}

	public void setCarNote(String carNote) {
		this.carNote = carNote;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Date getCarLendDate() {
		return carLendDate;
	}

	public void setCarLendDate(Date carLendDate) {
		this.carLendDate = carLendDate;
	}

	public Date getCarReturnDate() {
		return carReturnDate;
	}

	public void setCarReturnDate(Date carReturnDate) {
		this.carReturnDate = carReturnDate;
	}
//-----------------------------
	//普通用户查看自己的租赁记录
	public String toStringUser() {
		return 	lrId + "\t" +
				carId + "\t" +
				carModelName + "\t" +
				carCountLendPrince + "\t" +
				carNote + "\t" +
				carBrand + "\t" +
				carType + "\t" +
				carLendDate + "\t" +
				carReturnDate;
	}	
	
	//管理员查看全部租赁记录
	public String toStringAdmin() {
		return 	lrId + "\t" +
				carId + "\t" +
				carModelName + "\t" +
				userId + "\t" +
				userAccount + "\t" +
				carLendPrice + "\t" +
				carCountLendPrince + "\t" +
				carNote + "\t" +
				carBrand + "\t" +
				carType + "\t" +
				carLendDate + "\t" +
				carReturnDate;
	}
	
	@Override
	public String toString() {
		return "LendRecord [lrId=" + lrId + ", userId=" + userId + ", userAccount=" + userAccount + ", carId=" + carId
				+ ", carModelName=" + carModelName + ", carLendPrice=" + carLendPrice + ", carCountLendPrince="
				+ carCountLendPrince + ", carNote=" + carNote + ", carBrand=" + carBrand + ", carType=" + carType
				+ ", carLendDate=" + carLendDate + ", carReturnDate=" + carReturnDate + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
}


