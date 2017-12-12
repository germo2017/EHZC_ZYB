package com.Stage1.bean;
import java.util.Date;

public class Car implements Comparable<Car>{
	private int carId;
	private String carModelName;
	private String carNote;
	private String carBrand;
	private int carBrandId;
	private String carType;
	private int carTypeId;
	private double carLendPrice;
	private String carIsLendable;//
	private String carPutStatus;//
	private String carOrderBy;
	private String carColor;
	private double carPrice;
	private String carPlateNum;
	private Date carLendDate;
	
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
	public int getCarBrandId() {
		return carBrandId;
	}
	public void setCarBrandId(int carBrandId) {
		this.carBrandId = carBrandId;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public int getCarTypeId() {
		return carTypeId;
	}
	public void setCarTypeId(int carTypeId) {
		this.carTypeId = carTypeId;
	}
	public double getCarLendPrice() {
		return carLendPrice;
	}
	public void setCarLendPrice(double carLendPrice) {
		this.carLendPrice = carLendPrice;
	}
	public String getCarIsLendable() {
		return carIsLendable;
	}
	public void setCarIsLendable(String carIsLendable) {
		this.carIsLendable = carIsLendable;
	}
	public String getCarPutStatus() {
		return carPutStatus;
	}
	public void setCarPutStatus(String carPutStatus) {
		this.carPutStatus = carPutStatus;
	}
	public String getCarOrderBy() {
		return carOrderBy;
	}
	public void setCarOrderBy(String carOrderBy) {
		this.carOrderBy = carOrderBy;
	}
	public String getCarColor() {
		return carColor;
	}
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	public double getCarPrice() {
		return carPrice;
	}
	public void setCarPrice(double carPrice) {
		this.carPrice = carPrice;
	}
	public String getCarPlateNum() {
		return carPlateNum;
	}
	public void setCarPlateNum(String carPlateNum) {
		this.carPlateNum = carPlateNum;
	}
	public Date getCarLendDate() {
		return carLendDate;
	}
	public void setCarLendDate(Date carLendDate) {
		this.carLendDate = carLendDate;
	}
	
	public Car() {
		super();
	}	
	
	public Car(int carId, String carModelName, String carNote, String carBrand, int carBrandId, String carType,
			int carTypeId, double carLendPrice, String carIsLendable, String carPutStatus, String carOrderBy, String carColor,
			double carPrice, String carPlateNum, Date carLendDate) {
		super();
		this.carId = carId;
		this.carModelName = carModelName;
		this.carNote = carNote;
		this.carBrand = carBrand;
		this.carBrandId = carBrandId;
		this.carType = carType;
		this.carTypeId = carTypeId;
		this.carLendPrice = carLendPrice;
		this.carIsLendable = carIsLendable;
		this.carPutStatus = carPutStatus;
		this.carOrderBy = carOrderBy;
		this.carColor = carColor;
		this.carPrice = carPrice;
		this.carPlateNum = carPlateNum;
		this.carLendDate = carLendDate;
	}
	
	public String toStringUser() {
		return 	carId + "\t" +
				carModelName+ "\t" +
				carNote+ "\t" +
				carBrand +"("+ carBrandId +")"+ "\t" +
				carType+"(" + carTypeId+")"+ "\t"+
				carLendPrice +"/天" + "\t" +
				carIsLendable;
	}


	public String toStringAdmin() {
		return 	carId + "\t" +
				carModelName+ "\t" +
				carNote+ "\t" +
				carBrand +"("+ carBrandId +")"+ "\t" +
				carType+"(" + carTypeId+")"+ "\t"+
				carLendPrice +"/天" + "\t" +
				carIsLendable + "\t" +
				carPutStatus;
	}
	
	//测试日期是否已转码
	@Override
	public String toString() {
		return "Car [carId=" + carId + ", carLendDate=" + carLendDate + "]";
	}
	
	//按车租金价格排序的compareTo方法
	@Override
	public int compareTo(Car o) {
		return (int) (this.carLendPrice -o.carLendPrice);
	}
	
	
	
	
	
	
}
